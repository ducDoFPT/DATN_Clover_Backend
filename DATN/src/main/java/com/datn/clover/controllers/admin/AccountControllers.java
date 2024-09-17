package com.datn.clover.controllers.admin;

import com.datn.clover.DTO.admin.AccountBean;
import com.datn.clover.entity.Account;
import com.datn.clover.services.admin.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountControllers {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {return accountService.getAllAccounts();}

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountBean accountBean, BindingResult bindingResult) throws BindException { //ResponseEntity đại diện HTTP phản hồi

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }else {
            try {
                Account account = new Account();
                return ResponseEntity.ok(accountService.createAccount(account,accountBean));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable String id, @RequestBody @Valid AccountBean accountBean, BindingResult bindingResult) throws BindException {

        Account account = accountService.getAccountById(id);
        if (account == null) {
            return ResponseEntity.badRequest().build();
        }else{
            if (bindingResult.hasErrors()) {
                throw new BindException(bindingResult);
            }else {
                return ResponseEntity.ok(accountService.updateAccount(id, accountBean));
            }
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Account> deleteAccount(@PathVariable String id) {
        try {
            Account account = accountService.getAccountById(id);
            if (account == null) {
                return ResponseEntity.notFound().build();
            }else{
                accountService.deleteAccount(id);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
