package com.datn.clover.controllers.admin;

import com.datn.clover.entity.Address;
import com.datn.clover.services.admin.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressControllers {
    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAll() {
        return addressService.getAllAddress();
    }
}
