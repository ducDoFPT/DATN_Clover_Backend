package com.datn.clover.services.admin;

import com.datn.clover.entity.Shop;
import com.datn.clover.inter.ShopJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopJPA shopJPA;

    public List<Shop> getAllShops() {
        return shopJPA.findAll();
    }
}
