package com.uni.bakalauras.model.Ids;

import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.model.Returns;

import java.io.Serializable;
import java.util.Objects;

public class ReturningId implements Serializable {

    private Returns returns;
    private Products product;

    public ReturningId() {
    }


    public ReturningId(Returns returns, Products product) {
        this.returns = returns;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturningId that = (ReturningId) o;
        return returns.equals(that.returns) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returns, product);
    }
}
