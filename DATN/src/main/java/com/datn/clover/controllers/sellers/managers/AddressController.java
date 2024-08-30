package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.AddressBean;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Address;
import com.datn.clover.inter.AddressJPA;
import com.datn.clover.services.sellers.AccountSellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/seller/address")
public class AddressController {
    @Autowired
    AddressJPA addressJPA;
    @Autowired
    private AccountSellerService accountService;

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException be) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Request không hợp lệ";
        if (be.getBindingResult().hasErrors()) {
            errorMessage = be.getBindingResult().getAllErrors().getLast().getDefaultMessage();
        }
        return errorMessage;
    }

    //create address
    @PostMapping("/addAddress/{token}")
    public Address addAddress(@RequestBody @Valid AddressBean addressBean, BindingResult error, @PathVariable("token") String username) throws BindException {
        if (error.hasErrors()) {
            throw new BindException(error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        try {
            Account account = accountService.getAccount(username);
            Address address  = new Address();
            address.setId(addressBean.getId());
            address.setCity(addressBean.getCity());
            address.setDistrict(addressBean.getDistrict());
            address.setNation(addressBean.getNation());
            address.setProvince(addressBean.getProvince());
            address.setWards(addressBean.getWards());
            address.setStreetnameNumber(addressBean.getStreetnameNumber());
            address.setAccount(account);
            return addressJPA.save(address);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fail to create!", e);
        }
    }
    //delete address
    @DeleteMapping("/deleteAddres/{token}")
    public ResponseEntity<Void> deleteAddress(@RequestParam("id") String id, @PathVariable("token") String username) throws BindException {

        try {
            Account account = accountService.getAccount(username);
            if (account == null) {
                return ResponseEntity.notFound().build();
            }
            addressJPA.deleteById(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    // update address
    @PutMapping("/updateAddress/{token}")
    public ResponseEntity<Address> updateAddress(@RequestBody @Valid AddressBean addressBean,BindingResult rs, @PathVariable("token") String username ) throws BindException {
        if (rs.hasErrors()) {
            throw new BindException(rs);
        }
        try {
            Account account = accountService.getAccount(username);
            if (account == null) {
                return ResponseEntity.notFound().build();
            }
            Optional<Address> address  = addressJPA.findById(addressBean.getId());

            if (address.isPresent()) {
                address.get().setId(addressBean.getId());
                address.get().setCity(addressBean.getCity());
                address.get().setDistrict(addressBean.getDistrict());
                address.get().setNation(addressBean.getNation());
                address.get().setProvince(addressBean.getProvince());
                address.get().setWards(addressBean.getWards());
                address.get().setStreetnameNumber(addressBean.getStreetnameNumber());
                address.get().setAccount(account);
                return ResponseEntity.ok(addressJPA.save(address.get()));
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
