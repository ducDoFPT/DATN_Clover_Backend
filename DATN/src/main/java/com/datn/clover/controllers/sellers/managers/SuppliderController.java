package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.AccountSellerBean;
import com.datn.clover.DTO.Sellers.SupplierBean;
import com.datn.clover.JPAs.SuppliderSellerJPA;
import com.datn.clover.entity.Supplier;
import com.datn.clover.mapper.SupllierMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppilder")
public class SuppliderController {
    @Autowired
    SuppliderSellerJPA suppliderJPA;
    @Autowired
    private SupllierMapper supllierMapper;
    @Autowired
    Validator validator;

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(BindingResult be) {
        // Trả về message của lỗi đầu tiên
        Map<String, String> errors = new HashMap<>();
        String errorMessage = "Request không hợp lệ";
        errors.put("error", errorMessage);
        if (be.hasErrors()) {
            errorMessage = be.getAllErrors().getFirst().getDefaultMessage();
            errors.put("message", errorMessage);
        }
        return errors;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return suppliderJPA.findAll();
    }
    @PostMapping("/create")
    public ResponseEntity<Supplier> createSupplier(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String Token) throws BindException {
        SupplierBean supplier = supllierMapper.supllierToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(supplier, "supplier");
        validator.validateObject(supplier);
       if (errors.hasErrors()) {
           throw  new BindException(errors);
       }
        try {
            Supplier sup = new Supplier();
            sup.setName(supplier.getName());
            sup.setAddress(supplier.getAddress());
            sup.setPhone(supplier.getPhone());
            return ResponseEntity.ok(suppliderJPA.save(sup));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Supplier> updateSupplier(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
        SupplierBean supplier = supllierMapper.supllierToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(supplier, "supplier");
        validator.validateObject(supplier);
        if (errors.hasErrors()) {
            throw  new BindException(errors);
        }
        try {
            Supplier sup = suppliderJPA.findById(supplier.getId()).orElse(null);
            assert sup != null;
            sup.setName(supplier.getName());
            sup.setAddress(supplier.getAddress());
            sup.setPhone(supplier.getPhone());
            return ResponseEntity.ok(suppliderJPA.save(sup));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteSupplier(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        try {
         suppliderJPA.deleteById(id);
         return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
