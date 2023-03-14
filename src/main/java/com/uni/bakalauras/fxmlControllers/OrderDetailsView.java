package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Orders;

import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.util.MakeObservable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class OrderDetailsView {

    Orders selectedOrder;
    @FXML
    public Text orderNr = new Text();
    public Text client = new Text();
    public Text city = new Text();
    public Text address = new Text();
    public Text sum = new Text();

    public TableView<Have> tableOrderProducts;

    public TableColumn<Have, String> colType;
    public TableColumn<Have, String> colName;
    public TableColumn<Have, String> colMeasurement;
    public TableColumn<Have, String> colColor;
    public TableColumn<Have, Double> colCost;
    public TableColumn<Have, Integer> colQuantity;
    public TableColumn<Have, Double> colSum;



    public void setFormData(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;

        orderNr.setText(String.valueOf(selectedOrder.getId()));
        client.setText(selectedOrder.getClientName());
        city.setText(selectedOrder.getOrderCity());
        address.setText(selectedOrder.getOrderAddress());
        sum.setText(String.valueOf(selectedOrder.getSum()));

        fillTable();


    }

    public void fillTable() {
        colType.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("ProductMeasurements"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("ProductColor"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("ProductCost"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("Sum"));
        try {
            tableOrderProducts.setItems((ObservableList<Have>) MakeObservable.getOrdersProducts(selectedOrder.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
