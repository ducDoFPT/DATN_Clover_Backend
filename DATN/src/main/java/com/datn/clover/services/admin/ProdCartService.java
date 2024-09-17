package com.datn.clover.services.admin;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.ProdCart;
import com.datn.clover.inter.ProdCartJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdCartService {

    @Autowired
    private ProdCartJPA prodCartJPA;

    public List<ProdCart> getAllProdCarts() {
        return prodCartJPA.findAll();
    }
}
