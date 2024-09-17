package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.EvaluateUserJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Evaluate;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.responeObject.EvaluateResponse;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.EvaluateSellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/evaluate")
public class EvaluateManagerController {
    private final AccountJPA accountJPA;
    private final AccountSellerJPA accountSellerJPA;
    private final EvaluateUserJPA evaluateUserJPA;
    private final JwtService jwtService;
    private final EvaluateSellerService evaluateService;

    public EvaluateManagerController(AccountJPA accountJPA, AccountSellerJPA accountSellerJPA, EvaluateUserJPA evaluateUserJPA, JwtService jwtService, EvaluateSellerService evaluateService) {
        this.accountJPA = accountJPA;
        this.accountSellerJPA = accountSellerJPA;
        this.evaluateUserJPA = evaluateUserJPA;
        this.jwtService = jwtService;
        this.evaluateService = evaluateService;
    }

    @GetMapping("/getEvaluateByShop")
    public ResponseEntity<List<EvaluateResponse>> getEvaluateByShop(@RequestHeader("Authorization") String header) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(header));
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<List<Evaluate>> list = evaluateUserJPA.getEvaluateByShopId(account.get().getStaff().getShop().getId().toString());
        List<EvaluateResponse> responseList = new ArrayList<>();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Evaluate evaluate : list.get()) {
            responseList.add(evaluateService.setResult(evaluate));
        }
        return ResponseEntity.ok(responseList);
    }
}
