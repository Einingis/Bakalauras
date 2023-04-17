package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vieta")
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VIETOSID", nullable = false, unique = true)
    private Long id;


    @Column(name = "SANDELIS", nullable = false)
    private String warehouse;


    @Column(name = "LENTYNA", nullable = false)
    private String shelf;


    @Column(name = "VIETA", nullable = false)
    private String place;

    @Column(name = "PILNA")
    private Boolean full;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
    private Set<Stored> stored;

    private Integer Quantity;

    public Places() {
    }

    public Places(String warehouse, String shelf, String place, Boolean full) {
        this.warehouse = warehouse;
        this.shelf = shelf;
        this.place = place;
        this.full = full;
    }

    public Places(String warehouse, String shelf, String place, Boolean full, Set<Stored> stored) {
        this.warehouse = warehouse;
        this.shelf = shelf;
        this.place = place;
        this.full = full;
        this.stored = stored;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Stored> getStored() {
        return stored;
    }

    public void setStored(Set<Stored> stored) {
        this.stored = stored;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
