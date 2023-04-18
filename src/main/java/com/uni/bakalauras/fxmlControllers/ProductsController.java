package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.Main;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.hibernateOperations.ProductsOperations;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.scripts.Create;
import com.uni.bakalauras.scripts.Delete;
import com.uni.bakalauras.util.MakeObservable;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    public TextField fldFilterId;
    public TextField fldFilterType;
    public TextField fldFilterName;
    public TextField fldFilterColor;
    public TextField fldFilterMeasurement;
    public TextField fldFilterPrimeCostMin;
    public TextField fldFilterPrimeCostMax;
    public TextField fldFilterPriceMin;
    public TextField fldFilterPriceMax;
    public TextField fldFilterStockMin;
    public TextField fldFilterStockMax;

    public Button btnCreate;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnFilter;

    public Text txtNr;
    public Text txtType;
    public Text txtName;
    public Text txtColor;
    public Text txtMeasurement;
    public Text txtPrimeCost;
    public Text txtCost;
    public Text txtStock;
    public Text txtMin1;
    public Text txtMin2;
    public Text txtMin3;
    public Text txtMax1;
    public Text txtMax2;
    public Text txtMax3;

    public Text txtOrderAmount;

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
        txtOrderAmount.setVisible(false);

        btnCreate.setVisible(true);
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);
    }

    public void setUpFromOrder(String fromWhere, CreateOrderController createOrderController, List<Products> productsInList ) {
        this.fromWhere = fromWhere;
        this.createOrderController = createOrderController;
        this.productsInList = productsInList;

        fldOrderAmount.setVisible(true);
        txtOrderAmount.setVisible(true);

        btnCreate.setVisible(false);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);

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
                        if (Integer.valueOf(fldOrderAmount.getText()) > tableProducts.getSelectionModel().getSelectedItem().getInStock()) {
                            PopupOperations.alertMessage("Nepakankamas prekių likutis");
                        }
                        else {
                            tableProducts.getSelectionModel().getSelectedItem().setInStock(Integer.valueOf(fldOrderAmount.getText()));
                            createOrderController.addProduct(tableProducts.getSelectionModel().getSelectedItem());
                            productsList.remove(tableProducts.getSelectionModel().getSelectedItem());
                            tableProducts.setItems(MakeObservable.MakeProductListObservable(productsList));
                        }
                        break;
                    case "main":
                        break;
                }
            }
        }
    }

    public void createProduct(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("CreateProduct-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        CreateProductController createProductController = loader.getController();
        createProductController.setController(productsController, createProductController);

        stage.initModality(Modality.NONE);
        stage.setTitle("Naujas uzsakymas");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void updateProduct(ActionEvent actionEvent) throws IOException {
        if (tableProducts.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite Uzsakyma");
        }
        else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("CreateProduct-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            CreateProductController createProductController = loader.getController();
            createProductController.setController(productsController, createProductController);
            createProductController.setProduct(tableProducts.getSelectionModel().getSelectedItem());

            stage.initModality(Modality.NONE);
            stage.setTitle("Naujas uzsakymas");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteProduct(ActionEvent actionEvent) {
        if (tableProducts.getSelectionModel().getSelectedItem() == null) {
            PopupOperations.alertMessage("Pasirinkite produkta");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("pasalinti produkta ");

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
                    List<Products> deleteList = new ArrayList<>();
                    deleteList.add(tableProducts.getSelectionModel().getSelectedItem());
                    Delete.delete(deleteList);
                    productsList.remove(tableProducts.getSelectionModel().getSelectedItem());

                    tableProducts.setItems(MakeObservable.MakeProductListObservable(productsList));
                }
                return null;
            });
            dialog.showAndWait();
        }
    }

    public void filterProducts(ActionEvent actionEvent) {
        List<String> filters = new ArrayList<>();

        filters.add(fldFilterId.getText());
        filters.add(fldFilterType.getText());
        filters.add(fldFilterName.getText());
        filters.add(fldFilterColor.getText());
        filters.add(fldFilterMeasurement.getText());

        filters.add(fldFilterPrimeCostMin.getText());
        filters.add(fldFilterPrimeCostMax.getText());

        filters.add(fldFilterPriceMin.getText());
        filters.add(fldFilterPriceMax.getText());

        filters.add(fldFilterStockMin.getText());
        filters.add(fldFilterStockMax.getText());

        productsList.clear();
        productsList = ProductsOperations.findProductsByFilters(filters);

        tableProducts.setItems(MakeObservable.MakeProductListObservable(productsList));
    }
}
