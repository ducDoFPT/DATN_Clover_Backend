package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.VoucherBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.TypeVoucherSellerJPA;
import com.datn.clover.JPAs.VoucherSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.TypeVoucher;
import com.datn.clover.entity.Voucher;
import com.datn.clover.mapper.PromotionMapper;
import com.datn.clover.mapper.PromotionMapperImpl;
import com.datn.clover.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/voucher")
public class VoucherController {
    @Autowired
    VoucherSellerJPA voucherJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private TypeVoucherSellerJPA typeJPA;
    @Autowired
    private TypeVoucherSellerJPA typeVoucherJPA;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PromotionMapperImpl promotionMapperImpl;
    @Autowired
    private PromotionMapper promotionMapper;
    @Autowired
    Validator validator;
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

    @GetMapping
    public List<Voucher> getAll() {
        return voucherJPA.findAll();
    }

    @GetMapping("/getVoucherByShop")
    public List<Voucher> getVoucherByUsername(@RequestHeader("Authorization") String token) {
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        return account.map(value -> voucherJPA.findByAccount(value.getStaff().getShop().getId().toString())).orElse(null);
    }

    @PostMapping("/create")
    public ResponseEntity<Voucher> createVoucher(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params)  throws BindException {
        VoucherBean voucher = promotionMapper.vouToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(voucher, "voucher");
         validator.validateObject(voucher);
        if (error.hasErrors()) {
            throw new BindException( error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isPresent()) {
                Optional<TypeVoucher> typeVoucher = typeVoucherJPA.findById(voucher.getTvoucher());
                Voucher voucherEntity = new Voucher();
                voucherEntity.setName(voucher.getName());
                voucherEntity.setStartVoucher(voucher.getStartVoucher());
                voucherEntity.setEndVoucher(voucher.getEndVoucher());
                voucherEntity.setAccount(account.get());
                voucherEntity.setNumberUse(voucher.getNumberUse());
                voucherEntity.setStatus(voucher.isStatus());
                voucherEntity.setTvoucher(typeVoucher.get());
                voucherEntity.setVoucherValue(voucher.getVoucherValue());
                return ResponseEntity.ok(voucherJPA.save(voucherEntity));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Voucher> updateVoucher(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) throws BindException {
        VoucherBean voucher = promotionMapper.vouToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(voucher, "voucher");
        validator.validateObject(voucher);
        if (error.hasErrors()) {
            throw new BindException( error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<TypeVoucher> typeVoucher = typeVoucherJPA.findById(voucher.getTvoucher());
            Optional<Voucher> voucherEntity = voucherJPA.findById(voucher.getId());
            if (account.isPresent() && typeVoucher.isPresent() && voucherEntity.isPresent()) {
                voucherEntity.get().setName(voucher.getName());
                voucherEntity.get().setStartVoucher(voucher.getStartVoucher());
                voucherEntity.get().setEndVoucher(voucher.getEndVoucher());
                voucherEntity.get().setAccount(account.get());
                voucherEntity.get().setNumberUse(voucher.getNumberUse());
                voucherEntity.get().setStatus(voucher.isStatus());
                voucherEntity.get().setTvoucher(typeVoucher.get());
                voucherEntity.get().setVoucherValue(voucher.getVoucherValue());
                Voucher v = voucherJPA.save(voucherEntity.get());
                return ResponseEntity.ok(v);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteVoucher(@RequestHeader("Authorization") String username, @RequestParam("id") String id) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(username));
            voucherJPA.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
