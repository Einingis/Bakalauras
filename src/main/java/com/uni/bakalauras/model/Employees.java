package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "darbuotojas")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DARBUOTOJO_NR", nullable = false, unique = true)
    private Long id;

    @Column(name = "VARDAS")
    private String name;

    @Column(name = "PAVARDE")
    private String surname;

    @Column(name = "SLAPTAZODIS")
    private String password;

    @Column(name = "POZICIJA")
    private String position;

    @OneToMany(mappedBy = "employee")
    private Set<StockOrders> stockOrder;

    @OneToMany(mappedBy = "employee")
    private Set<Orders> order;

    public Employees() {
    }

    public Employees(Long id, String name, String surname, String password, String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.position = position;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
