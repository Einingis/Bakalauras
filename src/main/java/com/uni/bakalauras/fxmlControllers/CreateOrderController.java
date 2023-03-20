package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.model.Clients;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle; 

public class CreateOrderController implements Initializable {

    public TextField fldCity;
    public TextField fldAddress;
    public TextField fldDeliveryType;
    public ComboBox cbClient;
    public TextField fldClient;

    private static Session session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();
        ClientsOperations clientsOperations = new ClientsOperations(session);
        List<Clients> clients = ClientsOperations.findAllClients();
        ArrayList<String> clientNames = new ArrayList<>();
        clients.stream().forEach(clients1 -> clientNames.add(clients1.getFullName()));
        cbClient.getItems().addAll(clientNames);


    }

    public void clientSelected(ActionEvent actionEvent) {
        fldClient.setText(cbClient.getValue().toString());
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
}
