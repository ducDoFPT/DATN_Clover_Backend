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
public class CommentBean {
    private String id;
    @NotBlank(message = "Content is null!")
    private String content;
    @NotBlank(message = "Post is null!")
    private String post;



}