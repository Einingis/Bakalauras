package com.uni.bakalauras.model;

import javax.persistence.*;

@Entity
@Table(name = "pirkejas")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KLIENTO_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "KLIENTO_VARDAS")
    private String name;

    @Column(name = "NUMERIS")
    private String number;

    @Column(name = "ADRESAS")
    private String address;

    @Column(name = "MIESTAS")
    private String city;

    @OneToMany(mappedBy = "client")
    private Orders order;

    public Clients() {
    }

    public Clients(Long id, String name, String number, String address, String city) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
