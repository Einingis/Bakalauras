package com.uni.bakalauras.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "grazinimas")
public class Returns {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GRAZINIMOID", nullable = false, unique = true)
    private Long id;

    @OneToOne(orphanRemoval = true, mappedBy = "returns")
    private Orders order;

    @Column(name = "GRAZINIMODATA")
    private Date date;

    @Column(name = "GRAZINIMOSUMA")
    private Double sum;

    @Column(name = "GRAZINIMOBUSENA")
    private String status;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "returns")
    private Set<Returning> returning;

    public Returns() {
    }

    public Returns(Orders order, Date date, Double sum, String status, Set<Returning> returning) {
        this.order = order;
        this.date = date;
        this.sum = sum;
        this.status = status;
        this.returning = returning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Returning> getReturning() {
        return returning;
    }

    public void setReturning(Set<Returning> returning) {
        this.returning = returning;
    }
}
