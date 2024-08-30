package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Size(max = 225)
    @Column(name = "description", length = 225)
    private String description;

    @OneToMany(mappedBy = "properties")
    @JsonIgnoreProperties({"properties", "products"})
    private List<PropertiesValue> propertiesValues;

}