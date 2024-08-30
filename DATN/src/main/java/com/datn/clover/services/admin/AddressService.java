package com.datn.clover.services.admin;

import com.datn.clover.entity.Address;
import com.datn.clover.inter.AddressJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressJPA addressJPA;

    public List<Address> getAllAddress() {return addressJPA.findAll();}
}
