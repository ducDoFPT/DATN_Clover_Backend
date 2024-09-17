package com.datn.clover.controllers.social;

import com.datn.clover.DTO.Sellers.CommentBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.CommentSocialJPA;
import com.datn.clover.JPAs.PostSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Comment;
import com.datn.clover.entity.Post;
import com.datn.clover.mapper.Postmapper;
import com.datn.clover.mapper.PostmapperImpl;
import com.datn.clover.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/social/comment")
public class CommentController {

    private final Validator validator;
    private final PostSellerJPA postSellerJPA;
    private final AccountSellerJPA accountSellerJPA;
    private final JwtService jwtService;
    private final CommentSocialJPA commentSocialJPA;
    private final Postmapper postmapper;

    public CommentController(Validator validator, PostSellerJPA postSellerJPA, AccountSellerJPA accountSellerJPA, JwtService jwtService, CommentSocialJPA commentSocialJPA, Postmapper postmapper) {
        this.validator = validator;

        this.postSellerJPA = postSellerJPA;
        this.accountSellerJPA = accountSellerJPA;
        this.jwtService = jwtService;
        this.commentSocialJPA = commentSocialJPA;
        this.postmapper = postmapper;
    }
    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(BindingResult be) {
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
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) throws BindException {
        CommentBean comment = postmapper.commentToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(comment, "comment");
        validator.validateObject(comment);
        if (error.hasErrors()) {
            throw  new BindException( error);
        }
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Post> post = postSellerJPA.findById(comment.getPost());
        if (post.isEmpty() || account.isEmpty()) {
            throw new BindException( error);
        }
        Comment comment1 = new Comment();
        comment1.setContent(comment.getContent());
        comment1.setCommentDay(Instant.now());
        comment1.setPost(post.get());
        comment1.setAccount(account.get());
        Comment comment2 = commentSocialJPA.save(comment1);
       return ResponseEntity.ok(comment2);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) throws BindException {
        CommentBean commentBean = postmapper.commentToDTO(params);
        BindingResult error = new BeanPropertyBindingResult(commentBean, "comment");
        validator.validateObject(commentBean);
        if (error.hasErrors()) {
            throw  new BindException( error);
        }
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Comment> comment = commentSocialJPA.findById(commentBean.getId());
        if (comment.isEmpty() || account.isEmpty() || !comment.get().getAccount().equals(account.get())) {
            throw new BindException( error);
        }
        comment.get().setContent(commentBean.getContent());
        comment.get().setCommentDay(Instant.now());
        return ResponseEntity.ok( commentSocialJPA.save(comment.get()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestHeader("Authorization") String token, @RequestParam("id") String commentID) throws BindException {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Comment> comment = commentSocialJPA.findById(commentID);
        if (comment.isEmpty() || account.isEmpty() || !comment.get().getAccount().equals(account.get())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            commentSocialJPA.delete(comment.get());
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
