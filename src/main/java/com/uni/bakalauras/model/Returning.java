package com.uni.bakalauras.model;

import com.uni.bakalauras.model.Ids.ReturningId;

import javax.persistence.*;

@Entity
@Table(name = "grazina")
@IdClass(ReturningId.class)
public class Returning {

    @Id
    @ManyToOne
    @JoinColumn(name = "GRAZINIMOID")
    private Returns returns;

    @Id
    @ManyToOne
    @JoinColumn(name = "PREKESID")
    private Products product;

    @Column(name = "KIEKIS")
    private Integer Quantity;

    public Returning() {
    }

    public Returning(Returns returns, Products product, Integer quantity) {
        this.returns = returns;
        this.product = product;
        Quantity = quantity;
    }

    public Returns getReturns() {
        return returns;
    }

    public void setReturns(Returns returns) {
        this.returns = returns;
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
