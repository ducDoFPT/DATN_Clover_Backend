package com.datn.clover.services.sellers;

import com.datn.clover.Bean.Sellers.AccountSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.responeObject.AccountSellerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountSellerService {
    @Autowired
    AccountSellerJPA accountJPA;
    @Autowired
    UploadServices uploadServices;
    //luu account
    public  Account setAccount(AccountSellerBean account, Optional<Account> accountByUsername, AccountSellerJPA accountJPA) {
        accountByUsername.get().setFullname(account.getFullname());
        accountByUsername.get().setEmail(account.getEmail());
        accountByUsername.get().setPhone(account.getPhone());
       return accountJPA.save(accountByUsername.get());
    }
    //lay account by username
    public Account getAccount(String username) {
        Optional<Account> account = accountJPA.getAccountByUsername(username);
        return account.orElse(null);
    }
    // set account de response
    public AccountSellerResponse setAccountResponse(Account account) {
        AccountSellerResponse response = new AccountSellerResponse();
        response.setFullname(account.getFullname());
        response.setEmail(account.getEmail());
        response.setPhone(account.getPhone());
        response.setGender(account.getGender());
        response.setAddresses(account.getAddresses());
        response.setAvatar(account.getAvatar());
        return response;
    }
}
