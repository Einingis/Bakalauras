package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.Employees;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.hibernateOperations.Delete;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderController implements Initializable {

    @FXML
    public DatePicker startDateFilter;
    public TextField IdFilter;
    public DatePicker endDateFilter;
    public TextField clientFilter;
    public TextField addressFilter;
    public ComboBox<String> payedFilter;
    public TextField sumFilter;
    public TextField statusFilter;
    public TextField deliveryFilter;
    public Button btnCreate;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReturn;
    @FXML
    private TableView<Orders> tableOrders;

    public TableColumn<Orders, Long> colId;
    public TableColumn<Orders, Date> colData;
    public TableColumn<Orders, String> colClient;
    public TableColumn<Orders, String> colAddress;
    public TableColumn<Orders, String> colPayed;
    public TableColumn<Orders, String> colSum;
    public TableColumn<Orders, String> colStatus;
    public TableColumn<Orders, String> colDeliveryType;

    private static OrderController orderController;
    private static Employees employee;

    static List<Orders> orderList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        startDateFilter.setConverter(
                new StringConverter<>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });

        endDateFilter.setConverter(
                new StringConverter<>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });

        payedFilter.getItems().add("Visi");
        payedFilter.getItems().add("Neapmokėti");
        payedFilter.getItems().add("Apmokėti");

        btnReturn.setVisible(false);

        fillTable();
    }

    public void setController(OrderController orderController, Employees employee) {
        this.orderController = orderController;
        this.employee = employee;

        if (Objects.equals(employee.getPosition(), "Konsultantas")) {
            btnCreate.setVisible(true);
            btnDelete.setVisible(true);
        }
        else if (Objects.equals(employee.getPosition(), "Buhalteris")) {
            btnCreate.setVisible(false);
            btnDelete.setVisible(false);
        }
        else if (Objects.equals(employee.getPosition(), "Sandėlio darbuotojas" )) {
            btnCreate.setVisible(false);
            btnDelete.setVisible(false);
        }
    }

    public void fillTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Created"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("OrderAddress"));
        colPayed.setCellValueFactory(new PropertyValueFactory<>("PayedForString"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("SumToString"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colDeliveryType.setCellValueFactory(new PropertyValueFactory<>("DeliveryType"));

        orderList = OrdersOperations.findAllOrders();

        tableOrders.setItems(MakeObservable.MakeOrderListObservable(orderList));
    }

    public void openCreateWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("createOrder-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        CreateOrderController createOrderController = loader.getController();
        createOrderController.setController(createOrderController, orderController, employee);

        stage.initModality(Modality.NONE);
        stage.setTitle("Naujas užsakymas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void filterOrders(ActionEvent actionEvent) {
        String startDateString = "";
        String endDateString = "";

        if (!Objects.equals(startDateFilter.getValue(), null)) {
            startDateString = String.valueOf(startDateFilter.getValue());
            startDateFilter.setValue(null);
        }

        if (!Objects.equals(endDateFilter.getValue(), null)) {
            endDateString = String.valueOf(endDateFilter.getValue());
            endDateFilter.setValue(null);
        }

        List<String> filters = new ArrayList<>();

        filters.add(IdFilter.getText());
        filters.add(startDateString);
        filters.add(endDateString);
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
            PopupOperations.alertMessage("Pasirinkite užsakymą");
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("createOrder-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            CreateOrderController createOrderController = loader.getController();
            createOrderController.setController(createOrderController, orderController, employee);
            createOrderController.setOrder(tableOrders.getSelectionModel().getSelectedItem());

            stage.initModality(Modality.NONE);
            stage.setTitle("Naujas užsakymas");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteOrder(ActionEvent actionEvent) {
        if (tableOrders.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite produktą");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Pašalinti Užsakymą");

            ButtonType btnConfirm = new ButtonType("pašalinti", ButtonBar.ButtonData.OK_DONE);
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

    public void createReturn(ActionEvent actionEvent) throws IOException {
        Orders selectedItem = tableOrders.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("createReturns-view.fxml"));
        Parent root = loader.load();
        CreateReturnsController createReturnsController = loader.getController();
        createReturnsController.setFormData(selectedItem);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
