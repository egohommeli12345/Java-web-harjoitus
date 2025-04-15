package com.java_web.vaadin.entities;

import jakarta.persistence.*;

@Entity
public class NutritionalValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double carbohydrateTotal;
    private double carbohydrateSugar;
    private double protein;
    private double saturatedFat;
    private double unsaturatedFat;
    private double totalFat;
    private double fiber;
    private double kcal;

    @OneToOne
    @JoinColumn(name = "product_fk", unique = true)
    private Product product;

    // Getters & setters
}

