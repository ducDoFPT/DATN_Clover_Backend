package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.TypeProductBean;
import com.datn.clover.JPAs.TypeProductSellerJPA;
import com.datn.clover.entity.TypeProduct;
import com.datn.clover.mapper.ProductSellerMapperImpl;
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
@RequestMapping("/api/typeproduct")
public class TypeProductController {
    @Autowired
    TypeProductSellerJPA typeProductJPA;
    @Autowired
    private ProductSellerMapperImpl productSellerMapperImpl;
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
    public List<TypeProduct> getAllTypeProducts() {
        return typeProductJPA.findAll();
    }

    @PostMapping("/creatTypeProduct")
    public ResponseEntity<?> createTypeProduct(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
       TypeProductBean typeProductBean = productSellerMapperImpl.typeToDTO(params);
       BindingResult error = new BeanPropertyBindingResult(typeProductBean, "typeProduct");
         validator.validateObject(typeProductBean);
        if (error.hasErrors()) {
            throw new BindException( error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        try {
            TypeProduct typeProduct1 = new TypeProduct();
            typeProduct1.setName(typeProductBean.getName());
            return ResponseEntity.ok(typeProductJPA.save(typeProduct1));
        }catch (Exception e){
            throw new BindException((BindingResult) error);
        }


    }

    @PutMapping("/updateTypeProduct")
    public ResponseEntity<TypeProduct> updateTypeProduct(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
        TypeProductBean typeProduct = productSellerMapperImpl.typeToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(typeProduct, "typeProduct");
        validator.validateObject(typeProduct);
        if (error.hasErrors()) {
            throw new BindException( error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        TypeProduct typeProduct1 = typeProductJPA.findById(typeProduct.getId()).get();
        typeProduct1.setName(typeProduct.getName());
        return ResponseEntity.ok(typeProductJPA.save(typeProduct1));
    }
    @DeleteMapping("/deleteTypeProduct")
    public boolean deleteTypeProduct(@RequestParam("id") String id) {
        try {
            typeProductJPA.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
