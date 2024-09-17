package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.JPAs.*;
import com.datn.clover.entity.*;
import com.datn.clover.inter.DetaillBillJPA;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.BillSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/seller/bill")
public class BillController {

    @Autowired
    BillJSellerPA billJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private ShopSellerJPA shopJPA;
    @Autowired
    private StaffSellerJPA staffJPA;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private DetaillBillJPA detaillBillJPA;
    @Autowired
    private StatusBillJPA statusBillJPA;
    @Autowired
    private BillSellerService billSellerService;

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(Errors be) {
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

    // lay het hoa don
    @GetMapping("/getAll")
    public List<Bill> getAllBill() {
            return billJPA.findAll();
    }
    //lay hoa don theo shop
    @GetMapping("/getBillByShop")
    public List<DetailBill> getBillByShop(@RequestHeader("Authorization") String header) {
        Optional<Staff> staff = staffJPA.findByUsername(jwtService.accessToken(header));
        if (staff.isEmpty()) {
            return null;
        }
        Optional<Shop> shop = shopJPA.findById(staff.get().getShop().getId().toString());
        assert shop.orElse(null) != null;
        return detaillBillJPA.findByShopID(shop.orElse(null).getId().toString());
    }

    //xac nhan hoa don
    @PutMapping("/confirmBill")
    public ResponseEntity<?> createBill(@RequestHeader("Authorization") String token, @RequestParam("id") String id) {
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Bill> bill = billJPA.findById(id);
        Optional<BillStatus> status = statusBillJPA.findById(5+"");

        if (bill.isPresent() && status.isPresent() && account.isPresent() && bill.get().getStatus().getId().equals(1) && account.get().getStaff().getShop().getId().equals(bill.get().getDetailBills().iterator().next().getProd().getShop().getId())) {
            bill.get().setStatus(status.get());
               Bill billRS = billJPA.save(bill.get());
                return ResponseEntity.ok(billSellerService.setResponse(billRS));
        }
        return ResponseEntity.notFound().build();
    }
    //huy don
    @PutMapping("/cancelBill")
    public ResponseEntity<?> cancelBill(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Bill> bill = billJPA.findById(id);
        Optional<BillStatus> status = statusBillJPA.findById(3+"");
        if (bill.isPresent() && status.isPresent() && account.isPresent() && (bill.get().getStatus().getId().equals(1)) && account.get().getStaff().getShop().getId().equals(bill.get().getDetailBills().iterator().next().getProd().getShop().getId())) {
            bill.get().setStatus(status.get());
           Bill billRS =  billJPA.save(bill.get());
            return ResponseEntity.ok(billSellerService.setResponse(billRS));
        }
        return ResponseEntity.notFound().build();
    }
}
