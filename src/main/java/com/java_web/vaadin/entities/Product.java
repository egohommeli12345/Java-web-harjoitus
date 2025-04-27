package com.java_web.vaadin.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "category_fk")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Price> prices;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private NutritionalValue nutritionalValue;

    // Getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public double getCarbPerEuro() {
        return Math.round(
                ((this.getPricePerKilo()*100)
                        /this.getNutritionalValue().getCarbohydrateTotal())*100.0
        )/100.0;
    }

    public double getFatPerEuro() {
        return Math.round(
                ((this.getPricePerKilo()*100)
                        /this.getNutritionalValue().getTotalFat())*100.0
        )/100.0;
    }

    public double getProteinPerEuro() {
        return Math.round(
                ((this.getPricePerKilo()*100)
                        /this.getNutritionalValue().getProtein())*100.0
        )/100.0;
    }

    public String getProdStore() {
        return this.getPrices().get(0).getStore().getName();
    }

    public double getPricePerKiloRounded() {
        return Math.round(
                ((this.getPrices().get(0).getPrice()
                /this.getNutritionalValue().getPackage_size())*1000)*100.0
        )/100.0;
    }

    public double getPricePerKilo() {
        return (this.getPrices().get(0).getPrice()
                /this.getNutritionalValue().getPackage_size())*1000;
    }
}
