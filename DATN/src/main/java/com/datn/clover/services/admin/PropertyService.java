package com.datn.clover.services.admin;

import com.datn.clover.entity.Property;
import com.datn.clover.inter.PropertyJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyJPA propertyJPA;

    public List<Property> getAllProperties() {
        return propertyJPA.findAll();
    }
}
