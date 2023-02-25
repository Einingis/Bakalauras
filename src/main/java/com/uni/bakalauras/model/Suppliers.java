package com.uni.bakalauras.model;

import javax.persistence.*;

@Entity
@Table(name = "tiekejas")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TIEKEJO_ID", nullable = false, unique = true)
    private Long id;
    @Column(name = "PAVADINIMAS")
    private String name;
    @Column(name = "NUMERIS")
    private String number;
    @Column(name = "ADRESAS")
    private String address;
    @Column(name = "UZRASAI")
    private String notes;

    public Suppliers() {
    }

    public Suppliers(Long id, String name, String number, String address, String notes) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.notes = notes;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
