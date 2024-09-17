package com.datn.clover.services.admin;

import com.datn.clover.entity.ShipBill;
import com.datn.clover.inter.ShipBillJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipBillService {

    @Autowired
    private ShipBillJPA shipBillJPA;

    public List<ShipBill> getAllShipBills() {
        return shipBillJPA.findAll();
    }
}
