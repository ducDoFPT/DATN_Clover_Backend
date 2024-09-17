package com.datn.clover.services.admin;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.TypeProduct;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.inter.TypeProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductService {

    @Autowired
    private TypeProductJPA typeProductJPA;

    public List<TypeProduct> getAllTypeProduct() {
        return typeProductJPA.findAll();
    }
}
