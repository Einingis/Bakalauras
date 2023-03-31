package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.hibernateOperations.ProductsOperations;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.util.MakeObservable;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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

    static List<Orders> orderList = new ArrayList<>();

    private static Session session;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        payedFilter.getItems().add("Visi");
        payedFilter.getItems().add("Neapmoketi");
        payedFilter.getItems().add("Apmoketi");

        fillTable();
    }

    public void setController(OrderController orderController) {
        this.orderController = orderController;
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

        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        OrdersOperations ordersOperations  = new OrdersOperations(session);

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

        CreateOrderController createOrderController = loader.getController();
        createOrderController.setController(createOrderController, orderController);

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
}
