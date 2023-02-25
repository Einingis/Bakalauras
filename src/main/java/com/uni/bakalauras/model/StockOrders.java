package com.uni.bakalauras.model;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prekiu_papildymas")
public class StockOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "P_UZSAKYMO_ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name="DARBUOTOJO_NR", nullable=false)
    private Employees employee;

    @ManyToOne
    @JoinColumn(name="TIEKEJO_ID", nullable=false)
    private Suppliers supplier;

    @Column(name = "UZSAKYMO_DATA")
    private Date orderDate;

    @Column(name = "UZRASAI")
    private String notes;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "susidaro",
            joinColumns = { @JoinColumn(name = "P_UZSAKYMO_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PREKES_ID") }
    )
    Set<Products> product = new HashSet<>();

    public StockOrders(Employees employee, Suppliers supplier, Date orderDate, String notes) {
        this.employee = employee;
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
