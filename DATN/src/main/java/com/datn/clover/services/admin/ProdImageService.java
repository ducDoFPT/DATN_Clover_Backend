package com.datn.clover.services.admin;

import com.datn.clover.entity.ProdImage;
import com.datn.clover.inter.ProdImageJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdImageService {

    @Autowired
    private ProdImageJPA prodImageJPA;

    public List<ProdImage> getAllProdImages() {
        return prodImageJPA.findAll();
    }
}
