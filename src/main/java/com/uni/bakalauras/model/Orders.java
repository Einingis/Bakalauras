package com.uni.bakalauras.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "uzsakymas")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Uzsakymo_NR_", nullable = false, unique = true)
    private Long id;
    private Long employeeId;
    private Long clientID;
    @Column(name = "BUSENA")
    private String status;
    @Column(name = "SUKURTAS")
    private LocalDateTime created;
    @Column(name = "ATNAUJINTAS")
    private LocalDateTime updated;
    @Column(name = "UZRASAI")
    private String note;
    @Column(name = "PRISTATYMO_ADRESAS")
    private String orderAddress;
    @Column(name = "APMOKETAS")
    private Boolean payedFor;
    @Column(name = "PRISTATYMO_BUDAS")
    private String deliveryType;

    public Orders() {
    }

    public Orders(Long id, Long employeeId, Long clientID, String status, LocalDateTime created, LocalDateTime updated,
                  String note, String orderAddress, Boolean payedFor, String deliveryType) {
        this.id = id;
        this.employeeId = employeeId;
        this.clientID = clientID;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.note = note;
        this.orderAddress = orderAddress;
        this.payedFor = payedFor;
        this.deliveryType = deliveryType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Boolean getPayedFor() {
        return payedFor;
    }

    public void setPayedFor(Boolean payedFor) {
        this.payedFor = payedFor;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
