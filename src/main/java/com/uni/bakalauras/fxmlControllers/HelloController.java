package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.model.Employees;
import com.uni.bakalauras.scripts.RefactorDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private static Employees employee;
    public Button btnLogoff;

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    @FXML
    protected void clickOrders() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("orders-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        OrderController orderController = loader.getController();
        orderController.setController(orderController);

        stage.initModality(Modality.NONE);
        stage.setTitle("Uzsakymai");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void update(ActionEvent actionEvent) {
        RefactorDataBase.refactor();

    }

    public void clickProducts(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("products-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        ProductsController productsController = loader.getController();
        productsController.setUpFromMain("main", productsController);

        stage.initModality(Modality.NONE);
        stage.setTitle("Prekės");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void logoff(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btnLogoff.getScene().getWindow();

        stage.setTitle("prisijungti!");
        stage.setScene(scene);
        stage.show();

    }

    public void openSettings(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnLogoff.getScene().getWindow();

        SettingsController settingsController = loader.getController();
        settingsController.setEmployee(employee);

        stage.setTitle("Nustatymai");
        stage.setScene(scene);
        stage.show();
    }


    public void openClients(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("clients-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        ClientController clientController = loader.getController();

        stage.initModality(Modality.NONE);
        stage.setTitle("Klientai");
        stage.setScene(new Scene(root));
        stage.show();
    }
}