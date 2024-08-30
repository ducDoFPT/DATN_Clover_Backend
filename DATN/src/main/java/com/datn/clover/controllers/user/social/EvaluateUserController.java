package com.datn.clover.controllers.user.social;

import com.datn.clover.Bean.Sellers.EvaluateBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.EvaluateJPA;
import com.datn.clover.JPAs.ProductSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Evaluate;
import com.datn.clover.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluate")
public class EvaluateUserController {
    @Autowired
    AccountSellerJPA accountSellerJPA;

    @Autowired
    ProductSellerJPA productSellerJPA;
    @Autowired
    private EvaluateJPA evaluateJPA;

    @GetMapping("/getEvaluateByUsername/{token}")
    public ResponseEntity<List<Evaluate>> getEvaluateByUsername(@PathVariable String token) {
        Optional<List<Evaluate>> result = evaluateJPA.getEvaluateByUsername(token);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createEvaluate/{token}")
    public ResponseEntity<Evaluate> addEvaluate(@RequestBody EvaluateBean evaluateBean, @PathVariable String token) {
       try {
           Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
           Optional<Product> product = productSellerJPA.findById(evaluateBean.getProdID());
           if (account.isEmpty() || product.isEmpty()) {
               return ResponseEntity.notFound().build();
           }
           Evaluate evaluate = new Evaluate();
           evaluate.setId(evaluateBean.getId());
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

    @PutMapping("/updateEvaluate/{token}")
    public ResponseEntity<Evaluate> updateEvaluate(@PathVariable String token, @RequestBody EvaluateBean evaluateBean) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
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


    @DeleteMapping("/deleteEvaluate/{token}")
    public ResponseEntity<?> deleteEvaluate(@RequestParam("id") String id, @PathVariable String token) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
        Optional<Evaluate> evaluate = evaluateJPA.findById(id);
        if (account.isEmpty() || evaluate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            evaluateJPA.deleteById(evaluate.get().getId());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
