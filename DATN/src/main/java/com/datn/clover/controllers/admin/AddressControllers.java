package com.datn.clover.controllers.admin;

import com.datn.clover.DTO.admin.AddressBean;
import com.datn.clover.entity.Address;
import com.datn.clover.services.admin.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressControllers {
    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable String id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody @Valid AddressBean addressBean, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }else {
            try {
                return ResponseEntity.ok(addressService.createAddress(addressBean));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable String id, @RequestBody @Valid AddressBean addressBean, BindingResult bindingResult) throws BindException {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.badRequest().build();
        }else{
            if (bindingResult.hasErrors()) {
                throw new BindException(bindingResult);
            }else {
                return ResponseEntity.ok(addressService.updateAddress(id,addressBean));
            }
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Address> deleteAddress(@PathVariable String id) {
        try {
            Address address = addressService.getAddressById(id);
            if (address == null) {
                return ResponseEntity.notFound().build();
            }else{
                addressService.deleteAddress(id);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
