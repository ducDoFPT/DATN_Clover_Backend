package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TypeSupportResponse {
    private String id;

    private String name;

    private String scale;

    private String note;

    private Set<CustomerSupportResponse> customerSupports = new LinkedHashSet<>();

}