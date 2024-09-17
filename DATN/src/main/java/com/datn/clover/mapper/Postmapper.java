package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.CommentBean;
import com.datn.clover.DTO.Sellers.PostSellerBean;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface Postmapper {
    PostSellerBean ToDTO(Map<String, String> params);
    CommentBean commentToDTO(Map<String, String> params);
}
