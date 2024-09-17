package com.datn.clover.controllers.user.shopping;

import com.datn.clover.DTO.Sellers.EvaluateBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.BillJSellerPA;
import com.datn.clover.JPAs.EvaluateUserJPA;
import com.datn.clover.JPAs.ProductSellerJPA;
import com.datn.clover.entity.*;
import com.datn.clover.mapper.ProductSellerMapper;
import com.datn.clover.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/evaluate")
public class EvaluateUserController {
    @Autowired
    AccountSellerJPA accountSellerJPA;

    @Autowired
    ProductSellerJPA productSellerJPA;
    @Autowired
    private EvaluateUserJPA evaluateJPA;
    @Autowired
    private JwtService jwtService;
    @Autowired
    ProductSellerMapper productSellerMapper;
    @Autowired
    Validator validator;
    @Autowired
    private BillJSellerPA billJSellerPA;

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

    @GetMapping("/getEvaluateByUsername")
    public ResponseEntity<List<Evaluate>> getEvaluateByUsername(@RequestHeader("Authorization") String token) {
        Optional<List<Evaluate>> result = evaluateJPA.getEvaluateByUsername(jwtService.accessToken(token));
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getEvaluateByUsernameNonEvaluate")
    public ResponseEntity<List<?>> getEvaluateByUsernameNonEvaluate(@RequestHeader("Authorization") String token) {
        List<Bill> bills = billJSellerPA.getAllBillByAccount(jwtService.accessToken(token));
        Optional<List<Evaluate>> evaluates = evaluateJPA.getEvaluateByUsername(jwtService.accessToken(token));
        List<Product> product = new ArrayList<>();
        for (Bill bill : bills) {
            for (DetailBill d : bill.getDetailBills()) {
                product.add(d.getProd());
            }
        }
//        if (evaluates.isPresent() && !product.isEmpty()){
//        for (Evaluate evaluate : evaluates.get()) {
//
//        }
//    }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Evaluate> addEvaluate(@RequestParam Map<String , String> params, @RequestHeader("Authorization") String token) throws BindException {
        EvaluateBean evaluateBean = productSellerMapper.evaluateToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(evaluateBean, "evaluate");
        validator.validateObject(evaluateBean);
        if (error.hasErrors()) {
           throw new BindException( error);
       }
        try {
           Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
           Optional<Product> product = productSellerJPA.findById(evaluateBean.getProdID());
           if (account.isEmpty() || product.isEmpty()) {
               return ResponseEntity.notFound().build();
           }
           Evaluate evaluate = new Evaluate();
           evaluate.setContent(evaluateBean.getContent());
           evaluate.setEvaluateDay(LocalDate.now());
           evaluate.setStarCount(evaluateBean.getStarCount());
           evaluate.setProd(product.get());
           evaluate.setAcc(account.get());
           Evaluate a = evaluateJPA.save(evaluate);
           return ResponseEntity.ok(a);
       }catch (Exception e){
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/update")
    public ResponseEntity<Evaluate> updateEvaluate(@RequestHeader("Authorization") String token, @RequestParam Map<String , String> params) throws BindException {
        EvaluateBean evaluateBean = productSellerMapper.evaluateToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(evaluateBean, "evaluate");
        validator.validateObject(evaluateBean);
        if (error.hasErrors()) {
            throw new BindException( error);
        }
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Evaluate> evaluate = evaluateJPA.findById(evaluateBean.getId());
            if (account.isEmpty() || evaluate.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            evaluate.get().setContent(evaluateBean.getContent());
            evaluate.get().setEvaluateDay(LocalDate.now());
            evaluate.get().setStarCount(evaluateBean.getStarCount());
            Evaluate a = evaluateJPA.save(evaluate.get());
            return ResponseEntity.ok(a);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEvaluate(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Evaluate> evaluate = evaluateJPA.findById(id);
        if (account.isEmpty() || evaluate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            evaluateJPA.deleteById(evaluate.get().getId().toString());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
