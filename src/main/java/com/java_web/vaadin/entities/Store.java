package com.java_web.vaadin.entities;

import jakarta.persistence.*;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Price> prices;

    // Getters & setters
}

