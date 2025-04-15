package com.java_web.vaadin.entities;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Price> prices;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private NutritionalValue nutritionalValue;

    // Getters & setters
}
