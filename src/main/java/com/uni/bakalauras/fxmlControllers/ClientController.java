package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.util.MakeObservable;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public TableColumn<Clients, String> colAddress;
    public TableColumn<Clients, String> colCity;
    public TableColumn<Clients, String> colNumber;
    public TableColumn<Clients, String> colSurname;
    public TableColumn<Clients, String> colName;

    static List<Clients> clientList = new ArrayList<>();
    public TableView<Clients> tableClients;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTable();
    }

    public void fillTable() {
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));

        clientList = ClientsOperations.findAllClients();

        tableClients.setItems(MakeObservable.MakeClientsListObservable(clientList));
    }
}
