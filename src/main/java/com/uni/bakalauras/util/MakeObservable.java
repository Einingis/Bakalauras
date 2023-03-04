package com.uni.bakalauras.util;

import com.uni.bakalauras.fxmlModel.OrderList;
import com.uni.bakalauras.hibernateOperations.FindAll;
import com.uni.bakalauras.model.Orders;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MakeObservable {


    public static ObservableList<?> GetAllOrderList() throws SQLException {
        List<Orders> orders = FindAll.findAllOrders();
        ArrayList<OrderList> orderList = new ArrayList<>();

        for (Orders element : orders) {
            orderList.add(new OrderList(new SimpleLongProperty(element.getId()),
                    new SimpleDateFormat(element.getCreated()),
                                        element.getClient().getName(),
                                        element.getOrderAddress(),
                                        element.getPayedFor(),
                                        element.getSum(),
                                        element.getStatus(),
                                        element.getDeliveryType()));
       }

        ObservableList<?> observableList = FXCollections.observableArrayList();
        return observableList;
    }
}
