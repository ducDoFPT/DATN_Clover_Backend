package com.datn.clover.services.admin;

import com.datn.clover.DTO.admin.AccountBean;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Role;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.inter.RoleJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountJPA accountJPA;

    @Autowired
    private RoleJPA roleJPA;

//    @Autowired
//    private PassWordEncoder

    public List<Account> getAllAccounts() {
        return accountJPA.findAll();
    }

    public Account getAccountById(String id) {
        return accountJPA.findById(id).orElse(null);
    }

    public Account createAccount(Account account, AccountBean accountBean) {
        account.setUsername(accountBean.getUsername());
        account.setPassword(accountBean.getPassword());
        account.setFullname(accountBean.getFullname());
        account.setGender(accountBean.getGender());
        account.setPhone(accountBean.getPhone());
        account.setEmail(accountBean.getEmail());
        account.setAvatar(accountBean.getAvatar());

        Role role = roleJPA.findById(accountBean.getRoleId()).orElse(null);
        if (role != null) {
            account.setRole(role);
        }

        return accountJPA.save(account);
    }

    public Account updateAccount(String id, AccountBean accountBean) {
        Optional<Account> accountOptional = accountJPA.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setUsername(accountBean.getUsername());
            account.setPassword(accountBean.getPassword());
            account.setFullname(accountBean.getFullname());
            account.setGender(accountBean.getGender());
            account.setPhone(accountBean.getPhone());
            account.setEmail(accountBean.getEmail());
            account.setAvatar(accountBean.getAvatar());

            Role role = roleJPA.findById(accountBean.getRoleId()).orElse(null);
            if (role != null) {
                account.setRole(role);
            }

            return accountJPA.save(account);
        }
        return null;
    }
    public Account deleteAccount(String id) {
        Account account = accountJPA.findById(id).orElse(null);
        accountJPA.delete(account);
        return account;
    }

}
