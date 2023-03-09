package com.uni.bakalauras.util;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.hibernateOperations.FindAll;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MakeObservable {

    private static Session session;


    public static ObservableList<Orders> GetAllOrderList() throws SQLException {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();
        OrdersOperations ordersOperations = new OrdersOperations(session);

        List<Orders> orders = OrdersOperations.findAllOrders();
        ArrayList<Orders> orderList = new ArrayList<>(orders);


        ObservableList<Orders> observableList = FXCollections.observableArrayList(orderList);
        return observableList;
    }
}
