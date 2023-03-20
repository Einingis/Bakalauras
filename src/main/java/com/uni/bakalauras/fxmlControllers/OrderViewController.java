package com.uni.bakalauras.fxmlControllers;


import com.uni.bakalauras.Main;
import com.uni.bakalauras.util.MakeObservable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderViewController<Orders> implements Initializable {

    @FXML
    public Button btnCreate;
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

        fillTable();
    }

    public void fillTable() {
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

    public void openOrderDetails(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                Orders selectedItem = tableOrders.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("orderDetails-view.fxml"));
                Parent root = loader.load();
                OrderDetailsController orderDetailsView = loader.getController();
                orderDetailsView.setFormData((com.uni.bakalauras.model.Orders) selectedItem);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Update");
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }

    public void openCreateWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("createOrder-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.NONE);
        stage.setTitle("Naujas uzsakymas");
        stage.setScene(new Scene(root));
        stage.show();

    }
}
