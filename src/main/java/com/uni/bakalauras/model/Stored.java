package com.uni.bakalauras.model;

import com.uni.bakalauras.model.Ids.StoredId;

import javax.persistence.*;

@Entity
@Table(name = "sandeliuojama")
@IdClass(StoredId.class)
public class Stored {

    @EmbeddedId
    private StoredId id = new StoredId();

    @ManyToOne
    @JoinColumn(name = "VIETOSID")
    private Places place;

    @ManyToOne
    @JoinColumn(name = "PREKESID")
    private Products product;

    @Column(name = "KIEKIS")
    private Integer Quantity;

    public Stored() {
    }

    public Stored(Places place, Products product, Integer quantity) {
        this.place = place;
        this.product = product;
        Quantity = quantity;
    }

    public StoredId getId() {
        return id;
    }

    public void setId(StoredId id) {
        this.id = id;
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
