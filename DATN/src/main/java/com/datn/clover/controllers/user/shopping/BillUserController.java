package com.datn.clover.controllers.user.shopping;

import com.datn.clover.DTO.Sellers.BillBean;
import com.datn.clover.JPAs.*;
import com.datn.clover.entity.*;
import com.datn.clover.mapper.AccountSellerMapperImpl;
import com.datn.clover.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/bill")
public class BillUserController {


    private final Validator validator;
    private final BillUserJPA billUserJPA;
    private final JwtService jwtService;
    private final AccountSellerJPA accountSellerJPA;
    private final VoucherSellerJPA voucherSellerJPA;
    private final DetailBillUserJPA detailBillUserJPA;
    private final ProductCarUserJPA productCarUserJPA;
    private final StatusBillJPA statusBillJPA;
    private final AccountSellerMapperImpl accountSellerMapperImpl;

    public BillUserController(BillUserJPA billUserJPA, JwtService jwtService, AccountSellerJPA accountSellerJPA, VoucherSellerJPA voucherSellerJPA, ProductSellerJPA productSellerJPA, Validator validator, DetailBillUserJPA detailBillUserJPA, ProductCarUserJPA productCarUserJPA, StatusBillJPA statusBillJPA, AccountSellerMapperImpl accountSellerMapperImpl) {
        this.billUserJPA = billUserJPA;
        this.jwtService = jwtService;
        this.accountSellerJPA = accountSellerJPA;
        this.voucherSellerJPA = voucherSellerJPA;
        this.validator = validator;
        this.detailBillUserJPA = detailBillUserJPA;
        this.productCarUserJPA = productCarUserJPA;
        this.statusBillJPA = statusBillJPA;
        this.accountSellerMapperImpl = accountSellerMapperImpl;
    }

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(BindingResult be) {
        // Trả về message của lỗi đầu tiên
        Map<String, String> errors = new HashMap<>();
        String errorMessage = "Request không hợp lệ";
        errors.put("error", errorMessage);
        if (be.hasErrors()) {
            errorMessage = be.getAllErrors().getFirst().getDefaultMessage();
            errors.put("message", errorMessage);
        }
        return errors;
    }

    @GetMapping("/getBillByUsername")
    public ResponseEntity<List<Bill>> getBillByUsername(@RequestHeader("Authorization") String token) {
        List<Bill> bills = billUserJPA.findByAccount(jwtService.accessToken(token));
        return ResponseEntity.ok(bills);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestHeader("Authorization") String token,
                                           @RequestParam Map<String, String> params,
                                           @RequestParam("list") List<String> prodCarts) throws BindException {
        // Chuyển đổi params thành DTO và validate
        BillBean billBean = accountSellerMapperImpl.billToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(billBean, "bill");
        validator.validate(billBean, error);  // Sửa lại để sử dụng validator với BindingResult trực tiếp


        if (error.hasErrors()) {
            throw new BindException(error);  // Ném lỗi nếu có vấn đề khi validate bill
        }

        // Lấy thông tin tài khoản từ token
        Account account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token))
                .orElseThrow(() -> new BindException(error));

        // Kiểm tra voucher và xác thực điều kiện
        Voucher voucher = voucherSellerJPA.findById(billBean.getVoucher())
                .filter(v -> v.getEndVoucher().isAfter(Instant.now()) && v.getStartVoucher().isBefore(Instant.now()))
                .orElseThrow(() -> new BindException(error));

        // Lấy danh sách sản phẩm từ prodCarts
        List<ProdCart> prods = prodCarts.stream()
                .map(productCarUserJPA::findById)
                .flatMap(Optional::stream)
                .toList();

// Lấy danh sách Product từ ProdCart
        List<Product> products = prods.stream()
                .map(ProdCart::getProd)
                .filter(Objects::nonNull)  // Kiểm tra null để tránh lỗi
                .toList();

// Lấy danh sách Shop từ Product và loại bỏ trùng lặp
        List<Shop> shops = products.stream()
                .map(Product::getShop)
                .filter(Objects::nonNull)  // Kiểm tra null để tránh lỗi
                .distinct()                // Loại bỏ các shop trùng lặp
                .toList();

        // Kiểm tra trạng thái hóa đơn
        BillStatus status = statusBillJPA.findById("1")
                .orElseThrow(() -> new BindException(error));
        List<Bill> bills = new ArrayList<>();
for (Shop shop : shops) {
    // Tạo hóa đơn mới
    Bill bill = new Bill();
    bill.setAccount(account);
    bill.setStatus(status);
    bill.setBuyDay(LocalDate.now());
    bill.setEmail(account.getEmail());
    bill.setFullname(account.getFullname());
    bill.setPhone(account.getPhone());
    bill.setDiscountVoucher(Float.valueOf(voucher.getVoucherValue()));
    bill.setShipMoney(billBean.getShipMoney());
    bill.setPaymentMethods(billBean.getPaymentMethods());
    bill.setVoucher(voucher);

    // Lưu hóa đơn
    Bill savedBill = billUserJPA.save(bill);

    // Xử lý chi tiết hóa đơn cho mỗi sản phẩm
    prods.forEach(prod -> {
        if (prod.getProd().getShop().getId().equals(shop.getId())){


        float discount = 0;
        if (prod.getProd().getPromotion() != null &&
                prod.getProd().getPromotion().getStartDay().isBefore(Instant.now()) &&
                prod.getProd().getPromotion().getEndDay().isAfter(Instant.now())) {
            discount = prod.getProd().getPromotion().getPercentDiscount();
        }

        Float price = prod.getProd().getPrice() - (prod.getProd().getPrice() * (discount * 0.01f));
        DetailBill detailBill = new DetailBill();
        detailBill.setBill(savedBill);
        detailBill.setProd(prod.getProd());
        detailBill.setQuantity(prod.getQuantity());
        detailBill.setPrice(price);
        detailBill.setProdName(prod.getProd().getName());
        detailBill.setPercentDiscount((int) discount);
        detailBill.setTotalMoney(price * prod.getQuantity());
        // Lưu chi tiết hóa đơn
        detailBillUserJPA.save(detailBill);
        }
    });
    bills.add(savedBill);

}
return ResponseEntity.ok(bills);
}


}
