package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.BillJSellerPA;
import com.datn.clover.JPAs.ShopJPA;
import com.datn.clover.JPAs.StaffJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Bill;
import com.datn.clover.entity.Shop;
import com.datn.clover.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/bill")
public class BillController {

    @Autowired
    BillJSellerPA billJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private ShopJPA shopJPA;
    @Autowired
    private StaffJPA staffJPA;

    // lay het hoa don
    @GetMapping("/getAll")
    public List<Bill> getAllBill() {
            return billJPA.findAll();
    }
    //lay hoa don theo shop
    @GetMapping("/getBillByShop/{token}")
    public List<Bill> getBillByShop(@PathVariable("token") String username) {
        Optional<Staff> staff = staffJPA.findByUsername(username);
        if (staff.isEmpty()) {
            return null;
        }
        Optional<Shop> shop = shopJPA.findById(staff.get().getShop().getId());
        return shop.map(value -> billJPA.getAllBillByShop(value.getId())).orElse(null);
    }


    //xac nhan hoa don
    @PutMapping("/confirmBill/{id}")
    public ResponseEntity<Bill> createBill(@PathVariable("id") String id) {
        Optional<Bill> bill = billJPA.findById(id);
        if (bill.isPresent()) {
            bill.get().setStatus("CONFIRMED");
            billJPA.save(bill.get());
            return ResponseEntity.ok(bill.get());
        }
        return ResponseEntity.notFound().build();
    }
    //huy don
    @PutMapping("/cancelBill/{id}")
    public ResponseEntity<Bill> cancelBill(@PathVariable("id") String id) {
        Optional<Bill> bill = billJPA.findById(id);
        if (bill.isPresent()) {
            bill.get().setStatus("CANCELLED");
            billJPA.save(bill.get());
            return ResponseEntity.ok(bill.get());
        }
        return ResponseEntity.notFound().build();
    }
}
