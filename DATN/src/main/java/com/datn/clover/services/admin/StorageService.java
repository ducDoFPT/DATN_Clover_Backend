package com.datn.clover.services.admin;

import com.datn.clover.entity.Storage;
import com.datn.clover.inter.StorageJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private StorageJPA storageJPA;

    public List<Storage> getAllStorages() {
        return storageJPA.findAll();
    }
}
