package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"account","acc","accountId1", "accountId2","accounts"})

public class
AddressAccountResponse {

    private String province;

    private String city;

    private String district;

    private String wards;

    private String streetnameNumber;

    private String nation;


}