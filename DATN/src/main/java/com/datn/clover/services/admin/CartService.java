package com.datn.clover.services.admin;

import com.datn.clover.entity.Cart;
import com.datn.clover.inter.CartJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartJPA cartJPA;

    public List<Cart> getAllCarts() {
        return cartJPA.findAll();
    }
}
