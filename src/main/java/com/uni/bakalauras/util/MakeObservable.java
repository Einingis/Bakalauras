package com.uni.bakalauras.util;

import com.uni.bakalauras.fxmlModel.OrderList;
import com.uni.bakalauras.hibernateOperations.FindAll;
import com.uni.bakalauras.model.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MakeObservable {


    public static ObservableList<Orders> GetAllOrderList() throws SQLException {
        List<Orders> orders = FindAll.findAllOrders();
        ArrayList<Orders> orderList = new ArrayList<>(orders);


        ObservableList<Orders> observableList = FXCollections.observableArrayList(orderList);
        return observableList;
    }
}
