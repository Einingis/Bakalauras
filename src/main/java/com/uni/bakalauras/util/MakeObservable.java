package com.uni.bakalauras.util;

import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Places;
import com.uni.bakalauras.model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MakeObservable {

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

    public static ObservableList<Places>  MakePlacesListObservable(List PlacesList) {
        return FXCollections.observableArrayList(PlacesList);
    }
}
