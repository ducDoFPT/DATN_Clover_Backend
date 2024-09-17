package com.datn.clover.services.account;

import com.datn.clover.DTO.Sellers.AccountCreateDTO;
import com.datn.clover.DTO.Sellers.AccountSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Address;
import com.datn.clover.responeObject.AccountSellerResponse;
import com.datn.clover.services.sellers.UploadSellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountSellerService {
    @Autowired
    AccountSellerJPA accountSellerJPAJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;

    //luu account
    public  Account setAccount(AccountSellerBean account, Optional<Account> accountByUsername, AccountSellerJPA accountJPA) {
        accountByUsername.get().setFullname(account.getFullname());
        accountByUsername.get().setEmail(account.getEmail());
        accountByUsername.get().setPhone(account.getPhone());
       return accountJPA.save(accountByUsername.get());
    }
    //lay account by username
    public Account getAccount(String username) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(username);
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
    public static ResponseEntity<AccountSellerResponse> checkDTO(AccountSellerBean account
            , String username
            , AccountSellerJPA accountJPA
            , AccountSellerService accountSellerService
            , MultipartFile avatar
            , UploadSellerServices uploadServices){
        try{

            Optional<Account> accountByUsername = accountJPA.getAccountByUsername(username);
            if (accountByUsername.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            List<Account> accountByPhone = accountJPA.getAllClientNotExistsByPhone(accountByUsername.get().getUsername(),account.getPhone());
            List<Account> accountByEmail = accountJPA.getAllClientNotExistsByEmail(accountByUsername.get().getUsername(), account.getEmail());
            if(!Objects.equals(accountByUsername.get().getAvatar(), avatar.getOriginalFilename())  && accountByPhone.isEmpty() && accountByEmail.isEmpty()) {
                String avatarName = uploadServices.uploadFile(avatar);
                accountByUsername.get().setAvatar(avatarName);
                Account rs = accountSellerService.setAccount(account, accountByUsername, accountJPA);
                return ResponseEntity.ok(accountSellerService.setAccountResponse(rs));
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public AccountSellerResponse setAccountSeller(Account account) {
        AccountSellerResponse acc = new AccountSellerResponse();
        acc.setFullname(account.getFullname());
        acc.setEmail(account.getEmail());
        acc.setPhone(account.getPhone());
        acc.setGender(account.getGender());
        acc.setAddresses(account.getAddresses());
        return acc;
    }
    public Account createAccount(AccountCreateDTO account) {
        Account acc = new Account();
        acc.setUsername(account.getUsername());
        acc.setPassword(account.getPassword());
        acc.setFullname(account.getFullname());
        acc.setEmail(account.getEmail());
        acc.setPhone(account.getPhone());
        acc.setGender(account.getGender());
        return accountSellerJPA.save(acc);
    }

//    public static ResponseEntity<Void> setAvatar(@RequestParam("avatar") MultipartFile avatar
//            , @PathVariable("token") String username
//            , AccountSellerJPA accountJPA
//            , UploadSellerServices uploadServices) {
//        try{
//
//                accountJPA.save(account.get());
//                return ResponseEntity.ok().build();
//            }
//            return ResponseEntity.ok().build();
//        }catch (Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
