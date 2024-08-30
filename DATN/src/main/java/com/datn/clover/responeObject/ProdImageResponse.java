package com.datn.clover.responeObject;

import com.datn.clover.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdImageResponse {
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product prod;

}