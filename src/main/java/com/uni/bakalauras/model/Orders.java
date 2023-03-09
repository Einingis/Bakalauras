package com.uni.bakalauras.model;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "uzsakymas")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UZSAKYMOID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name="KLIENTOID", nullable=false)
    private Clients client;

    @ManyToOne
    @JoinColumn(name="DARBUOTOJOID", nullable=false)
    private Employees employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GRAZINIMOID")
    private Returns returns;

    @Column(name = "BUSENA")
    private String status;

    @Column(name = "APMOKETAS")
    private Boolean payedFor;

    @Column(name = "PRISTATYMOADRESAS")
    private String orderAddress;

    @Column(name = "PRISTATYMOBUDAS")
    private String deliveryType;

    @Column(name = "DATA")
    private Date created;

    @Column(name = "UZRASAI")
    private String note;

    @Column(name = "SUMA")
    private Double sum;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private Set<Have> have;

    public Orders() {
    }

    public Orders(Clients client, Employees employee, Returns returns, String status, Boolean payedFor,
                  String orderAddress, String deliveryType, Date created, String note, Double sum, Set<Have> have) {
        this.client = client;
        this.employee = employee;
        this.returns = returns;
        this.status = status;
        this.payedFor = payedFor;
        this.orderAddress = orderAddress;
        this.deliveryType = deliveryType;
        this.created = created;
        this.note = note;
        this.sum = sum;
        this.have = have;
    }

    public Orders(Clients client, Employees employee, String status, Boolean payedFor, String orderAddress,
                  String deliveryType, Date created, String note, Double sum, Set<Have> have) {
        this.client = client;
        this.employee = employee;
        this.status = status;
        this.payedFor = payedFor;
        this.orderAddress = orderAddress;
        this.deliveryType = deliveryType;
        this.created = created;
        this.note = note;
        this.sum = sum;
        this.have = have;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public String getClientName() {
        return getClient().getName();
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Returns getReturns() {
        return returns;
    }

    public void setReturns(Returns returns) {
        this.returns = returns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPayedFor() {
        return payedFor;
    }

    public void setPayedFor(Boolean payedFor) {
        this.payedFor = payedFor;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Set<Have> getHave() {
        return have;
    }

    public void setHave(Set<Have> have) {
        this.have = have;
    }
}
