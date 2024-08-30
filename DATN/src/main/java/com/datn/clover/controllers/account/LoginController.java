package com.datn.clover.controllers.account;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.services.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountSellerJPA accountSellerJPA;

    @GetMapping
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(username);

        if (account.isEmpty()) {
            return "Invalid username or password";
        }
        if (account.get().getPassword().equals(password) && account.get().getUsername().equals(username)) {
            return "success";
        }
        return "Invalid username or password";
    }
}
