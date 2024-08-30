package com.datn.clover.controllers.user.shopping;

import com.datn.clover.JPAs.BillUserJPA;
import com.datn.clover.entity.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/bill")
public class BillUserController {


    private final BillUserJPA billUserJPA;

    public BillUserController(BillUserJPA billUserJPA) {
        this.billUserJPA = billUserJPA;
    }

    @GetMapping("/getBillByUsername/{token}")
    public ResponseEntity<List<Bill>> getBillByUsername(@PathVariable String token) {
        List<Bill> bills = billUserJPA.findByAccount(token);
        return ResponseEntity.ok(bills);
    }
}
