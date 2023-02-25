package com.uni.bakalauras.model;

import javax.persistence.*;

@Entity
@Table(name = "preke")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PREKES_ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name="RUSIES_ID", nullable=false)
    private Groups group;
    @Column(name = "PAVADINIMAS")
    private String name;
    @Column(name = "SANDELYJE")
    private Integer inStock;
    @Column(name = "ISMATAVIMAI")
    private String measurement;
    @Column(name = "SPALVA")
    private String color;
    @Column(name = "SAVIKAINA")
    private Double primeCost;
    @Column(name = "PARDAVIMO_KAINA")
    private Double sellCost;

    public Products() {
    }

    public Products(Groups group, String name) {
        this.group = group;
        this.name = name;
    }

    public Products(Long id, Groups group, String name, Integer inStock, String measurement, String color, Double primeCost, Double sellCost) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.inStock = inStock;
        this.measurement = measurement;
        this.color = color;
        this.primeCost = primeCost;
        this.sellCost = sellCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groups getType() {
        return group;
    }

    public void setType(Groups group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrimeCost() {
        return primeCost;
    }

    public void setPrimeCost(Double primeCost) {
        this.primeCost = primeCost;
    }

    public Double getSellCost() {
        return sellCost;
    }

    public void setSellCost(Double sellCost) {
        this.sellCost = sellCost;
    }
}
