package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.fxmlModel.OrderList;
import com.uni.bakalauras.util.MakeObservable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class UzsakymaiController implements Initializable {

    @FXML
    private TableView<OrderList> tableOrders;

    public TableColumn<OrderList, Long> colId;
    public TableColumn<OrderList, Date> colData;
    public TableColumn<OrderList, String> colClient;
    public TableColumn<OrderList, String> colAddress;
    public TableColumn<OrderList, Boolean> colPayed;
    public TableColumn<OrderList, Double> colSum;
    public TableColumn<OrderList, String> colStatus;
    public TableColumn<OrderList, String> colDeliveryType;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FillTable();
    }

    public void FillTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colData.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("OrderAddress"));
        colPayed.setCellValueFactory(new PropertyValueFactory<>("Payed"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("OrderSum"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
        colDeliveryType.setCellValueFactory(new PropertyValueFactory<>("OrderDeliveryType"));
        try {
            tableOrders.setItems(MakeObservable.GetAllRecipeList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
