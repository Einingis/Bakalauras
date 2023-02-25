package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vieta")
public class Places {

    @Id
    @Column(name = "SANDELIS", nullable = false, unique = true)
    private String warehouse;

    @Id
    @Column(name = "LENTYNA", nullable = false, unique = true)
    private String shelf;

    @Id
    @Column(name = "VIETA", nullable = false, unique = true)
    private String place;

    @Column(name = "PILNA")
    private Boolean full;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "sandeliuojama",
            joinColumns = { @JoinColumn(name = "sandelis"), @JoinColumn(name = "lentyna"), @JoinColumn(name = "vieta")},
            inverseJoinColumns = { @JoinColumn(name = "prekes_id") }
    )
    Set<Products> product = new HashSet<>();

    public Places() {
    }

    public Places(String warehouse, String shelf, String place, Boolean full) {
        this.warehouse = warehouse;
        this.shelf = shelf;
        this.place = place;
        this.full = full;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }
}
