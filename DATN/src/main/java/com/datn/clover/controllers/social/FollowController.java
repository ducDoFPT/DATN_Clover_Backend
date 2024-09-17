package com.datn.clover.controllers.social;

import com.datn.clover.DTO.Sellers.FollowBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.FollowUserJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Follow;
import com.datn.clover.mapper.AccountSellerMapperImpl;
import com.datn.clover.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/follow")
public class FollowController {



    private final FollowUserJPA followJPA;
    private final AccountSellerJPA accountSellerJPA;
    private final JwtService jwtService;
    private final AccountSellerMapperImpl accountSellerMapperImpl;
    private final Validator validator;

    public FollowController(FollowUserJPA followJPA, AccountSellerJPA accountSellerJPA, JwtService jwtService, AccountSellerMapperImpl accountSellerMapperImpl, Validator validator) {
        this.followJPA = followJPA;
        this.accountSellerJPA = accountSellerJPA;
        this.jwtService = jwtService;
        this.accountSellerMapperImpl = accountSellerMapperImpl;
        this.validator = validator;
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

    @GetMapping("/getFollowByUsername")
    public List<Follow> getFollowByUsername(@RequestHeader("Authorization") String token) {
        return followJPA.findByAccountId1(jwtService.accessToken(token));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFollow(@RequestParam Map<String, String> params,  @RequestHeader("Authorization") String token) throws BindException {
        FollowBean followBean = accountSellerMapperImpl.followToDTO(params);
        Errors error = validator.validateObject(followBean);
        if(error.hasErrors()){
            throw new BindException((BindingResult) error);
        }
        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(followBean.getAccountId2());
        if (account1.isPresent() && account2.isPresent() ) {
            Iterator<Follow> followIterator = account1.get().getFollows1().iterator();
            for (Follow f : account1.get().getFollows1()){
            if(f.getAccountId2().getId().equals(account2.get().getId())){
                followJPA.delete(f);
                Map<String, String> result = new HashMap<>();
                result.put("result", "Deleted");
                return ResponseEntity.ok().body(result);
            }
        }
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

//    @DeleteMapping("/delete")
//    public ResponseEntity<?> deleteFriend(@RequestHeader("Authorization") String token, @RequestParam("idAccount2") String idAccount2, @RequestParam("idFollow") String idFriend) {
//
//        Optional<Account> account1 = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
//        Optional<Account> account2 = accountSellerJPA.getAccountByUsername(idAccount2);
//        Optional<Follow> follow = followJPA.findById(idFriend);
//        if (account1.isPresent() && account2.isPresent() && follow.isPresent()) {
//            followJPA.delete(follow.get());
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }

}
