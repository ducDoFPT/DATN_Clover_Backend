package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.PromotionSellerBean;
import com.datn.clover.DTO.Sellers.VoucherBean;
import com.datn.clover.responeObject.PromotionSellerResponse;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface PromotionMapper {
        PromotionSellerBean toDTO(Map<String, String> params);
        VoucherBean vouToDTO(Map<String, String> params);
}
