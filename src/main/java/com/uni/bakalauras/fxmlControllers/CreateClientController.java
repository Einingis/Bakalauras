package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.scripts.Create;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateClientController {

    public TextField fldName;
    public TextField fldSurname;
    public TextField fldNumber;
    public TextField fldCity;
    public TextField fldAddress;

    public CreateClientController createClientController;
    public ClientController clientController;

    public Clients client = new Clients();
    public Button btnCreateUpdate;

    public void setController(CreateClientController createClientController, ClientController clientController) {
        this.createClientController = createClientController;
        this.clientController = clientController;
        btnCreateUpdate.setText("Sukurti");
    }

    public void createClient(ActionEvent actionEvent) {
        if (Objects.equals(fldName.getText(), "")) {
            PopupOperations.alertMessage("iveskite vardą");
        }
        else if (!ClientsOperations.findByFullName(fldName.getText(), fldSurname.getText()).isPresent()) {
            String text;

            client.setName(fldName.getText());
            client.setSurname(fldSurname.getText());
            client.setNumber(fldNumber.getText());
            client.setCity(fldCity.getText());
            client.setAddress(fldAddress.getText());

            List<Clients> clientsList = new ArrayList<>();
            clientsList.add(client);

            Create.createAllInList(clientsList);

            if (client.getId() == null) {
                text = "sukurtas";
            }
            else {
                text = "pakeistas";
            }

            PopupOperations.alertMessage("klientas" + " '" + fldName.getText() + " " + fldSurname.getText() + "' " + text);

            fldName.clear();
            fldSurname.clear();
            fldNumber.clear();
            fldCity.clear();
            fldAddress.clear();

            clientController.fillTable();
        }
        else {
            PopupOperations.alertMessage("Toks klientas jau egzistuoja");
        }
    }

    public void setOrder(Clients client) {
        this.client = client;

        fldName.setText(client.getName());
        fldSurname.setText(client.getSurname());
        fldNumber.setText(client.getNumber());
        fldCity.setText(client.getCity());
        fldAddress.setText(client.getAddress());

        btnCreateUpdate.setText("Pakeisti");
    }
}
