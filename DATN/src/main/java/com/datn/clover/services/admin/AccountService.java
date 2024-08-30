package com.datn.clover.services.admin;

import com.datn.clover.entity.Account;
import com.datn.clover.inter.AccountJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountJPA accountJPA;

    public List<Account> getAllAccounts() {
        return accountJPA.findAll();
    }
}
