package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rusis")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RUSIES_ID", nullable = false, unique = true)
    private Long id;
    @Column(name = "PAVADINIMAS")
    private String name;
    @OneToMany(mappedBy = "group")
    private Set<Products> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }
}
