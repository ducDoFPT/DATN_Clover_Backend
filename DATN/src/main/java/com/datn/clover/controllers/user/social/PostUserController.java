package com.datn.clover.controllers.user.social;

import com.datn.clover.Bean.Sellers.LikeBean;
import com.datn.clover.Bean.Sellers.ShareBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.LikeJPA;
import com.datn.clover.JPAs.PostSellerJPA;
import com.datn.clover.JPAs.ShareSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Like;
import com.datn.clover.entity.Post;
import com.datn.clover.entity.Share;
import com.datn.clover.inter.AccountJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/post")
public class PostUserController {
    @Autowired
    PostSellerJPA postJPA;
    @Autowired
    private AccountJPA accountJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    @Autowired
    private LikeJPA likeJPA;
    @Autowired
    private ShareSellerJPA shareSellerJPA;

    @GetMapping
    public List<Post> getPosts() {
        return postJPA.findAll();
    }

    @PostMapping("/likePost/{token}")
    public ResponseEntity<Like> likePost(@RequestBody LikeBean likeBean, @RequestParam("id") String id, @PathVariable String token) {
        Optional<Post> post = postJPA.findById(id);
        Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
        if (post.isEmpty() || account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Like like = new Like();
        like.setPost(post.get());
        like.setAccount(account.get());
        like.setLikeDay(LocalDate.now());
        like.setId(likeBean.getId());
        return ResponseEntity.ok(likeJPA.save(like));
    }

    @DeleteMapping("/unLikePost/{token}")
    public ResponseEntity<Like> unLikePost( @RequestParam("id") String id, @PathVariable String token) {
        try {
            Optional<Like> like = likeJPA.findById(id);
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if (like.isEmpty() || account.isEmpty() ) {
                return ResponseEntity.badRequest().build();
            }
            if (Objects.equals(account.get().getId(), like.get().getAccount().getId())) {}
            likeJPA.delete(like.get());
            return ResponseEntity.ok(like.get());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/sharePost/{token}")
    public ResponseEntity<Share> sharePost(@RequestBody ShareBean shareBean, @RequestParam("id") String id, @PathVariable String token) {
        Optional<Post> post = postJPA.findById(id);
        Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
        if (post.isEmpty() || account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Share share = new Share();
        share.setPost(post.get());
        share.setAccount(account.get());
        share.setShareDay(LocalDate.now());
        share.setId(shareBean.getId());
        return ResponseEntity.ok(shareSellerJPA.save(share));
    }

    @DeleteMapping("/unSharePost/{token}")
    public ResponseEntity<Share> unSharePost(@RequestParam("id") String id, @PathVariable String token) {
        try {
            Optional<Share> share = shareSellerJPA.findById(id);
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if (share.isEmpty() || account.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (Objects.equals(account.get().getId(), share.get().getAccount().getId())) {
                shareSellerJPA.delete(share.get());
                return ResponseEntity.ok(share.get());
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
