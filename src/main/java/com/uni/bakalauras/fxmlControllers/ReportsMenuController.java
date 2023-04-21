package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsMenuController {


    public void sellsReport(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sellReport-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        SellReportController sellReportController = loader.getController();

        stage.initModality(Modality.NONE);
        stage.setTitle("Klientai");
        stage.setScene(new Scene(root));
        stage.show();

    }
}
