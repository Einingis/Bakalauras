package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.model.Employees;
import com.uni.bakalauras.hibernateOperations.Create;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsController {
    public Text textName;
    public PasswordField fldOldPassword;
    public PasswordField fldNewPassword;
    public PasswordField fldNewRepeat;
    
    private static Employees employee;

    public void setEmployee(Employees employee) {
        this.employee = employee;

        textName.setText(employee.getName());
    }

    public void changePassword(ActionEvent actionEvent) {
        if (!Objects.equals(employee.getPassword(), fldOldPassword.getText())) {
            PopupOperations.alertMessage("Neteisingas slaptažodis");
            return;
        }
        if (!Objects.equals(fldNewPassword.getText(), fldNewRepeat.getText())) {
            PopupOperations.alertMessage("Naujas slaptažodis nesutampa");
            return;
        }

        employee.setPassword(fldNewPassword.getText());
        List<Employees> employeesList = new ArrayList<>();

        employeesList.add(employee);

        Create.createAllInList(employeesList);

        PopupOperations.alertMessage("Slaptažodis pakeistas");
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Parent root = loader.load();

        HelloController helloController = loader.getController();
        helloController.setEmployee(employee);

        Stage stage = (Stage) fldOldPassword.getScene().getWindow();
        stage.setTitle("Meniu");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
