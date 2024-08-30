package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class
AddressResponse {

    private String id;

    private String province;

    private String city;

    private String district;

    private String wards;

    private String streetnameNumber;

    private String nation;

    private Account account;

}