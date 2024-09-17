package com.datn.clover.controllers.social;

import com.datn.clover.DTO.Sellers.FriendBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.FriendUserJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Friend;
import com.datn.clover.mapper.AccountSellerMapperImpl;
import com.datn.clover.responeObject.FriendResponse;
import com.datn.clover.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendUserJPA friendJPA;
    private final AccountSellerJPA accountSellerJPA;
    private final JwtService jwtService;
    private final AccountSellerMapperImpl accountSellerMapperImpl;

    public FriendController(FriendUserJPA friendJPA, AccountSellerJPA accountSellerJPA, JwtService jwtService, AccountSellerMapperImpl accountSellerMapperImpl) {
        this.friendJPA = friendJPA;
        this.accountSellerJPA = accountSellerJPA;
        this.jwtService = jwtService;
        this.accountSellerMapperImpl = accountSellerMapperImpl;
    }

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

    @GetMapping("/getFirendByUsername")
    public List<Friend> getFirendByUsername(@RequestHeader("Authorization") String token) {
        return friendJPA.findByAccountId1(jwtService.accessToken(token));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFriend(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
        FriendBean friendBean = accountSellerMapperImpl.friendToDTO(params);
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(friendBean.getAccountId2());
if (account1.isPresent() && account2.isPresent()) {
    Friend friend = new Friend();
    friend.setId(friendBean.getId());
    friend.setFriendDay(LocalDate.now());
    friend.setStatus(false);
    friend.setAccountId1(account1.get());
    friend.setAccountId2(account2.get());
    return ResponseEntity.ok(friendJPA.save(friend));
}
return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFriend(@RequestHeader("Authorization") String token, @RequestParam("idAccount2") String idAccount2, @RequestParam("idFriend") String idFriend) {
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(jwtService.extractUsername(token));
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(idAccount2);
        Optional<Friend> friend = friendJPA.findById(idFriend);
        if (account1.isPresent() && account2.isPresent() && friend.isPresent() && (account1.get().getId().equals(friend.get().getAccountId1().getId()) || account1.get().getId().equals(friend.get().getAccountId2().getId())) ) {
            friendJPA.delete(friend.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/acecptFriendd")
    public ResponseEntity<FriendResponse> aceptFriendd(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws BindException {
        Optional<Friend> friend = friendJPA.findById(id);
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        if (friend.isPresent()&& account.isPresent() && account.get().getId().equals(friend.get().getAccountId2().getId()) ) {
            friend.get().setStatus(true);
            Friend rs =friendJPA.save(friend.get());
            FriendResponse friendResponse = new FriendResponse();
            friendResponse.setId(rs.getId());
            friendResponse.setStatus(rs.getStatus());
            return ResponseEntity.ok(friendResponse);
        }
        return ResponseEntity.badRequest().build();
    }

}
