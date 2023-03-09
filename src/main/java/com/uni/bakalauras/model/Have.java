package com.uni.bakalauras.model;

import com.uni.bakalauras.model.Ids.HaveId;

import javax.persistence.*;

@Entity
@Table(name = "turi")
@IdClass(HaveId.class)
public class Have {

    @Id
    @ManyToOne
    @JoinColumn(name = "PREKESID")
    private Products product;

    @Id
    @ManyToOne
    @JoinColumn(name = "UZSAKYMOID")
    private Orders order;

    @Column(name = "KIEKIS")
    private Integer Quantity;

    public Have() {
    }

    public Have(Products product, Orders order, Integer quantity) {
        this.product = product;
        this.order = order;
        Quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
