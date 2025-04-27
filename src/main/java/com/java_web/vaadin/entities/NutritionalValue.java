package com.java_web.vaadin.entities;

import jakarta.persistence.*;

@Entity
public class NutritionalValue {

    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_fk", unique = true)
    private Product product;
    private double carbohydrateTotal;
    private double carbohydrateSugar;
    private double protein;
    private double saturatedFat;
    private double unsaturatedFat;
    private double totalFat;
    private double fiber;
    private double kcal;
    private double package_size;


    // Getters & setters

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getCarbohydrateTotal() {
        return carbohydrateTotal;
    }

    public void setCarbohydrateTotal(double carbohydrateTotal) {
        this.carbohydrateTotal = carbohydrateTotal;
    }

    public double getCarbohydrateSugar() {
        return carbohydrateSugar;
    }

    public void setCarbohydrateSugar(double carbohydrateSugar) {
        this.carbohydrateSugar = carbohydrateSugar;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getUnsaturatedFat() {
        return unsaturatedFat;
    }

    public void setUnsaturatedFat(double unsaturatedFat) {
        this.unsaturatedFat = unsaturatedFat;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getPackage_size() {
        return package_size;
    }

    public void setPackage_size(double package_size) {
        this.package_size = package_size;
    }
}

