package com.datn.clover.services.admin;

import com.datn.clover.entity.Bill;
import com.datn.clover.inter.BillJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillJPA billJPA;
    public List<Bill> getAllBills() {
        return billJPA.findAll();
    }
}
