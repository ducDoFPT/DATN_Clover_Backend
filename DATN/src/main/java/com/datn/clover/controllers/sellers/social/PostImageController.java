package com.datn.clover.controllers.sellers.social;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.PostImageJPA;
import com.datn.clover.JPAs.PostSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Post;
import com.datn.clover.entity.PostImage;
import com.datn.clover.services.sellers.UploadServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/postIamge")
public class PostImageController {
    private final PostSellerJPA postSellerJPA;
    private final UploadServices uploadServices;
    private final PostImageJPA postImageJPA;
    private final AccountSellerJPA accountSellerJPA;

    public PostImageController(PostSellerJPA postSellerJPA, UploadServices uploadServices, PostImageJPA postImageJPA, AccountSellerJPA accountSellerJPA) {
        this.postSellerJPA = postSellerJPA;
        this.uploadServices = uploadServices;
        this.postImageJPA = postImageJPA;
        this.accountSellerJPA = accountSellerJPA;
    }

    @PostMapping("/create")
    public ResponseEntity<PostImage> createPostImage(@RequestParam("files") List<MultipartFile> images, @RequestParam("postID")String PostId) {
        Optional<Post> post = postSellerJPA.findById(PostId);
        if (post.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PostImage prodImage = new PostImage();
        int i = 7;
        for (MultipartFile file : images) {
            String fileName = uploadServices.uploadFile(file);
            prodImage.setId("IMG00"+i);
            prodImage.setPost(post.get());
            prodImage.setNameImage(fileName);
            postImageJPA.save(prodImage);
            i++;
        }
        return ResponseEntity.ok().build();
    }

    //cap nhat anh san pham
    @PutMapping("/updateImageProduct/{token}")
    public ResponseEntity<?> updateImageProduct(@PathVariable("token") String token, List<MultipartFile> images, @RequestParam("postID") String PostId){
        Optional<Post> post = postSellerJPA.findById(PostId);
        if (post.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PostImage> postImages = postImageJPA.findByPostId(post.get().getId());
        PostImage postImage = new PostImage();
        for (MultipartFile file : images) {
            for(PostImage Image : postImages){
                if(!Image.getNameImage().equals(file.getOriginalFilename())){
                    String fileName = uploadServices.uploadFile(file);
                    postImage.setPost(post.get());
                    postImage.setNameImage(fileName);
                    postImageJPA.save(postImage);
                }
            }
        }
        return ResponseEntity.ok().build();
    }

    //xoa anh san pham
    @DeleteMapping("/deleteImageProduct/{token}")
    public ResponseEntity<Void> deleteImageProduct(@PathVariable("token") String token, @RequestParam("postID") String postID){
        try {
            Optional<Post> product = postSellerJPA.findById(postID);
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if (product.isEmpty() || account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            List<PostImage> postImages = postImageJPA.findByPostId(product.get().getId());
            for (PostImage postImage : postImages) {
                postImageJPA.deleteById(postImage.getId());
            }
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
