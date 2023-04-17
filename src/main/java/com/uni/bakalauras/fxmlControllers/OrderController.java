package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.Employees;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.scripts.Delete;
import com.uni.bakalauras.util.MakeObservable;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    public Button btnCreate;

    public TextField startDateFilter;
    public TextField IdFilter;
    public TextField endDateFilter;
    public TextField clientFilter;
    public TextField addressFilter;
    public ComboBox<String> payedFilter;
    public TextField sumFilter;
    public TextField statusFilter;
    public TextField deliveryFilter;
    @FXML
    private TableView<Orders> tableOrders;

    public TableColumn<Orders, Long> colId;
    public TableColumn<Orders, Date> colData;
    public TableColumn<Orders, String> colClient;
    public TableColumn<Orders, String> colAddress;
    public TableColumn<Orders, String> colPayed;
    public TableColumn<Orders, Double> colSum;
    public TableColumn<Orders, String> colStatus;
    public TableColumn<Orders, String> colDeliveryType;

    private static OrderController orderController;
    private static Employees employee;

    static List<Orders> orderList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        payedFilter.getItems().add("Visi");
        payedFilter.getItems().add("Neapmoketi");
        payedFilter.getItems().add("Apmoketi");

        fillTable();
    }

    public void setController(OrderController orderController, Employees employee) {
        this.orderController = orderController;
        this.employee = employee;
    }

    public void fillTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Created"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("OrderAddress"));
        colPayed.setCellValueFactory(new PropertyValueFactory<>("PayedForString"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("Sum"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colDeliveryType.setCellValueFactory(new PropertyValueFactory<>("DeliveryType"));

        orderList = OrdersOperations.findAllOrders();

        tableOrders.setItems(MakeObservable.MakeOrderListObservable(orderList));
    }

    public void openOrderDetails(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                Orders selectedItem = tableOrders.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("orderDetails-view.fxml"));
                Parent root = loader.load();
                OrderDetailsController orderDetailsView = loader.getController();
                orderDetailsView.setFormData(selectedItem);
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

        CreateOrderController createOrderController = loader.getController();
        createOrderController.setController(createOrderController, orderController, employee);

        stage.initModality(Modality.NONE);
        stage.setTitle("Naujas uzsakymas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void filterOrders(ActionEvent actionEvent) {
        List<String> filters = new ArrayList<>();

        filters.add(IdFilter.getText());
        filters.add(startDateFilter.getText());
        filters.add(endDateFilter.getText());
        filters.add(clientFilter.getText());
        filters.add(addressFilter.getText());
        filters.add(payedFilter.getValue());
        filters.add(sumFilter.getText());
        filters.add(statusFilter.getText());
        filters.add(deliveryFilter.getText());

        orderList.clear();
        orderList = OrdersOperations.findOrderByFilters(filters);

        tableOrders.setItems(MakeObservable.MakeOrderListObservable(orderList));
    }

    public void openUpdateWindow(ActionEvent actionEvent) throws IOException, SQLException {
        if (tableOrders.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite Uzsakyma");
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("createOrder-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            CreateOrderController createOrderController = loader.getController();
            createOrderController.setController(createOrderController, orderController, employee);
            createOrderController.setOrder(tableOrders.getSelectionModel().getSelectedItem());

            stage.initModality(Modality.NONE);
            stage.setTitle("Naujas uzsakymas");
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    public void deleteOrder(ActionEvent actionEvent) {
        if (tableOrders.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite produkta");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("pašalinti Užsakymą");

            ButtonType btnConfirm = new ButtonType("pasalinti", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType("atšaukti", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(btnConfirm, btnCancel);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == btnConfirm) {
                    List<Orders> deleteList = new ArrayList<>();
                    deleteList.add(tableOrders.getSelectionModel().getSelectedItem());
                    Delete.delete(deleteList);
                    orderList.remove(tableOrders.getSelectionModel().getSelectedItem());

                    tableOrders.setItems(MakeObservable.MakeOrderListObservable(orderList));
                }
                return null;
            });
            dialog.showAndWait();
        }
    }
}
