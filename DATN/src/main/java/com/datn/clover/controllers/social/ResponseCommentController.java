package com.datn.clover.controllers.social;

import com.datn.clover.DTO.Sellers.RespondCommentBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Comment;
import com.datn.clover.entity.RespondComment;
import com.datn.clover.inter.CommentJPA;
import com.datn.clover.inter.RespondCommentJPA;
import com.datn.clover.mapper.AccountSellerMapperImpl;
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
@RequestMapping("/api/social/responseComment")
public class ResponseCommentController {

    private final Validator validator;

    private final AccountSellerJPA accountSellerJPA;
    private final JwtService jwtService;
    private final CommentJPA commentJPA;
    private final RespondCommentJPA respondCommentJPA;
    private final AccountSellerMapperImpl accountSellerMapperImpl;

    public ResponseCommentController(Validator validator, AccountSellerJPA accountSellerJPA, JwtService jwtService, CommentJPA commentJPA, RespondCommentJPA respondCommentJPA, AccountSellerMapperImpl accountSellerMapperImpl) {
        this.validator = validator;
        this.accountSellerJPA = accountSellerJPA;
        this.jwtService = jwtService;
        this.commentJPA = commentJPA;
        this.respondCommentJPA = respondCommentJPA;
        this.accountSellerMapperImpl = accountSellerMapperImpl;
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
    public ResponseEntity<RespondComment> create(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) throws BindException{
        RespondCommentBean commentResponse = accountSellerMapperImpl.respondCommentToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(commentResponse, "commentResponse");
        validator.validateObject(commentResponse);
        if (errors.hasErrors()) {
            throw new BindException( errors);
        }
        try {


            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Comment> comment = commentJPA.findById(commentResponse.getComment());
            if (account.isEmpty() || comment.isEmpty() ) {
                throw  new RuntimeException("Account or comment not found");
            }
            RespondComment respondComment = new RespondComment();
            respondComment.setComment(comment.get());
            respondComment.setRespondDay(Instant.now());
            respondComment.setAccount(account.get());
            respondComment.setContent(commentResponse.getContent());
            return ResponseEntity.ok(respondCommentJPA.save(respondComment));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateResponseComment(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) throws BindException{
        RespondCommentBean commentResponseBean = accountSellerMapperImpl.respondCommentToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(commentResponseBean, "commentResponseBean");
        validator.validateObject(commentResponseBean);
        if (errors.hasErrors()) {
            throw new BindException( errors);
        }
        try {

            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<RespondComment> respondComment = respondCommentJPA.findById(commentResponseBean.getId());
            if (account.isEmpty() || respondComment.isEmpty() || !account.get().getId().equals(respondComment.get().getAccount().getId())) {
               return ResponseEntity.badRequest().build();
            }
            respondComment.get().setContent(commentResponseBean.getContent());
            respondComment.get().setRespondDay(Instant.now());
            return ResponseEntity.ok(respondCommentJPA.save(respondComment.get()));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @RequestParam("id") String id) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<RespondComment> respondComment = respondCommentJPA.findById(id);
            if (account.isEmpty() || respondComment.isEmpty() || !account.get().getId().equals(respondComment.get().getAccount().getId())) {
                return ResponseEntity.badRequest().build();
            }
            respondCommentJPA.delete(respondComment.get());
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
