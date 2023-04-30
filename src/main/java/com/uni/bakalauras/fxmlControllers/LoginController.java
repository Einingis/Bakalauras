package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.EmployeesOperations;
import com.uni.bakalauras.model.Employees;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginController {
    public TextField fldName;
    public PasswordField fldPassword;

    private static Optional<Employees> employee;

    public void Login(ActionEvent actionEvent) throws IOException {
         employee = EmployeesOperations.findEmployeesLogin(fldName.getText(), fldPassword.getText());

        if (employee.isEmpty()) {
            PopupOperations.alertMessage("neteisingas vardas arba slaptažodis");
        }
        else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
            Parent root = loader.load();

            HelloController helloController = loader.getController();
            helloController.setEmployee(employee.get());

            Stage stage = (Stage) fldName.getScene().getWindow();
            stage.setTitle("Uzsakymai");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
