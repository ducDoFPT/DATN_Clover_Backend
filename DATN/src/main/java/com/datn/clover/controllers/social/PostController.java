package com.datn.clover.controllers.social;

import com.datn.clover.DTO.Sellers.PostSellerBean;
import com.datn.clover.JPAs.*;
import com.datn.clover.entity.*;
//import com.datn.clover.services.sellers.JwtService;
import com.datn.clover.mapper.Postmapper;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.PostSellerService;
import com.datn.clover.services.sellers.UploadSellerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    UploadSellerServices uploadServices;

    @Autowired
    PostImageSellerJPA postImageJPA;
    @Autowired
    private LikeUserJPA likeJPA;
    @Autowired
    private ShareSellerJPA shareSellerJPA;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    @Autowired
    private Postmapper postmapper;
    @Autowired
    Validator validator;
    @Autowired
    private PostStatusJPA postStatusJPA;
    @Autowired
    private PostSellerService postSellerService;

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(Errors be) {
        // Trả về message của lỗi đầu tiên
        Map<String, String> errors = new HashMap<>();
        String errorMessage = "Request không hợp lệ";
        errors.put("error", errorMessage);
        if (be.hasErrors()) {
            errorMessage = be.getAllErrors().getFirst().getDefaultMessage();
            errors.put("message", errorMessage);
        }
        return errors;
    }

//    @GetMapping()
//    public ResponseEntity<List<Post>> getAllPosts() {
//        List<Post> posts = postJPA.findAll();
//        return ResponseEntity.ok(posts);
//    }
    @GetMapping
    public ResponseEntity<List<Post>> getPostByToken(@RequestHeader("Authorization") String token) {
        Optional<List<Post>> postByUsername = postJPA.findByUsername(jwtService.accessToken(token));
        return postByUsername.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
        public ResponseEntity<?> createPost(@RequestParam("files") List<MultipartFile> images,@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
            PostSellerBean post = postmapper.ToDTO(params);
            BindingResult errors = new BeanPropertyBindingResult(post, "post");
            validator.validateObject(post);
            if (errors.hasErrors()) {
                throw  new BindException( errors);
            }
            try {
            Optional<PostStatus> status = postStatusJPA.findById(1+"");
            Post postCreate = new Post();
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isEmpty() && status.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            postCreate.setTitle(post.getTitle());
            postCreate.setContent(post.getContent());
            postCreate.setPostDay(Instant.now());
            postCreate.setNumberLikes(0);
            postCreate.setStatus(status.get());
            postCreate.setStypeShare(post.getStypeShare());
            postCreate.setAccount(account.get());
          Post  postRS = postJPA.save(postCreate);
                PostImage prodImage = new PostImage();
                for (MultipartFile file : images) {
                    String fileName = uploadServices.uploadFile(file);
                    prodImage.setPost(postRS);
                    prodImage.setNameImage(fileName);
                    postImageJPA.save(prodImage);
                }
            return ResponseEntity.ok(postSellerService.setResponse(postRS));
            }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }
    @PutMapping("/update")
    public ResponseEntity<?> updatePost(@RequestParam Map<String, String> params,@RequestParam("files") List<MultipartFile> images, @RequestHeader("Authorization") String token) throws BindException {
        PostSellerBean postSellerBean = postmapper.ToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(postSellerBean, "postSellerBean");
        validator.validateObject(postSellerBean);
        if (errors.hasErrors()) {
            throw  new BindException( errors);
        }
        try {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Post> postUpdate = postJPA.findById(postSellerBean.getId());
        if (account.isPresent() && postUpdate.isPresent() && account.get().getId().equals(postUpdate.get().getAccount().getId())) {
            postUpdate.get().setTitle(postSellerBean.getTitle());
            postUpdate.get().setContent(postSellerBean.getContent());
            Post  postRS = postJPA.save(postUpdate.get());
            List<PostImage> postImages = postImageJPA.findByPostId(postRS.getId().toString());
            // Lấy danh sách tên file từ các ảnh đã có trong cơ sở dữ liệu
            List<String> existingImageNames = postImages.stream()
                    .map(PostImage::getNameImage)
                    .toList();
        PostImage postImage = new PostImage();
            // Xóa ảnh không có trong danh sách tải lên
            for (PostImage postImage1 : postImages) {
                if (images.stream().noneMatch(file -> Objects.equals(file.getOriginalFilename(), postImage1.getNameImage()))) {
                    postImageJPA.delete(postImage1); // Xóa ảnh không có trong danh sách tải lên
                }
            }
        for (MultipartFile file : images) {
            if (!existingImageNames.contains(file.getOriginalFilename())) {
                String fileName = uploadServices.uploadFile(file);
                postImage.setPost(postRS);
                postImage.setNameImage(fileName);
                postImageJPA.save(postImage);
            }
        }


            return ResponseEntity.ok(postRS);
        }
        return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/deletePost")
    public ResponseEntity<?> deletePost(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        try{
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Post> post = postJPA.findById(id);
            if(post.isPresent() && account.isPresent() && account.get().getId().equals(post.get().getAccount().getId())) {
                postJPA.deletePostImage(post.get().getId().toString());
                postJPA.delete(post.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/likePost")
    public ResponseEntity<Like> likePost( @RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        Optional<Post> post = postJPA.findById(id);
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        if (post.isEmpty() || account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        for (Like l : post.get().getLikes()){
            if(l.getAccount().getId().equals(account.get().getId())){
                likeJPA.delete(l);
                post.get().setNumberLikes(post.get().getNumberLikes() - 1);
                postJPA.save(post.get());
                return ResponseEntity.ok().build();
            }
        }
        Like like = new Like();
        like.setPost(post.get());
        like.setAccount(account.get());
        like.setLikeDay(LocalDate.now());
        post.get().setNumberLikes(post.get().getNumberLikes() + 1);
        postJPA.save(post.get());
        return ResponseEntity.ok(likeJPA.save(like));
    }

//    @DeleteMapping("/unLikePost")
//    public ResponseEntity<Like> unLikePost( @RequestParam("id") String id, @RequestHeader("Authorization") String token) {
//        try {
//            Optional<Like> like = likeJPA.findById(id);
//            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
//        if (like.isEmpty() || account.isEmpty() ) {
//            return ResponseEntity.badRequest().build();
//        }
//        if (Objects.equals(account.get().getId(), like.get().getAccount().getId())) {}
//            likeJPA.delete(like.get());
//        return ResponseEntity.ok(like.get());
//        }
//        catch(Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/sharePost")
    public ResponseEntity<Share> sharePost( @RequestParam("id") String id, @RequestParam("receiver") String receiver, @RequestHeader("Authorization") String token) {
        Optional<Post> post = postJPA.findById(id);
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        if (post.isEmpty() || account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Share share = new Share();
        share.setPost(post.get());
        share.setAccount(account.get());
        share.setShareDay(LocalDate.now());
        share.setReceiver(receiver);
        return ResponseEntity.ok(shareSellerJPA.save(share));
    }
    @DeleteMapping("/unSharePost")
    public ResponseEntity<Share> unSharePost(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
       try {
           Optional<Share> share = shareSellerJPA.findById(id);
           Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
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
