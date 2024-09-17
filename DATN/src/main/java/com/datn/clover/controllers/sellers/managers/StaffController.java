package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.AccountCreateDTO;
import com.datn.clover.DTO.Sellers.AccountSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.ShopSellerJPA;
import com.datn.clover.JPAs.StaffSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Staff;
import com.datn.clover.mapper.AccountSellerMapperImpl;
import com.datn.clover.responeObject.AccountSellerResponse;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.account.AccountSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/seller/staff")
public class StaffController {
    @Autowired
     AccountSellerJPA accountSellerJPA;
    @Autowired
    ShopSellerJPA shopJPA;
    @Autowired
    StaffSellerJPA staffJPA;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountSellerMapperImpl accountSellerMapperImpl;
    @Autowired
    Validator validator;
    @Autowired
    private AccountSellerService accountSellerService;

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

    @GetMapping("/getStaffbyShop")
    public List<Account> getStaffbyShop(@RequestHeader("Authorization") String token) {
       try {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        if(account.isEmpty()){
            return null;
        }
        if (account.get().getId().equals(account.get().getShops().getAccount().getId())){
            Set<Staff> accounts =  account.get().getShops().getStaff();
            List<Account> staffs = new ArrayList<>();
            for (Staff staff : accounts) {
                staffs.add(staff.getAccount());
            }
            return staffs;
        }
       }catch (Exception e) {
            return null;
       }
        return null;
    }
    @PostMapping("/create")
    public ResponseEntity<Staff> create(@RequestHeader("Authorization") String token, Map<String, String> params) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            AccountCreateDTO staff0 = accountSellerMapperImpl.paramsToAccountDTO(params);
            BindingResult errors = new BeanPropertyBindingResult(staff0, "staff");
            validator.validateObject(staff0);
            if(account.isEmpty() || errors.hasErrors()){
                throw new BindException( errors);
            }
            if (account.get().getId().equals(account.get().getShops().getAccount().getId())){
                Account account1 = accountSellerService.createAccount(staff0);
                Staff staff = new Staff();
                staff.setAccount(account1);
                staff.setShop(account.get().getShops());
                return ResponseEntity.ok(staffJPA.save(staff));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            if(account.isEmpty()){
                return false;
            }
            if (account.get().getId().equals(account.get().getShops().getAccount().getId())){
                staffJPA.deleteById(id);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
