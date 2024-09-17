package com.datn.clover.services.admin;

import com.datn.clover.entity.Share;
import com.datn.clover.inter.ShareJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {

    @Autowired
    private ShareJPA shareJPA;

    public List<Share> getAllShares() {
        return shareJPA.findAll();
    }
}
