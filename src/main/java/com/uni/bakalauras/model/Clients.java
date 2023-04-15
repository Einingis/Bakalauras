package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "klientas")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KLIENTOID", nullable = false, unique = true)
    private Long id;

    @Column(name = "VARDAS")
    private String name;

    @Column(name = "PAVARDE")
    private String surname;

    @Column(name = "TELNUMERIS")
    private String number;

    @Column(name = "MIESTAS")
    private String city;

    @Column(name = "ADRESAS")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Orders> order;

    public Clients() {
    }

    public Clients(String name, String surname, String number, String city, String address) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.city = city;
        this.address = address;
    }

    public Clients(String name, String surname, String city, String address) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
    }

    public Clients(String name, String surname, String number, String city, String address, Set<Orders> order) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.city = city;
        this.address = address;
        this.order = order;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return getName() + " " + getSurname();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Orders> getOrder() {
        return order;
    }

    public void setOrder(Set<Orders> order) {
        this.order = order;
    }
}
