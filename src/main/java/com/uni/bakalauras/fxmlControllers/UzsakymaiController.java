package com.uni.bakalauras.fxmlControllers;


import com.uni.bakalauras.util.MakeObservable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class UzsakymaiController<Orders> implements Initializable {

    @FXML
    private TableView<Orders> tableOrders;

    public TableColumn<Orders, Long> colId;
    public TableColumn<Orders, Date> colData;
    public TableColumn<Orders, String> colClient;
    public TableColumn<Orders, String> colAddress;
    public TableColumn<Orders, Boolean> colPayed;
    public TableColumn<Orders, Double> colSum;
    public TableColumn<Orders, String> colStatus;
    public TableColumn<Orders, String> colDeliveryType;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FillTable();
    }

    public void FillTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Created"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("OrderAddress"));
        colPayed.setCellValueFactory(new PropertyValueFactory<>("PayedFor"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("Sum"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colDeliveryType.setCellValueFactory(new PropertyValueFactory<>("DeliveryType"));
        try {
            tableOrders.setItems((ObservableList<Orders>) MakeObservable.GetAllOrderList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
