package com.datn.clover.controllers.user.social;

import com.datn.clover.Bean.Sellers.FollowBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.FollowJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Follow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowJPA followJPA;
    private final AccountSellerJPA accountSellerJPA;

    public FollowController(FollowJPA followJPA, AccountSellerJPA accountSellerJPA) {
        this.followJPA = followJPA;
        this.accountSellerJPA = accountSellerJPA;
    }

    @GetMapping("/getFollowByUsername/{token}")
    public List<Follow> getFollowByUsername(@PathVariable String token) {
        return followJPA.findByAccountId1(token);
    }

        @PostMapping("/createFollow/{token}")
    public ResponseEntity<Follow> createFollow(@RequestBody FollowBean followBean, @PathVariable String token) {
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(token);
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(followBean.getAccountId2());
        if (account1.isPresent() && account2.isPresent()) {
            Follow follow = new Follow();
            follow.setId(followBean.getId());
            follow.setFollowDay(LocalDate.now());
            follow.setStatus(true);
            follow.setAccountId1(account1.get());
            follow.setAccountId2(account2.get());
            return ResponseEntity.ok(followJPA.save(follow));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteFollow/{token}")
    public ResponseEntity<?> deleteFriend(@PathVariable String token, @RequestParam("idAccount2") String idAccount2, @RequestParam("idFollow") String idFriend) {
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(token);
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(idAccount2);
        Optional<Follow> follow = followJPA.findById(idFriend);
        if (account1.isPresent() && account2.isPresent() && follow.isPresent()) {
            followJPA.delete(follow.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
