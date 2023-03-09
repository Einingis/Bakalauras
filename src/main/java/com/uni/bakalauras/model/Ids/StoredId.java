package com.uni.bakalauras.model.Ids;

import com.uni.bakalauras.model.Places;
import com.uni.bakalauras.model.Products;

import java.io.Serializable;
import java.util.Objects;

public class StoredId implements Serializable {

    private Places place;
    private Products product;

    public StoredId() {
    }

    public StoredId(Places place, Products product) {
        this.place = place;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoredId storedId = (StoredId) o;
        return place.equals(storedId.place) && product.equals(storedId.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, product);
    }
}
