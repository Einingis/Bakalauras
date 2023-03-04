package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void clickOrders() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("orders-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.NONE);
        stage.setTitle("Uzsakymai");
        stage.setScene(new Scene(root));
        stage.show();
    }
}