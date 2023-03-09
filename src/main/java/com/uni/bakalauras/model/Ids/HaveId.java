package com.uni.bakalauras.model.Ids;

import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Products;

import java.io.Serializable;
import java.util.Objects;

public class HaveId implements Serializable {

    private Products product;
    private Orders order;

    public HaveId() {
    }

    public HaveId(Products product, Orders order) {
        this.product = product;
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HaveId haveId = (HaveId) o;
        return Objects.equals(product, haveId.product) && Objects.equals(order, haveId.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }
}
