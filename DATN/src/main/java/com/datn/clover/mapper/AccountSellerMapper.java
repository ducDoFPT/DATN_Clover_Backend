package com.datn.clover.mapper;

import com.datn.clover.DTO.Sellers.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface AccountSellerMapper {
    AccountSellerMapper INSTANCE = Mappers.getMapper(AccountSellerMapper.class);
    AccountCreateDTO paramsToAccountDTO(Map<String, String> params);
    AccountSellerBean toDTO(Map<String , String> params);
    FollowBean followToDTO(Map<String , String> params);
    FriendBean friendToDTO(Map<String , String> params);
    RespondCommentBean respondCommentToDTO(Map<String , String> params);
    BillBean billToDTO(Map<String , String> params);
}
