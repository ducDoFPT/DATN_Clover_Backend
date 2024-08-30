package com.datn.clover.controllers.admin;

import com.datn.clover.entity.Account;
import com.datn.clover.services.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountControllers {
    @Autowired
    private AccountService accountService;;

    @GetMapping
    public List<Account> getAllAccounts() {return accountService.getAllAccounts();}
}
