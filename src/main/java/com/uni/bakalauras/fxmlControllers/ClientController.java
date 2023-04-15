package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.util.MakeObservable;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    public ClientController clientController;

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

    public void openCreateClient(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("createClient-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        CreateClientController createClientController = loader.getController();
        createClientController.setController(createClientController, clientController);
        stage.initModality(Modality.NONE);
        stage.setTitle("Naujas uzsakymas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void openUpdateClient(ActionEvent actionEvent) throws IOException {
        if (tableClients.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite klienta");
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("createClient-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            CreateClientController createClientController = loader.getController();
            createClientController.setController(createClientController, clientController);
            createClientController.setOrder(tableClients.getSelectionModel().getSelectedItem());

            stage.initModality(Modality.NONE);
            stage.setTitle("Naujas uzsakymas");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
