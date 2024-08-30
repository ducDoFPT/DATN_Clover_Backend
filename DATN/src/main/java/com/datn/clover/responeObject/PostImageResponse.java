package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class PostImageResponse {
    private String id;

    private String nameImage;

    private PostResponse post;

}