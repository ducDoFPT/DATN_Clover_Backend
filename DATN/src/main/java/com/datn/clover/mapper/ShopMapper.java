package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.ShopSellerBean;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface ShopMapper {
    ShopSellerBean toDTO(Map<String, String> params);
}
