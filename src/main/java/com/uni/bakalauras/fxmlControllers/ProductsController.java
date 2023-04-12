package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.hibernateOperations.ProductsOperations;
import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.util.MakeObservable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    @FXML
    public TableView<Products> tableProducts;

    public TableColumn<Products, Long> colId;
    public TableColumn<Products, String> colGroup;
    public TableColumn<Products, String> colName;
    public TableColumn<Products, String> colColor;
    public TableColumn<Products, String> colMeasurement;
    public TableColumn<Products, Double> colPrimeCost;
    public TableColumn<Products, Double> colSellCost;
    public TableColumn<Products, Integer> colStock;

    public TextField fldOrderAmount;


    public String fromWhere = "main";
    public CreateOrderController createOrderController;
    public ProductsController productsController;

    static List<Products> productsList = new ArrayList<>();
    static List<Products> productsInList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillTable();
    }
    public void setUpFromMain(String fromWhere, ProductsController productsController) {
        this.fromWhere = fromWhere;
        this.productsController = productsController;
        fldOrderAmount.setVisible(false);
    }

    public void setUpFromOrder(String fromWhere, CreateOrderController createOrderController, List<Products> productsInList ) {
        this.fromWhere = fromWhere;
        this.createOrderController = createOrderController;
        this.productsInList = productsInList;
        fldOrderAmount.setVisible(true);

    }

    public void fillTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colGroup.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("Measurement"));
        colPrimeCost.setCellValueFactory(new PropertyValueFactory<>("PrimeCost"));
        colSellCost.setCellValueFactory(new PropertyValueFactory<>("SellCost"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("InStock"));
        try {

            productsList = ProductsOperations.findAllProducts();

            for (Products productIn : productsInList) {
                Long id = productIn.getId();
                productsList.removeIf(products -> Objects.equals(products.getId(), id));
            }

            tableProducts.setItems(MakeObservable.GetAllProductsList(productsList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClick(MouseEvent mouseEvent) {
        if(fldOrderAmount.getText().equals("")) {

        }
        else if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                switch (fromWhere) {
                    case "CreateOrder":
                        tableProducts.getSelectionModel().getSelectedItem().setInStock(Integer.valueOf(fldOrderAmount.getText()));
                        createOrderController.addProduct(tableProducts.getSelectionModel().getSelectedItem());
                        productsList.remove(tableProducts.getSelectionModel().getSelectedItem());
                        tableProducts.setItems(MakeObservable.MakeProductListObservable(productsList));

                        break;
                    case "main":
                        break;
                }
            }
        }
    }
}
