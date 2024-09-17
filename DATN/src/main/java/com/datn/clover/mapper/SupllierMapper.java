package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.SupplierBean;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface SupllierMapper {
    SupplierBean supllierToDTO(Map<String, String> params);
}
