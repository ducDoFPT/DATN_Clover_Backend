package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.SupplierBean;
import com.datn.clover.JPAs.SuppliderJPA;
import com.datn.clover.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppilder")
public class SuppliderController {
    @Autowired
    SuppliderJPA suppliderJPA;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return suppliderJPA.findAll();
    }

    @PostMapping("/createSupplier/{Token}")
    public ResponseEntity<Supplier> createSupplier(@RequestBody SupplierBean supplier, @PathVariable String Token) {
        try {
            Supplier sup = new Supplier();
            sup.setId(supplier.getId());
            sup.setName(supplier.getName());
            sup.setAddress(supplier.getAddress());
            sup.setPhone(supplier.getPhone());
            return ResponseEntity.ok(suppliderJPA.save(sup));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/updateSupplier/{token}")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody SupplierBean supplier, @PathVariable String token) {
        try {
            Supplier sup = suppliderJPA.findById(supplier.getId()).orElse(null);
            assert sup != null;
            sup.setId(supplier.getId());
            sup.setName(supplier.getName());
            sup.setAddress(supplier.getAddress());
            sup.setPhone(supplier.getPhone());
            return ResponseEntity.ok(suppliderJPA.save(sup));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<Void> deleteSupplier(@RequestParam("id") String id, @PathVariable String token) {
        try {
         suppliderJPA.deleteById(id);
         return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
