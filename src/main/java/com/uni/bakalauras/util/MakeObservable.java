package com.uni.bakalauras.util;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.hibernateOperations.ProductsOperations;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Products;
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

    public static ObservableList<Have> getOrdersProducts(Long OrderId) throws SQLException {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();
        OrdersOperations ordersOperations = new OrdersOperations(session);
        List<Have> have = OrdersOperations.findOrdersProduct(OrderId);
        ArrayList<Have> haveList = new ArrayList<>(have);

        ObservableList<Have> observableList = FXCollections.observableArrayList(haveList);
        return observableList;
    }

    public static ObservableList<Products> GetAllProductsList() throws SQLException {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();
        ProductsOperations productsOperations = new ProductsOperations(session);

        List<Products> products = ProductsOperations.findAllProducts();
        ArrayList<Products> productsList = new ArrayList<>(products);

        ObservableList<Products> observableList = FXCollections.observableArrayList(productsList);
        return observableList;
    }

    public static ObservableList<Products> MakeProductListObservable(List list) {
        ObservableList<Products> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

    public static ObservableList<Clients> MakeClientsListObservable(List list) {
        ObservableList<Clients> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }
}
