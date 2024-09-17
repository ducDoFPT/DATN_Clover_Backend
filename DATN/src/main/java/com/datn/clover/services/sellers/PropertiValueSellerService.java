package com.datn.clover.services.sellers;

import com.datn.clover.DTO.Sellers.PropertivalueDTO;
import com.datn.clover.JPAs.PropertiValueSellerJPA;
import com.datn.clover.entity.PropertiesValue;
import com.datn.clover.entity.Property;
import com.datn.clover.inter.PropertyJPA;
import com.datn.clover.responeObject.PropertiValueSellerResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;

@Service
public class PropertiValueSellerService {

    private final PropertyJPA propertyJPA;
    private final PropertiValueSellerJPA propertiValueSellerJPA;

    public PropertiValueSellerService(PropertyJPA propertyJPA, PropertiValueSellerJPA propertiValueSellerJPA) {
        this.propertyJPA = propertyJPA;
        this.propertiValueSellerJPA = propertiValueSellerJPA;
    }

    public PropertiValueSellerResponse create(PropertivalueDTO dto){
            Optional<Property> properties = propertyJPA.findById(dto.getProperties());
            if(properties.isPresent()){
                PropertiesValue value = new PropertiesValue();
                value.setName(dto.getName());
                value.setDescription(dto.getDescription());
                value.setProperties(properties.get());
                PropertiesValue propertiesValue =  propertiValueSellerJPA.save(value);
                PropertiValueSellerResponse response = new PropertiValueSellerResponse();
                response.setName(response.getName());
                response.setDescription(response.getDescription());
                response.setProperties(response.getProperties());
                return response;
            }
                return new PropertiValueSellerResponse();
        }
    public PropertiValueSellerResponse update(PropertivalueDTO dto){
        Optional<Property> properties = propertyJPA.findById(dto.getProperties());
        Optional<PropertiesValue> value = propertiValueSellerJPA.findById(String.valueOf(dto.getId()));
        if(properties.isPresent() && value.isPresent()){
            value.get().setName(dto.getName());
            value.get().setDescription(dto.getDescription());
            value.get().setProperties(properties.get());
            PropertiesValue propertiesValue =  propertiValueSellerJPA.save(value.get());
            PropertiValueSellerResponse response = new PropertiValueSellerResponse();
            response.setName(response.getName());
            response.setDescription(response.getDescription());
            response.setProperties(response.getProperties());
            return response;
        }
        return new PropertiValueSellerResponse();
    }
}
