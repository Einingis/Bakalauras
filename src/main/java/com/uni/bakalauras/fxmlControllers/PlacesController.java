package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.hibernateOperations.PlacesOperations;
import com.uni.bakalauras.model.Places;
import com.uni.bakalauras.util.MakeObservable;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlacesController {

    public TableView<Places> tablePlaces;

    public TableColumn<Places, String> clmWarehouse;
    public TableColumn<Places, String> clmShelf;
    public TableColumn<Places, String> clmPlace;

    public TextField fldQuantity;

    private CreateProductController createProductController;
    private List<Places> usedPlacesList;
    private List<Places> placesList = new ArrayList<>();

    public void setUpFromProduct(String createProduct, CreateProductController createProductController, List<Places> placesList) {
        this.createProductController = createProductController;
        this.usedPlacesList = placesList;

        fillTable();
    }

    public void fillTable() {
        clmWarehouse.setCellValueFactory(new PropertyValueFactory<>("Warehouse"));
        clmShelf.setCellValueFactory(new PropertyValueFactory<>("Shelf"));
        clmPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));

        placesList = PlacesOperations.findAllPlaces();

        for (Places placesIn : usedPlacesList) {
            placesList.removeIf(places -> Objects.equals(places.getId(), placesIn.getId()));
        }

        tablePlaces.setItems(MakeObservable.MakePlacesListObservable(placesList));
    }


    public void addPlaceMouse(MouseEvent mouseEvent) {
        if(fldQuantity.getText().equals("")) {
        }
        else if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                addPlace();
            }
        }
    }

    public void addPlaceBtn(ActionEvent actionEvent) {
        if(fldQuantity.getText().equals("")) {
        }
        else {
            addPlace();
        }
    }

    public void addPlace() {
        tablePlaces.getSelectionModel().getSelectedItem().setQuantity(Integer.valueOf(fldQuantity.getText()));
        createProductController.addPlace(tablePlaces.getSelectionModel().getSelectedItem());
        placesList.remove(tablePlaces.getSelectionModel().getSelectedItem());
        tablePlaces.setItems(MakeObservable.MakePlacesListObservable(placesList));
    }
}
