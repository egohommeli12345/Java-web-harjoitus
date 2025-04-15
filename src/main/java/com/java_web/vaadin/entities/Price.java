package com.java_web.vaadin.entities;

import jakarta.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double price;

    @ManyToOne
    @JoinColumn(name = "store_fk")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_fk")
    private Product product;

    // Getters & setters
}

