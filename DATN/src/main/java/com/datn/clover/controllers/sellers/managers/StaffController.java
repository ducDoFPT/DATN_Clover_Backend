package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.ShopJPA;
import com.datn.clover.JPAs.StaffJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Shop;
import com.datn.clover.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/staff")
public class StaffController {
    @Autowired
     AccountSellerJPA accountSellerJPA;
    @Autowired
     ShopJPA shopJPA;
    @Autowired
     StaffJPA staffJPA;



    @GetMapping("/getStaffbyShop/{token}")
    public List<Account> getStaffbyShop(@PathVariable String token) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
        if(account.isEmpty()){
            return null;
        }
        if (account.get().getId().equals(account.get().getShop().getAccount().getId())){
            List<Staff> accounts = account.get().getShop().getStaff();
            List<Account> staffs = new ArrayList<>();
            for (Staff staff : accounts) {
                staffs.add(staff.getAccount());
            }
            return staffs;
        }
        return null;
    }

    @PostMapping("/create/{token}")
    public ResponseEntity<Staff> create(@PathVariable String token, @RequestParam("id") String id, @RequestParam("StaffID") String idStaff) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            Optional<Account> staffO = accountSellerJPA.findById(idStaff);
            if(account.isEmpty() || staffO.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            if (account.get().getId().equals(account.get().getShop().getAccount().getId())){
                Staff staff = new Staff();
                staff.setId(id);
                staff.setAccount(staffO.get());
                staff.setShop(account.get().getShop());
                return ResponseEntity.ok(staffJPA.save(staff));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete/{token}")
    public boolean delete(@RequestParam("id") String id, @PathVariable String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if(account.isEmpty()){
                return false;
            }
            if (account.get().getId().equals(account.get().getShop().getAccount().getId())){
                staffJPA.deleteById(id);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
