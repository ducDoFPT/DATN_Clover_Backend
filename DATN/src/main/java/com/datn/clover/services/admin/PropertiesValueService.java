package com.datn.clover.services.admin;

import com.datn.clover.entity.PropertiesValue;
import com.datn.clover.inter.PropertiesValueJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertiesValueService {

    @Autowired
    private PropertiesValueJPA propertiesValueJPA;

    public List<PropertiesValue> getAllPropertieValues() {
        return propertiesValueJPA.findAll();
    }
}
