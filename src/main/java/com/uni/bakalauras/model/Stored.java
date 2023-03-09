package com.uni.bakalauras.model;

import com.uni.bakalauras.model.Ids.StoredId;

import javax.persistence.*;

@Entity
@Table(name = "sandeliuojama")
@IdClass(StoredId.class)
public class Stored {

    @Id
    @ManyToOne
    @JoinColumn(name = "VIETOSID")
    private Places place;

    @Id
    @ManyToOne
    @JoinColumn(name = "PREKESID")
    private Products product;

    @Column(name = "KIEKIS")
    private Integer Quantity;

    public Stored(Places place, Products product, Integer quantity) {
        this.place = place;
        this.product = product;
        Quantity = quantity;
    }

    public Stored() {

    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
