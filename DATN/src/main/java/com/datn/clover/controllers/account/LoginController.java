package com.datn.clover.controllers.account;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.account.AccountSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AccountSellerService accountService;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(username);

        if (account.isEmpty()) {
            Map<String, String> jwtToken = new HashMap<>();
            jwtToken.put("error", "Invalid username or password");
           return jwtToken;
        }
        if (account.get().getPassword().equals(password) && account.get().getUsername().equals(username)) {
            Map<String, String> jwtToken = new HashMap<>();
            jwtToken.put("status","success");
            jwtToken.put("access_token", jwtService.generateToken(account.get().getUsername()));
            return jwtToken;
        }
        Map<String, String> jwtToken = new HashMap<>();
        jwtToken.put("status", "fail");
        jwtToken.put("error", "Invalid username or password");
        return jwtToken;
    }
}
