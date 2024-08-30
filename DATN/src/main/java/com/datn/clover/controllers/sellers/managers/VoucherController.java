package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.VoucherBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.TypeVoucherJPA;
import com.datn.clover.JPAs.VoucherJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.TypeVoucher;
import com.datn.clover.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/voucher")
public class VoucherController {
    @Autowired
    VoucherJPA voucherJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private TypeVoucherJPA typeJPA;
    @Autowired
    private TypeVoucherJPA typeVoucherJPA;

    @GetMapping
    public List<Voucher> getAll() {
        return voucherJPA.findAll();
    }

    @GetMapping("/getVoucherByShop/{username}")
    public List<Voucher> getVoucherByUsername(@PathVariable("username") String username) {
        Optional<Account> account = accountJPA.getAccountByUsername(username);
        return account.map(value -> voucherJPA.findByAccount(value.getStaff().getShop().getId())).orElse(null);
    }

    @PostMapping("/createVoucher/{username}")
    public ResponseEntity<Voucher> createVoucher(@PathVariable("username") String username, @RequestBody VoucherBean voucher) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            if (account.isPresent()) {
                Optional<TypeVoucher> typeVoucher = typeVoucherJPA.findById(voucher.getTvoucher());
                Voucher voucherEntity = new Voucher();
                voucherEntity.setId(voucher.getId());
                voucherEntity.setName(voucher.getName());
                voucherEntity.setStartVoucher(voucher.getStartVoucher());
                voucherEntity.setEndVoucher(voucher.getEndVoucher());
                voucherEntity.setAccount(account.get());
                voucherEntity.setNumberUse(voucher.getNumberUse());
                voucherEntity.setStatus(voucher.getStatus());
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

    @PutMapping("/updateVoucher/{username}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable("username") String username, @RequestBody VoucherBean voucher) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            if (account.isPresent()) {
                Optional<TypeVoucher> typeVoucher = typeVoucherJPA.findById(voucher.getTvoucher());
                Voucher voucherEntity = new Voucher();
                voucherEntity.setId(voucher.getId());
                voucherEntity.setName(voucher.getName());
                voucherEntity.setStartVoucher(voucher.getStartVoucher());
                voucherEntity.setEndVoucher(voucher.getEndVoucher());
                voucherEntity.setAccount(account.get());
                voucherEntity.setNumberUse(voucher.getNumberUse());
                voucherEntity.setStatus(voucher.getStatus());
                voucherEntity.setTvoucher(typeVoucher.get());
                voucherEntity.setVoucherValue(voucher.getVoucherValue());
                return ResponseEntity.ok(voucherJPA.save(voucherEntity));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("{token}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable("token") String username, @RequestParam("id") String id) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            voucherJPA.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
