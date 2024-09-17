package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.AddressSellerBean;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface AddressAccountmapper {
    AddressAccountmapper INSTANCE = Mappers.getMapper(AddressAccountmapper.class);
    AddressSellerBean toDTO(Map<String, String> params);
}
