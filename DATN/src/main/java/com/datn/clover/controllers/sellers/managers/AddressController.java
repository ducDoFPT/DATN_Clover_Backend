package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.AddressSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Address;
import com.datn.clover.inter.AddressJPA;
import com.datn.clover.mapper.AddressAccountmapper;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.account.AddressAcountServices;
import com.datn.clover.services.account.AccountSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/seller/address")
public class AddressController {
    @Autowired
    AddressJPA addressJPA;
    @Autowired
    private AccountSellerService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AddressAcountServices addressAcountServices;
    @Autowired
    private AddressAccountmapper addressAccountmapper;
    @Autowired
    Validator validator;
    @Autowired
    private AccountSellerJPA accountSellerJPA;

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
    //create address
    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
        AddressSellerBean addressSellerBean = addressAccountmapper.toDTO(params);
        BindingResult error = new BeanPropertyBindingResult(addressSellerBean, "addressSellerBean");
        validator.validate(addressSellerBean, error);
        if (error.hasErrors()) {
           throw  new BindException(error);
        }
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isPresent()) {
                return addressAcountServices.create(addressSellerBean,account);
            }
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    //delete address
    @DeleteMapping("/deleteAddres")
    public ResponseEntity<Void> deleteAddress(@RequestParam("id") String id, @RequestHeader("Authorization") String token) throws BindException {
        try {
            Address address = addressJPA.findById(id).orElse(null);
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            if ( account.isEmpty() || !account.get().getId().equals(Objects.requireNonNull(address).getAccount().getId())) {
                return ResponseEntity.notFound().build();
            }
            addressJPA.deleteById(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    // update address
    @PutMapping("/updateAddress")
    public ResponseEntity<?> updateAddress(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String username ) throws BindException {
        AddressSellerBean addressSellerBean = addressAccountmapper.toDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(addressSellerBean, "addressSellerBean");
        validator.validate(addressSellerBean, errors);
        if (errors.hasErrors()) {
            throw  new BindException(errors);
        }
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(username));
            if (account.isPresent() && account.get().getId().equals(account.get().getAddresses().getAccount().getId())) {
                return addressAcountServices.update(addressSellerBean,account.get().getUsername());
            }
            return ResponseEntity.badRequest().body(errors);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(errors);
        }
    }
}
