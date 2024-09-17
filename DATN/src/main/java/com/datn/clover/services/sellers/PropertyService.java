package com.datn.clover.services.sellers;

import com.datn.clover.DTO.Sellers.PropertyDTO;
import com.datn.clover.entity.Property;
import com.datn.clover.inter.PropertyJPA;
import com.datn.clover.responeObject.PropertyResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyJPA propertyJPA;

    public PropertyService(PropertyJPA propertyJPA) {
        this.propertyJPA = propertyJPA;
    }

    public PropertyResponse create(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setName(propertyDTO.getName());
        property.setDescription(propertyDTO.getDescription());
        Property propertiRS = propertyJPA.save(property);
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setId(propertiRS.getId());
        propertyResponse.setName(propertiRS.getName());
        propertyResponse.setDescription(propertiRS.getDescription());
        return propertyResponse;
    }
    public PropertyResponse update(PropertyDTO propertyDTO) {
        Optional<Property> property = propertyJPA.findById(String.valueOf(propertyDTO.getId()));
        if (property.isPresent()) {
        property.get().setName(propertyDTO.getName());
        property.get().setDescription(propertyDTO.getDescription());
        Property propertiRS = propertyJPA.save(property.get());
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setId(propertiRS.getId());
        propertyResponse.setName(propertiRS.getName());
        propertyResponse.setDescription(propertiRS.getDescription());
        return propertyResponse;
        }else{
            return new PropertyResponse();
        }
    }
}
