package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.AccountSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.AddressSellerJPA;
import com.datn.clover.entity.Account;
//import com.datn.clover.services.sellers.JwtService;
import com.datn.clover.mapper.AccountSellerMapper;
import com.datn.clover.responeObject.AccountSellerResponse;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.account.AccountSellerService;
import com.datn.clover.services.sellers.UploadSellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountSellerJPA accountJPA;
    @Autowired
    private AccountSellerService accountService;
    @Autowired
    private UploadSellerServices uploadServices;
    @Autowired
    private AddressSellerJPA addressJPA;
    @Autowired
    private AccountSellerService accountSellerService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private final AccountSellerMapper accountSellerMapper =  AccountSellerMapper.INSTANCE;
    @Autowired
    Validator validator;
//    @Autowired
//    JwtService jwtService;
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
    //lay thong tin bang username
    @GetMapping
    public ResponseEntity<AccountSellerResponse> getAccount(@RequestHeader("Authorization") String header) {
        try {
            Account account = accountSellerService.getAccount(jwtService.accessToken(header));
            return ResponseEntity.ok(accountSellerService.setAccountResponse(account));
        }catch (NullPointerException e) {
           return ResponseEntity.notFound().build();
        }
    }
        // update thong tin account
        @PutMapping("/updateInfor")
        public ResponseEntity<?> updateAccount(@RequestParam Map<String, String> params
                , @RequestHeader("Authorization") String username
                , @RequestParam("avatar") MultipartFile avatar) throws BindException {
            AccountSellerBean accountSellerBean = accountSellerMapper.toDTO(params);

            // Tạo đối tượng BindingResult cho AccountSellerBean
            BindingResult error = new BeanPropertyBindingResult(accountSellerBean, "accountSellerBean");
            validator.validate(accountSellerBean, error);
            if (error.hasErrors()) {
                throw  new BindException(error);
            }
            // Xử lý logic lưu trữ hoặc cập nhật dữ liệu
            return AccountSellerService.checkDTO(accountSellerBean, jwtService.accessToken(username), accountJPA, accountSellerService, avatar, uploadServices);
        }
}
