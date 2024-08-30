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
public class StorageBean {
    private String id;

    private LocalDate storageDay;

    private Account account;

    private PostBean post;

}