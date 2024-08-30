package com.datn.clover.controllers.user.social;

import com.datn.clover.Bean.Sellers.FriendBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.FriendJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Friend;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendJPA friendJPA;
    private final AccountSellerJPA accountSellerJPA;

    public FriendController(FriendJPA friendJPA, AccountSellerJPA accountSellerJPA) {
        this.friendJPA = friendJPA;
        this.accountSellerJPA = accountSellerJPA;
    }

    @GetMapping("/getFirendByUsername/{token}")
    public List<Friend> getFirendByUsername(@PathVariable String token) {
        return friendJPA.findByAccountId1(token);
    }

    @PostMapping("/createFriend/{token}")
    public ResponseEntity<Friend> createFriend(@RequestBody FriendBean friendBean, @PathVariable String token) {
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(token);
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(friendBean.getAccountId2());
if (account1.isPresent() && account2.isPresent()) {
    Friend friend = new Friend();
    friend.setId(friendBean.getId());
    friend.setFriendDay(LocalDate.now());
    friend.setStatus(true);
    friend.setAccountId1(account1.get());
    friend.setAccountId2(account2.get());
    return ResponseEntity.ok(friendJPA.save(friend));
}
return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteFriend/{token}")
    public ResponseEntity<?> deleteFriend(@PathVariable String token, @RequestParam("idAccount2") String idAccount2, @RequestParam("idFriend") String idFriend) {
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(token);
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(idAccount2);
        Optional<Friend> friend = friendJPA.findById(idFriend);
        if (account1.isPresent() && account2.isPresent() && friend.isPresent()) {
            friendJPA.delete(friend.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
