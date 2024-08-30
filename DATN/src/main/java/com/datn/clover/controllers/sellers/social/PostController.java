package com.datn.clover.controllers.sellers.social;

import com.datn.clover.Bean.Sellers.LikeBean;
import com.datn.clover.Bean.Sellers.PostBean;
import com.datn.clover.Bean.Sellers.ShareBean;
import com.datn.clover.JPAs.*;
import com.datn.clover.entity.*;
//import com.datn.clover.services.sellers.JwtService;
import com.datn.clover.services.sellers.UploadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    AccountSellerJPA accountJPA;

    @Autowired
    PostSellerJPA postJPA;

//    @Autowired
//    JwtService jwtService;

    @Autowired
    UploadServices uploadServices;

    @Autowired
    PostImageJPA postImageJPA;
    @Autowired
    private LikeJPA likeJPA;
    @Autowired
    private ShareSellerJPA shareSellerJPA;

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postJPA.findAll();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<Post>> getPostByToken(@PathVariable("username") String token) {
        Optional<List<Post>> postByUsername = postJPA.findByUsername(token);
        return postByUsername.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/createPost/{token}")
    public ResponseEntity<Post> createPost(@RequestBody PostBean post, @PathVariable("token") String token) {
        try {
        Post postCreate = new Post();
        Optional<Account> account = accountJPA.getAccountByUsername(token);
        postCreate.setId(post.getId());
        postCreate.setTitle(post.getTitle());
        postCreate.setContent(post.getContent());
        postCreate.setPostDay(LocalDateTime.now());
        postCreate.setNumberLikes(0);
        postCreate.setStatus(post.getStatus());
        postCreate.setStypeShare(post.getStypeShare());
        postCreate.setAccount(account.get());
      Post  postRS = postJPA.save(postCreate);

        return ResponseEntity.ok(postRS);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/updatePost/{token}")
    public ResponseEntity<Post> updatePost(@RequestBody PostBean post, @PathVariable("token") String token) {
        System.out.println(post.getContent());
         Optional<Post> postUpdate = postJPA.findById(post.getId());
         postUpdate.get().setTitle(post.getTitle());
         postUpdate.get().setContent(post.getContent());
        Post  postRS = postJPA.save(postUpdate.get());

        return ResponseEntity.ok(postRS);
    }
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") String id) {
        try{
            Optional<Post> post = postJPA.findById(id);
            postJPA.delete(post.get());
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/likePost/{token}")
    public ResponseEntity<Like> likePost( @RequestParam("id") String id, @PathVariable String token, @RequestParam("idLike") String idlike) {
        Optional<Post> post = postJPA.findById(id);
        Optional<Account> account = accountJPA.getAccountByUsername(token);
        if (post.isEmpty() || account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Like like = new Like();
        like.setPost(post.get());
        like.setAccount(account.get());
        like.setLikeDay(LocalDate.now());
        like.setId(idlike);
        return ResponseEntity.ok(likeJPA.save(like));
    }

    @DeleteMapping("/unLikePost/{token}")
    public ResponseEntity<Like> unLikePost( @RequestParam("id") String id, @PathVariable String token) {
        try {
            Optional<Like> like = likeJPA.findById(id);
            Optional<Account> account = accountJPA.getAccountByUsername(token);
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
    public ResponseEntity<Share> sharePost( @RequestParam("id") String id, @PathVariable String token, @RequestParam("idShare") String idShare) {
        Optional<Post> post = postJPA.findById(id);
        Optional<Account> account = accountJPA.getAccountByUsername(token);
        if (post.isEmpty() || account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Share share = new Share();
        share.setPost(post.get());
        share.setAccount(account.get());
        share.setShareDay(LocalDate.now());
        share.setId(idShare);
        return ResponseEntity.ok(shareSellerJPA.save(share));
    }

    @DeleteMapping("/unSharePost/{token}")
    public ResponseEntity<Share> unSharePost(@RequestParam("id") String id, @PathVariable String token) {
       try {
           Optional<Share> share = shareSellerJPA.findById(id);
           Optional<Account> account = accountJPA.getAccountByUsername(token);
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
