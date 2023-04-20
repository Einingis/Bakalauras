package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "preke")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PREKESID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name="RUSIESID", nullable=false)
    private Groups group;

    @Column(name = "PAVADINIMAS")
    private String name;

    @Column(name = "SPALVA")
    private String color;

    @Column(name = "ISMATAVIMAS")
    private String measurement;

    @Column(name = "SAVIKAINA")
    private Double primeCost;

    @Column(name = "KAINA")
    private Double sellCost;

    @Column(name = "KIEKIS")
    private Integer inStock;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Stored> stored;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Have> have;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Returning> returning;

    private Double sum;

    public Products() {
    }

    public Products(Groups group, String name, String color, String measurement, Double primeCost, Double sellCost, Integer inStock) {
        this.group = group;
        this.name = name;
        this.color = color;
        this.measurement = measurement;
        this.primeCost = primeCost;
        this.sellCost = sellCost;
        this.inStock = inStock;
    }

    public Products(String name, String color, String measurement, Double primeCost, Double sellCost, Integer inStock) {
        this.name = name;
        this.color = color;
        this.measurement = measurement;
        this.primeCost = primeCost;
        this.sellCost = sellCost;
        this.inStock = inStock;
    }

    public Products(Groups group, String name, String color, String measurement, Double primeCost, Double sellCost, Integer inStock, Set<Stored> stored) {
        this.group = group;
        this.name = name;
        this.color = color;
        this.measurement = measurement;
        this.primeCost = primeCost;
        this.sellCost = sellCost;
        this.inStock = inStock;
        this.stored = stored;
    }

    public Products(Groups group, String name, String color, String measurement, Double primeCost,
                    Double sellCost, Integer inStock, Set<Stored> stored, Set<Have> have, Set<Returning> returning) {
        this.group = group;
        this.name = name;
        this.color = color;
        this.measurement = measurement;
        this.primeCost = primeCost;
        this.sellCost = sellCost;
        this.inStock = inStock;
        this.stored = stored;
        this.have = have;
        this.returning = returning;
    }

    public Products(Products selectedItem) {
        this.id = selectedItem.getId();
        this.group = selectedItem.getGroup();
        this.name = selectedItem.getName();
        this.color = selectedItem.getColor();
        this.measurement = selectedItem.getMeasurement();
        this.primeCost = selectedItem.getPrimeCost();
        this.sellCost = selectedItem.getSellCost();
        this.inStock = selectedItem.getInStock();
        this.stored = selectedItem.getStored();
        this.have = selectedItem.getHave();
        this.returning = selectedItem.getReturning();
        this.sum = selectedItem.getSum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groups getGroup() {
        return group;
    }

    public String getGroupName() {
        return getGroup().getName();
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
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

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Set<Stored> getStored() {
        return stored;
    }

    public void setStored(Set<Stored> stored) {
        this.stored = stored;
    }

    public Set<Have> getHave() {
        return have;
    }

    public void setHave(Set<Have> have) {
        this.have = have;
    }

    public Set<Returning> getReturning() {
        return returning;
    }

    public void setReturning(Set<Returning> returning) {
        this.returning = returning;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
