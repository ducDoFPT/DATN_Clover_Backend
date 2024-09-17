package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.PropertivalueDTO;
import com.datn.clover.DTO.Sellers.PropertyDTO;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.PropertiValueSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.PropertiesValue;
import com.datn.clover.entity.Property;
import com.datn.clover.inter.PropertyJPA;
import com.datn.clover.mapper.ProductSellerMapper;
import com.datn.clover.mapper.ProductSellerMapperImpl;
import com.datn.clover.mapper.PromotionMapper;
import com.datn.clover.mapper.PromotionMapperImpl;
import com.datn.clover.responeObject.PropertiValueSellerResponse;
import com.datn.clover.responeObject.PropertyResponse;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.PropertiValueSellerService;
import com.datn.clover.services.sellers.PropertyService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/productAttribute")
public class ProductAttributeController {

    private final ProductSellerMapper productSellerMapper;
    private final Validator validator;
    private final PropertyService propertyService;
    private final AccountSellerJPA accountSellerJPA;
    private final JwtService jwtService;
    private final PropertyJPA propertyJPA;
    private final PromotionMapper promotionMapper;
    private final PropertiValueSellerService propertiValueSellerService;
    private final PropertiValueSellerJPA propertiValueSellerJPA;


    public ProductAttributeController(ProductSellerMapper productSellerMapper, Validator validator, PropertyService propertyService, AccountSellerJPA accountSellerJPA, JwtService jwtService, PropertyJPA propertyJPA, PromotionMapper promotionMapper, PropertiValueSellerService propertiValueSellerService, PropertiValueSellerJPA propertiValueSellerJPA) {
        this.productSellerMapper = productSellerMapper;
        this.validator = validator;
        this.propertyService = propertyService;
        this.accountSellerJPA = accountSellerJPA;
        this.jwtService = jwtService;
        this.propertyJPA = propertyJPA;
        this.promotionMapper = promotionMapper;
        this.propertiValueSellerService = propertiValueSellerService;
        this.propertiValueSellerJPA = propertiValueSellerJPA;
    }
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

    @PostMapping("/createProperty")
    public ResponseEntity<PropertyResponse> createProbpaties(@RequestParam Map<String, String> requestBody) throws  BindException {
        PropertyDTO propertyDTO = productSellerMapper.propertyToDTO(requestBody);
        BindingResult bindingResult = new BeanPropertyBindingResult(propertyDTO, "productAttribute");
        validator.validate(propertyDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        try {
            PropertyResponse response = propertyService.create(propertyDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            throw new BindException(bindingResult, "productAttribute");
        }

    }
    @PutMapping("/updateProperty")
    public ResponseEntity<PropertyResponse> updateProbpaties(@RequestParam Map<String, String> requestBody) throws  BindException {
        PropertyDTO propertyDTO = productSellerMapper.propertyToDTO(requestBody);
        BindingResult bindingResult = new BeanPropertyBindingResult(propertyDTO, "productAttribute");
        validator.validate(propertyDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        try {
            PropertyResponse response = propertyService.update(propertyDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            throw new BindException(bindingResult, "productAttribute");
        }
    }
    @DeleteMapping("/deleteProperty")
    public ResponseEntity<PropertyResponse> deleteProperty(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws  BindException {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Property> property = propertyJPA.findById(id);
            if (account.isPresent() && property.isPresent()) {
                propertyJPA.deleteById(id);
                PropertyResponse response = new PropertyResponse();
                response.setId(property.get().getId());
                response.setName(property.get().getName());
                response.setDescription(property.get().getDescription());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @PostMapping("/createPropertiValue")
    public  ResponseEntity<PropertiValueSellerResponse> createPropertiValue(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) throws BindException {
        PropertivalueDTO propertivalueDTO = productSellerMapper.propertiValueSellerToDTO(params);
        BindingResult bindingResult = new BeanPropertyBindingResult(propertivalueDTO, "productAttribute");
        validator.validate(propertivalueDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        try {
            PropertiValueSellerResponse response = propertiValueSellerService.create(propertivalueDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new BindException(bindingResult, "productAttribute");
        }
    }

    @DeleteMapping("/deletePropertyValue")
    public ResponseEntity<PropertiValueSellerResponse> deletePropertyValue(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws  BindException {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<PropertiesValue> propertiesValue = propertiValueSellerJPA.findById(id);
        if (account.isPresent() && propertiesValue.isPresent()) {
            propertiValueSellerJPA.deleteById(id);
            PropertiValueSellerResponse response = new PropertiValueSellerResponse();
            response.setId(propertiesValue.get().getId());
            response.setName(propertiesValue.get().getName());
            response.setDescription(propertiesValue.get().getDescription());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }


}
