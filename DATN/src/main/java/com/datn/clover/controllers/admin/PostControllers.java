//package com.datn.clover.controllers.admin;
//
//
//import com.datn.clover.DTO.admin.PostBean;
//import com.datn.clover.entity.Post;
//import com.datn.clover.services.admin.PostService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/posts")
//public class PostControllers {
//    @Autowired
//    private PostService postService;
//
//    @GetMapping
//    public List<Post> getAllPosts() {
//        return postService.getAllPosts();
//    }
//
//    @GetMapping("/browse")
//    public List<Post> getBrowsePosts() {
//        return postService.getAllBrowesPosts();
//    }
//
//    @GetMapping("/denounce")
//    public List<Post> getDenouncePosts() {
//        return postService.getAllDenouncePosts();
//    }
//
//    @GetMapping("/{id}")
//    public Post getPostById(@PathVariable String id) {
//        return postService.getPostById(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<Post> addPost(@RequestBody @Valid PostBean postBean, BindingResult bindingResult)throws BindException {
//        if (bindingResult.hasErrors()) {
//            throw new BindException(bindingResult);
//        } else {
//            try {
//                Post post = new Post();
//                return ResponseEntity.ok(postService.addPost(post, postBean));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return ResponseEntity.badRequest().build();
//            }
//        }
//    }
//
//    @PutMapping("/browse/{id}")
//    public ResponseEntity<Post> browsePost(@PathVariable String id) {
//        Post post = postService.getPostById(id);
//        if (post == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(postService.browsePosts(id));
//        }
//
//    }
//
//    @DeleteMapping("/denounce/{id}")
//    public ResponseEntity<Post> denouncePost(@PathVariable String id) {
//        try {
//            Post post = postService.getPostById(id);
//            if (post == null) {
//                return ResponseEntity.notFound().build();
//            } else {
//                return ResponseEntity.ok(postService.denouncePosts(id));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//
//    }
//
//}
