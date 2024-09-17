package com.datn.clover.services.admin;

import com.datn.clover.entity.DetailBill;
import com.datn.clover.inter.DetaillBillJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailBillService {

    @Autowired
    private DetaillBillJPA detaillBillJPA;

    public List<DetailBill> getAllDetailBills() {
        return detaillBillJPA.findAll();
    }
}
