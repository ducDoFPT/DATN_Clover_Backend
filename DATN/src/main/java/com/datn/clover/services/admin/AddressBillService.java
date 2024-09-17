package com.datn.clover.services.admin;

import com.datn.clover.entity.AddressBill;
import com.datn.clover.inter.AddressBillJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBillService {
    @Autowired
    AddressBillJPA addressBillJPA;

    public List<AddressBill> getAllAddressBill() {return addressBillJPA.findAll();}
}
