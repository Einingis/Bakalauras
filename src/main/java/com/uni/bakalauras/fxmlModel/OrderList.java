package com.uni.bakalauras.fxmlModel;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;

public class OrderList {

    private SimpleLongProperty orderId;
    private SimpleDateFormat orderDate;
    private SimpleStringProperty clientName;
    private SimpleStringProperty orderAddress;
    private SimpleBooleanProperty payed;
    private SimpleDoubleProperty orderSum;
    private SimpleStringProperty orderStatus;
    private SimpleStringProperty orderDeliveryType;

    public OrderList() {
    }

    public OrderList(SimpleLongProperty orderId, SimpleDateFormat orderDate, SimpleStringProperty clientName,
                     SimpleStringProperty orderAddress, SimpleBooleanProperty payed, SimpleDoubleProperty orderSum,
                     SimpleStringProperty orderStatus, SimpleStringProperty orderDeliveryType) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.clientName = clientName;
        this.orderAddress = orderAddress;
        this.payed = payed;
        this.orderSum = orderSum;
        this.orderStatus = orderStatus;
        this.orderDeliveryType = orderDeliveryType;
    }

    public long getOrderId() {
        return orderId.get();
    }

    public SimpleLongProperty orderIdProperty() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId.set(orderId);
    }

    public SimpleDateFormat getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(SimpleDateFormat orderDate) {
        this.orderDate = orderDate;
    }

    public String getClientName() {
        return clientName.get();
    }

    public SimpleStringProperty clientNameProperty() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName.set(clientName);
    }

    public String getOrderAddress() {
        return orderAddress.get();
    }

    public SimpleStringProperty orderAddressProperty() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress.set(orderAddress);
    }

    public boolean isPayed() {
        return payed.get();
    }

    public SimpleBooleanProperty payedProperty() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed.set(payed);
    }

    public double getOrderSum() {
        return orderSum.get();
    }

    public SimpleDoubleProperty orderSumProperty() {
        return orderSum;
    }

    public void setOrderSum(double orderSum) {
        this.orderSum.set(orderSum);
    }

    public String getOrderStatus() {
        return orderStatus.get();
    }

    public SimpleStringProperty orderStatusProperty() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus.set(orderStatus);
    }

    public String getOrderDeliveryType() {
        return orderDeliveryType.get();
    }

    public SimpleStringProperty orderDeliveryTypeProperty() {
        return orderDeliveryType;
    }

    public void setOrderDeliveryType(String orderDeliveryType) {
        this.orderDeliveryType.set(orderDeliveryType);
    }
}
