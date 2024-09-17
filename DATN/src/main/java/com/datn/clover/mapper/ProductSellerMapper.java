package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.*;
import com.datn.clover.responeObject.PropertiValueSellerResponse;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface ProductSellerMapper {
    ProductSellerBean toDTO(Map<String, String> params);
    TypeProductBean typeToDTO(Map<String, String> params);
    EvaluateBean evaluateToDTO(Map<String, String> params);
    EvaluateFeedbackDTO evaluateFeedbackToDTO(Map<String, String> params);
    PropertyDTO propertyToDTO(Map<String, String> params);
    PropertivalueDTO propertiValueSellerToDTO(Map<String, String> params);
}
