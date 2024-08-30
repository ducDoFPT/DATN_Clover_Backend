package com.datn.clover.controllers.admin;

import com.datn.clover.entity.Bill;
import com.datn.clover.services.admin.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillControllers {
    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getBillService() {
        return billService.getAllBills();
    }
}
