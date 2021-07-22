package com.fernnx2.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "priceBuy", nullable = false)
    private Double priceBuy;
    @Column(name = "priceSell", nullable = false)
    private Double priceSell;
    @Column(name = "stock")
    private int stock;
    @Column(name = "category", nullable = false)
    private int category;
    @Column(name = "description", nullable = false)
    private String description;


}
