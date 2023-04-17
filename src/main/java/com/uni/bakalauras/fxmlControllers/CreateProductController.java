package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.GroupsOperations;
import com.uni.bakalauras.hibernateOperations.ProductsOperations;
import com.uni.bakalauras.model.*;
import com.uni.bakalauras.scripts.Create;
import com.uni.bakalauras.util.MakeObservable;
import com.uni.bakalauras.util.PopupOperations;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    public Button btnCreate;

    public TableView<Places> tablePlaces;

    public TableColumn<Places, String> clmWarehouse;
    public TableColumn<Places, String> clmShelf;
    public TableColumn<Places, String> clmPlace;
    public TableColumn<Stored, Integer> clmPlaced;

    static List<Groups> groupsList = new ArrayList<>();
    static Products product;

    private List<Places> placesList = new ArrayList<>();
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
        btnCreate.setText("Sukurti");
    }

    public void fillTable() {
        clmWarehouse.setCellValueFactory(new PropertyValueFactory<>("Warehouse"));
        clmShelf.setCellValueFactory(new PropertyValueFactory<>("Shelf"));
        clmPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));
        clmPlaced.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        tablePlaces.setItems(MakeObservable.MakePlacesListObservable(placesList));

    }

    public void setProduct(Products product) {
        this.product = product;
        btnCreate.setText("Pakeisti");

        fldType.setText(product.getGroup().getName());
        fldName.setText(product.getName());
        fldColor.setText(product.getColor());
        fldMeasurement.setText(product.getMeasurement());
        fldPrimeCost.setText(String.valueOf(product.getPrimeCost()));
        fldPrice.setText(String.valueOf(product.getSellCost()));
        fldStock.setText(String.valueOf(product.getInStock()));

        for (Stored stored : ProductsOperations.findProductPlaces(product.getId())) {
            stored.getPlace().setQuantity(stored.getQuantity());
            placesList.add(stored.getPlace());
        }

        fillTable();
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

        List<Stored> storedList = new ArrayList<>();

        for (Places place : placesList) {
            Stored s = new Stored();
            s.setProduct(product);
            s.setPlace(place);
            s.setQuantity(place.getQuantity());

            storedList.add(s);
        }
        Create.createAllInList(storedList);

        productsController.fillTable();

        placesList.clear();

        tablePlaces.setItems(MakeObservable.MakePlacesListObservable(placesList));

        fldType.clear();
        fldName.clear();
        fldColor.clear();
        fldMeasurement.clear();
        fldPrimeCost.clear();
        fldPrice.clear();
        fldStock.clear();
    }

    public void openAddPlaces(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("places-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        PlacesController placesController = loader.getController();
        placesController.setUpFromProduct("CreateProduct", createProductController, placesList);

        stage.initModality(Modality.NONE);
        stage.setTitle("Prekės");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addPlace(Places selectedItem) {
        placesList.add(selectedItem);
        tablePlaces.setItems(MakeObservable.MakePlacesListObservable(placesList));
    }

    public void updatePlaced(ActionEvent actionEvent) throws IOException {
        if (tablePlaces.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite Vietą");
        } else {

            Places selectedItem = tablePlaces.getSelectionModel().getSelectedItem();

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Pakeisti kieki");

            ButtonType btnConfirm = new ButtonType("Pakeisti", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType("atšaukti", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(btnConfirm, btnCancel);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField quantity = new TextField(String.valueOf(selectedItem.getQuantity()));
            quantity.setPromptText(String.valueOf(selectedItem.getQuantity()));

            grid.add(new Label("Kiekis:"), 0, 0);
            grid.add(quantity, 1, 0);

            Node confirm = dialog.getDialogPane().lookupButton(btnConfirm);
            confirm.setDisable(true);


            quantity.textProperty().addListener((observable, oldValue, newValue) -> {
                confirm.setDisable(newValue.trim().isEmpty());
                if (!newValue.matches("\\d*")) {
                    quantity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });

            dialog.getDialogPane().setContent(grid);


            Platform.runLater(() -> quantity.requestFocus());


            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == btnConfirm) {

                    selectedItem.setQuantity(Integer.valueOf(quantity.getText()));
                    tablePlaces.refresh();
                }
                return null;
            });
            dialog.showAndWait();
        }
    }

    public void deletePlace(ActionEvent actionEvent) {
        if (tablePlaces.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite Vietą");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("pašalinti Vietą ");

            ButtonType btnConfirm = new ButtonType("pasalinti", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType("atšaukti", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(btnConfirm, btnCancel);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == btnConfirm) {
                    placesList.remove(tablePlaces.getSelectionModel().getSelectedItem());
                    tablePlaces.setItems(MakeObservable.MakePlacesListObservable(placesList));
                }
                return null;
            });
            dialog.showAndWait();
        }
    }
}
