package com.datn.clover.responeObject;

import com.datn.clover.entity.PropertiesValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties({"account","acc","accountId1", "accountId2","accounts"})
public class PropertyResponse {
    private Integer id;

    private String name;

    private String description;

    @JsonIgnoreProperties({"properties", "products", "account"})
    private Set<PropertiesValue> propertiesValues = new LinkedHashSet<>();
}
