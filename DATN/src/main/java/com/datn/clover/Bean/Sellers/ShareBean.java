package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ShareBean {
    private String id;

    private LocalDate shareDay;

    private String receiver;

    private Account account;
    private PostBean post;

}