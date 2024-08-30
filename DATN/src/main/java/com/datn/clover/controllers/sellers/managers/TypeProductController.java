package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.TypeProductBean;
import com.datn.clover.JPAs.TypeProductJPA;
import com.datn.clover.entity.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeproduct")
public class TypeProductController {
    @Autowired
    TypeProductJPA typeProductJPA;

    @GetMapping
    public List<TypeProduct> getAllTypeProducts() {
        return typeProductJPA.findAll();
    }

    @PostMapping("/creatTypeProduct")
    public ResponseEntity<TypeProduct> createTypeProduct(@RequestBody TypeProductBean typeProduct) {
        TypeProduct typeProduct1 = new TypeProduct();
        typeProduct1.setName(typeProduct.getName());
        typeProduct1.setId(typeProduct.getId());
        return ResponseEntity.ok(typeProductJPA.save(typeProduct1));
    }

    @PutMapping("/updateTypeProduct")
    public ResponseEntity<TypeProduct> updateTypeProduct(@RequestBody TypeProduct typeProduct) {
        return ResponseEntity.ok(typeProductJPA.save(typeProduct));
    }
    @DeleteMapping("/deleteTypeProduct/{id}")
    public boolean deleteTypeProduct(@PathVariable("id") String id) {
        try {
            typeProductJPA.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
