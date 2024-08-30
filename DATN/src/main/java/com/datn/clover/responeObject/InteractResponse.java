package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InteractResponse {
    private String id;

    private String name;

    private FunctionResponse function;

    private Set<Account> accounts = new LinkedHashSet<>();

}