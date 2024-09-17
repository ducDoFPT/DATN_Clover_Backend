package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.EvaluateFeedbackDTO;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.EvaluateFeedbackJPA;
import com.datn.clover.JPAs.EvaluateUserJPA;
import com.datn.clover.JPAs.ShopSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Evaluate;
import com.datn.clover.entity.EvaluatesFeedback;
import com.datn.clover.entity.Shop;
import com.datn.clover.mapper.ProductSellerMapper;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.UploadSellerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/evaluateFeedback")
public class EvaluateFeedbackController {

    private final EvaluateFeedbackJPA evaluateFeedbackJPA;
    private final ProductSellerMapper productSellerMapper;
    private final Validator validator;
    private final AccountSellerJPA accountSellerJPA;
    private final JwtService jwtService;
    private final ShopSellerJPA shopSellerJPA;
    private final EvaluateUserJPA evaluateUserJPA;
    private final UploadSellerServices uploadSellerServices;

    public EvaluateFeedbackController(EvaluateFeedbackJPA evaluateFeedbackJPA, ProductSellerMapper productSellerMapper, Validator validator, AccountSellerJPA accountSellerJPA, JwtService jwtService, ShopSellerJPA shopSellerJPA, EvaluateUserJPA evaluateUserJPA, UploadSellerServices uploadSellerServices) {
        this.evaluateFeedbackJPA = evaluateFeedbackJPA;
        this.productSellerMapper = productSellerMapper;
        this.validator = validator;
        this.accountSellerJPA = accountSellerJPA;
        this.jwtService = jwtService;
        this.shopSellerJPA = shopSellerJPA;
        this.evaluateUserJPA = evaluateUserJPA;
        this.uploadSellerServices = uploadSellerServices;
    }

    @GetMapping
    public List<EvaluatesFeedback> getEvaluateFeedback(@RequestHeader("Authorization") String token, @RequestParam("id") String id) {

        try {
            List<EvaluatesFeedback> feedbackList = evaluateFeedbackJPA.findByEvaluateId(id);
            if (feedbackList.isEmpty()) {
                return null;
            }
            return feedbackList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvaluateFeedback(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params , @RequestParam("image")MultipartFile images) throws BindException {
        EvaluateFeedbackDTO evaluateFeedbackDTO = productSellerMapper.evaluateFeedbackToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(evaluateFeedbackDTO, "evaluateFeedbackDTO");
        validator.validate(evaluateFeedbackDTO, errors);
        if (errors.hasErrors()) {
            throw new BindException(errors);
        }
        try {


        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<Evaluate> evaluate = evaluateUserJPA.findById(evaluateFeedbackDTO.getEvaluate());

        if (account.isPresent()  && evaluate.isPresent() && (evaluate.get().getProd().getShop().getId().equals(account.get().getStaff().getShop().getId()))) {
            Shop shop = account.get().getStaff().getShop();
            EvaluatesFeedback evaluatesFeedback = new EvaluatesFeedback();
            evaluatesFeedback.setEvaluate(evaluate.get());
            evaluatesFeedback.setEvaluateDay(LocalDate.now());
            evaluatesFeedback.setContent(evaluateFeedbackDTO.getContent());
            evaluatesFeedback.setShop(shop);
            String fileName = uploadSellerServices.uploadFile(images);
            evaluatesFeedback.setImage(fileName);
            EvaluatesFeedback evaluatesFeedbackResult = evaluateFeedbackJPA.save(evaluatesFeedback);
            return ResponseEntity.ok(evaluatesFeedbackResult);
        }
        return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateEvaluateFeedback(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params , @RequestParam("image")MultipartFile images) throws BindException {
        EvaluateFeedbackDTO evaluateFeedbackDTO = productSellerMapper.evaluateFeedbackToDTO(params);
        BindingResult errors = new BeanPropertyBindingResult(evaluateFeedbackDTO, "evaluateFeedbackDTO");
        validator.validate(evaluateFeedbackDTO, errors);
        if (errors.hasErrors()) {
            throw new BindException(errors);
        }
        try {


            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Evaluate> evaluate = evaluateUserJPA.findById(evaluateFeedbackDTO.getEvaluate());
            Optional<EvaluatesFeedback> evaluatesFeedback = evaluateFeedbackJPA.findById(evaluateFeedbackDTO.getId().toString());
            if (account.isPresent() && evaluatesFeedback.isPresent()  && evaluate.isPresent() && (evaluate.get().getProd().getShop().getId().equals(account.get().getStaff().getShop().getId()))) {
                Shop shop = account.get().getStaff().getShop();
                evaluatesFeedback.get().setEvaluate(evaluate.get());
                evaluatesFeedback.get().setEvaluateDay(LocalDate.now());
                evaluatesFeedback.get().setContent(evaluateFeedbackDTO.getContent());
                evaluatesFeedback.get().setShop(shop);
                String fileName = uploadSellerServices.uploadFile(images);
                evaluatesFeedback.get().setImage(fileName);
                EvaluatesFeedback evaluatesFeedbackResult = evaluateFeedbackJPA.save(evaluatesFeedback.get());
                return ResponseEntity.ok(evaluatesFeedbackResult);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeEvaluateFeedback(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws BindException {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<EvaluatesFeedback> evaluateFeedback = evaluateFeedbackJPA.findById(id);
        if (account.isPresent() && evaluateFeedback.isPresent() && evaluateFeedback.get().getShop().getId().equals(account.get().getStaff().getShop().getId())) {
            evaluateFeedbackJPA.delete(evaluateFeedback.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
