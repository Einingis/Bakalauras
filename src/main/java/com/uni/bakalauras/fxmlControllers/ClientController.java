package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.hibernateOperations.Delete;
import com.uni.bakalauras.util.MakeObservable;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
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
    public TextField fldName;
    public TextField fldSurname;
    public TextField fldNumber;
    public TextField fldCity;
    public TextField fldAddress;

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
        stage.setTitle("Naujas užsakymas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void openUpdateClient(ActionEvent actionEvent) throws IOException {
        if (tableClients.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite klientą");
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("createClient-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            CreateClientController createClientController = loader.getController();
            createClientController.setController(createClientController, clientController);
            createClientController.setOrder(tableClients.getSelectionModel().getSelectedItem());

            stage.initModality(Modality.NONE);
            stage.setTitle("Naujas užsakymas");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteClient(ActionEvent actionEvent) {
        if (tableClients.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite klientą");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("pašalinti klientą ");

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
                    List<Clients> deleteList = new ArrayList<>();
                    deleteList.add(tableClients.getSelectionModel().getSelectedItem());
                    Delete.delete(deleteList);
                    clientList.remove(tableClients.getSelectionModel().getSelectedItem());

                    tableClients.setItems(MakeObservable.MakeClientsListObservable(clientList));
                }
                return null;
            });
            dialog.showAndWait();
        }
    }

    public void filterClients(ActionEvent actionEvent) {
        List<String> filters = new ArrayList<>();

        filters.add(fldName.getText());
        filters.add(fldSurname.getText());
        filters.add(fldNumber.getText());
        filters.add(fldCity.getText());
        filters.add(fldAddress.getText());

        clientList.clear();
        clientList = ClientsOperations.findClientsByFilters(filters);

        tableClients.setItems(MakeObservable.MakeClientsListObservable(clientList));
    }
}
