package com.datn.clover.controllers.account;

import com.datn.clover.Bean.Sellers.AccountSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.RoleJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Role;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final AccountSellerJPA accountSellerJPA;
    private final RoleJPA roleJPA;

    public RegisterController(AccountSellerJPA accountSellerJPA, RoleJPA roleJPA) {
        this.accountSellerJPA = accountSellerJPA;
        this.roleJPA = roleJPA;
    }

    @PostMapping("/chekUsername/{username}")
    public Boolean chekUsername(@PathVariable("username") String username) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(username);
        return account.isEmpty();
    }

    @PostMapping("/chekEmail/{email}")
    public Boolean chekEmail(@PathVariable("email") String username) {
        Optional<Account> account = accountSellerJPA.getClientByEmail(username);
        return account.isEmpty();
    }
    @PostMapping("/chekPhone/{phone}")
    public Boolean chekPhone(@PathVariable("phone") String phone) {
        Optional<Account> account = accountSellerJPA.getClientByPhone(phone);
        return account.isEmpty();
    }

    @PostMapping
    public Account addClient(@RequestBody AccountSellerBean account) {
        Account client = new Account();
        client.setFullname(account.getFullname());
        client.setPhone(account.getPhone());
        client.setEmail(account.getEmail());
        client.setUsername(account.getUsername());
        client.setPassword(account.getPassword());
        Role role = roleJPA.findByName("USER");
        client.setRole(role);
        return accountSellerJPA.save(client);

    }
}
