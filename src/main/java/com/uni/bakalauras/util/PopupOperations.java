package com.uni.bakalauras.util;

import javafx.scene.control.Alert;

public class PopupOperations {

    public static void alertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setContentText(message);
        alert.showAndWait();

    }


}
