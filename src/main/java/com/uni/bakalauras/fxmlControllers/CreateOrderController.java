package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.*;
import com.uni.bakalauras.scripts.Create;
import com.uni.bakalauras.scripts.Delete;
import com.uni.bakalauras.util.MakeObservable;
import com.uni.bakalauras.util.PopupOperations;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static java.time.LocalDate.now;

public class CreateOrderController implements Initializable {

    public TextField fldCity;
    public TextField fldAddress;
    public TextField fldDeliveryType;
    public ComboBox<String> cbClient;
    public TextField fldClient;

    public TableView<Products> tableOrderProducts;
    public TableColumn<Products, Long> colId;
    public TableColumn<Products, String> colGroup;
    public TableColumn<Products, String> colName;
    public TableColumn<Products, String> colColor;
    public TableColumn<Products, String> colMeasurement;
    public TableColumn<Products, Double> colSellCost;
    public TableColumn<Products, Integer> colStock;

    private static CreateOrderController createOrderController;
    private static OrderController orderController;

    private static Boolean statusUpdate;

    static List<Products> productsList = new ArrayList<>();
    static List<Clients> clientsList = new ArrayList<>();
    static Orders updateOrder;
    static Employees employee;

    public Button btnCreateUpdate;
    public Button btnDelete;
    public Button btnChangeQuantity;


    public void setController(CreateOrderController createOrderController, OrderController orderController, Employees employee) {
        btnCreateUpdate.setText("Sukurti");
        statusUpdate = false;
        this.createOrderController = createOrderController;
        this.orderController = orderController;
        this.employee = employee;
    }

    public void addProduct(Products selectedItem) {
        productsList.add(selectedItem);
        tableOrderProducts.setItems(MakeObservable.MakeProductListObservable(productsList));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientsList = ClientsOperations.findAllClients();

        clientsList.forEach(clients1 ->cbClient.getItems().add(clients1.getFullName()));

        try {
            fillTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOrder(Orders updateOrder) throws SQLException {
        this.updateOrder = updateOrder;
        btnCreateUpdate.setText("Pakeisti");

        statusUpdate = true;

        fldClient.setText(updateOrder.getClientName());
        fldCity.setText(updateOrder.getOrderCity());
        fldAddress.setText(updateOrder.getOrderAddress());
        fldDeliveryType.setText(updateOrder.getDeliveryType());

        productsList.clear();

        for (Have have : OrdersOperations.findOrdersProduct(updateOrder.getId())) {
            have.getProduct().setInStock(have.getQuantity());
            productsList.add(have.getProduct());
        }
        fillTable();
    }

    public void fillTable() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colGroup.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("Measurement"));
        colSellCost.setCellValueFactory(new PropertyValueFactory<>("SellCost"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("InStock"));

        tableOrderProducts.setItems(MakeObservable.MakeProductListObservable(productsList));
    }

    public void clientSelected(ActionEvent actionEvent) {
        fldClient.setText(cbClient.getValue());
    }

    public void showClientsByText(KeyEvent keyEvent) {
        cbClient.hide();
        List<Clients> clients = ClientsOperations.findByNamePart(fldClient.getText());
        ArrayList<String> clientNames = new ArrayList<>();
        clients.stream().forEach(clients1 -> clientNames.add(clients1.getFullName()));
        cbClient.getItems().clear();
        cbClient.getItems().addAll(clientNames);
        cbClient.show();

    }

    public void openProductList(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("products-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        ProductsController productsController = loader.getController();
        productsController.setUpFromOrder("CreateOrder", createOrderController, productsList);

        stage.initModality(Modality.NONE);
        stage.setTitle("Prekės");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void CreateOrder(ActionEvent actionEvent) {

        Double OrderSum = 0.0;
        Clients client;

        for (Products product : productsList) {
            OrderSum += product.getSellCost() * product.getInStock();
        }

        if (clientsList.stream().filter(c -> Objects.equals(c.getFullName(), fldClient.getText())).findFirst().isEmpty()) {

            String[] parts = fldClient.getText().split(" ", 2);
            client = new Clients(parts[0], parts[1], fldCity.getText(), fldAddress.getText());

            List<Clients> clientsList = new ArrayList<>();

            clientsList.add(client);

            Create.createAllInList(clientsList);

            client = ClientsOperations.findByFullNameAlways(client.getName(), client.getSurname());

        }
        else {
            client = clientsList.stream().filter(c -> Objects.equals(c.getFullName(), fldClient.getText())).findFirst().get();
        }

        if (statusUpdate) {
            updateOrder.setClient(client);
            updateOrder.setEmployee(employee);
            updateOrder.setStatus("sukurtas");
            updateOrder.setPayedFor(false);
            updateOrder.setOrderCity(fldCity.getText());
            updateOrder.setOrderAddress(fldAddress.getText());
            updateOrder.setDeliveryType(fldDeliveryType.getText());
            updateOrder.setSum(OrderSum);
            Delete.delete(OrdersOperations.findOrdersProduct(updateOrder.getId()));
        }
        else {
            updateOrder = new Orders(client,
                    employee,
                    "sukurtas",
                    false,
                    fldCity.getText(),
                    fldAddress.getText(),
                    fldDeliveryType.getText(),
                    now(),
                    OrderSum);
        }
        List<Orders> ordersList = new ArrayList<>();

        ordersList.add(updateOrder);

        Create.createAllInList(ordersList);

        List<Have> haveList = new ArrayList<>();

        for (Products product : productsList) {
            Have h = new Have();
            h.setProduct(product);
            h.setOrder(updateOrder);
            h.setQuantity(product.getInStock());

            haveList.add(h);
        }
            Create.createAllInList(haveList);

        orderController.fillTable();

        productsList.clear();
        fldClient.clear();
        fldAddress.clear();
        fldCity.clear();
        fldDeliveryType.clear();

        tableOrderProducts.setItems(MakeObservable.MakeProductListObservable(productsList));

    }

    public void deleteProduct(ActionEvent actionEvent) {
        if (tableOrderProducts.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite produkta");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("pasalinti produkta ");

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
                    productsList.remove(tableOrderProducts.getSelectionModel().getSelectedItem());
                    tableOrderProducts.setItems(MakeObservable.MakeProductListObservable(productsList));
                }
                return null;
            });
            dialog.showAndWait();
        }
    }

    public void ChangeQuantity(ActionEvent actionEvent) {
        if (tableOrderProducts.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite produkta");
        } else {

            Products selectedItem = tableOrderProducts.getSelectionModel().getSelectedItem();

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Pakeisti kieki");

            ButtonType btnConfirm = new ButtonType("Pakeisti", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType("atšaukti", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(btnConfirm, btnCancel);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField quantity = new TextField(String.valueOf(selectedItem.getInStock()));
            quantity.setPromptText(String.valueOf(selectedItem.getInStock()));

            grid.add(new Label("Kiekis:"), 0, 0);
            grid.add(quantity, 1, 0);

            Node confirm = dialog.getDialogPane().lookupButton(btnConfirm);
            confirm.setDisable(true);


            quantity.textProperty().addListener((observable, oldValue, newValue) -> {
                confirm.setDisable(newValue.trim().isEmpty());
                if (!newValue.matches("\\d*")) {
                    quantity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });

            dialog.getDialogPane().setContent(grid);


            Platform.runLater(() -> quantity.requestFocus());


            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == btnConfirm) {

                    selectedItem.setInStock(Integer.valueOf(quantity.getText()));
                    tableOrderProducts.refresh();
                }
                return null;
            });
            dialog.showAndWait();
        }
    }
}
