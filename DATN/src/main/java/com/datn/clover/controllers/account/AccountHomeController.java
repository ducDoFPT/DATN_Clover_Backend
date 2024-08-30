package com.datn.clover.controllers.account;

import com.datn.clover.Bean.Sellers.AccountSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.AddressSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.responeObject.AccountSellerResponse;
import com.datn.clover.services.sellers.AccountSellerService;
import com.datn.clover.services.sellers.UploadServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/home/account")
public class AccountHomeController {
    @Autowired
    AccountSellerJPA accountJPA;
    @Autowired
    private AccountSellerService accountService;
    @Autowired
    private UploadServices uploadServices;
    @Autowired
    private AddressSellerJPA addressJPA;
    @Autowired
    private AccountSellerService accountSellerService;
//    @Autowired
//    JwtService jwtService;

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException be) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Request không hợp lệ";
        if (be.getBindingResult().hasErrors()) {
            errorMessage = be.getBindingResult().getAllErrors().getLast().getDefaultMessage();
        }
        return errorMessage;
    }
    //lay thong tin bang username
    @GetMapping("/{username}")
    public ResponseEntity<Account> getAccount(@PathVariable String username) {
        try {
            Account account = accountService.getAccount(username);
            return ResponseEntity.ok(account);
        }catch (NullPointerException e) {
           return ResponseEntity.notFound().build();
        }
    }
    // update thong tin account
    @PutMapping("/updateInfor/{token}")
    public ResponseEntity<AccountSellerResponse> updateAccount(@RequestBody @Valid AccountSellerBean account, BindingResult error, @PathVariable("token") String username ) throws BindException {
        if (error.hasErrors()) {
            throw new BindException(error);
        }
        if (!account.getId().equals(username)) {
            throw new BindException(error);
        }
        Optional<Account> accountByUsername = accountJPA.getAccountByUsername(username);
        if (accountByUsername.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try{
            Account rs = accountSellerService.setAccount(account, accountByUsername, accountJPA);
            return ResponseEntity.ok( accountService.setAccountResponse(rs));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
// update ảnh avatar account
    @PutMapping("/updateAvatar/{token}")
    public ResponseEntity<Void> updateAvatar(@RequestPart("avatar") MultipartFile avatar,@PathVariable("token") String username ) throws BindException {
        try{
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            if(account.isPresent() && !Objects.equals(account.get().getAvatar(), avatar.getOriginalFilename())){
                String avatarName = uploadServices.uploadFile(avatar);
                account.get().setAvatar(avatarName);
                accountJPA.save(account.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
