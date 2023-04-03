package com.uni.bakalauras.util;

import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class MakeObservable {

    private static Session session;

//    public static ObservableList<Have> getOrdersProducts(Long OrderId) throws SQLException {
//        session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
//        OrdersOperations ordersOperations = new OrdersOperations(session);
//        List<Have> have = OrdersOperations.findOrdersProduct(OrderId);
//        ArrayList<Have> haveList = new ArrayList<>(have);
//
//        ObservableList<Have> observableList = FXCollections.observableArrayList(haveList);
//        return observableList;
//    }

    public static ObservableList<Products> GetAllProductsList(List products) throws SQLException {
        return FXCollections.observableArrayList(products);
    }

    public static ObservableList<Products> MakeProductListObservable(List list) {
        return FXCollections.observableArrayList(list);
    }

    public static ObservableList<Clients> MakeClientsListObservable(List list) {
        return FXCollections.observableArrayList(list);
    }

    public static ObservableList<Orders>  MakeOrderListObservable(List orderList) {
        return FXCollections.observableArrayList(orderList);
    }
}
