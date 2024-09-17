package com.datn.clover.services.admin;

import com.datn.clover.entity.Supplier;
import com.datn.clover.inter.SupplierJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppilerService {

    @Autowired
    private SupplierJPA supplierJPA;

    public List<Supplier> getAllSuppliers() {
        return supplierJPA.findAll();
    }
}
