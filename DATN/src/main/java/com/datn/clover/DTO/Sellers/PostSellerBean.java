package com.datn.clover.DTO.Sellers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostSellerBean {
    private String id;
    @NotBlank(message = "Title is Null!")
    private String title;
    @NotBlank(message = "Content is Null!")
    private String content;
    @NotBlank(message = "Share type is Null!")
    private String stypeShare;



}