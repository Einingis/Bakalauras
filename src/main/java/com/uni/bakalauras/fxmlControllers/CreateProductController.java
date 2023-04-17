package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.hibernateOperations.ClientsOperations;
import com.uni.bakalauras.hibernateOperations.GroupsOperations;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Groups;
import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.scripts.Create;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateProductController implements Initializable {
    public ComboBox<String> cbType;
    public TextField fldType;

    public TextField fldName;
    public TextField fldColor;
    public TextField fldMeasurement;
    public TextField fldPrimeCost;
    public TextField fldPrice;
    public TextField fldStock;

    static List<Groups> groupsList = new ArrayList<>();

    public ProductsController productsController;
    public CreateProductController createProductController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupsList = GroupsOperations.findAllGroups();

        groupsList.forEach(group ->cbType.getItems().add(group.getName()));
    }

    public void setController(ProductsController productsController, CreateProductController createProductController) {
        this.productsController = productsController;
        this.createProductController = createProductController;
    }

    public void typeSelected(ActionEvent actionEvent) {
        fldType.setText(cbType.getValue());
    }

    public void showProductByText(KeyEvent keyEvent) {
        cbType.hide();
        List<Groups> groups = GroupsOperations.findByNamePart(fldType.getText());
        ArrayList<String> groupsNames = new ArrayList<>();
        groups.stream().forEach(group -> groupsNames.add(group.getName()));
        cbType.getItems().clear();
        cbType.getItems().addAll(groupsNames);
        cbType.show();
    }

    public void createProduct(ActionEvent actionEvent) {

        Groups group;

        if (groupsList.stream().filter(g -> Objects.equals(g.getName(), fldType.getText())).findFirst().isEmpty()) {

            group = new Groups(fldType.getText());

            List<Groups> groupsList = new ArrayList<>();

            groupsList.add(group);

            Create.createAllInList(groupsList);

            group = GroupsOperations.findByNamePart(fldType.getText()).stream().findFirst().get();

        }
        else {
            group = groupsList.stream().filter(g -> Objects.equals(g.getName(), fldType.getText())).findFirst().get();
        }

        Products product = new Products();
        product.setGroup(group);
        product.setName(fldName.getText());
        product.setColor(fldColor.getText());
        product.setMeasurement(fldMeasurement.getText());
        product.setPrimeCost(Double.valueOf(fldPrimeCost.getText()));
        product.setSellCost(Double.valueOf(fldPrice.getText()));
        product.setInStock(Integer.valueOf(fldStock.getText()));

        List<Products> productsList = new ArrayList<>();
        productsList.add(product);

        Create.createAllInList(productsList);

        productsController.fillTable();

        fldType.clear();
        fldName.clear();
        fldColor.clear();
        fldMeasurement.clear();
        fldPrimeCost.clear();
        fldPrice.clear();
        fldStock.clear();
    }

}
