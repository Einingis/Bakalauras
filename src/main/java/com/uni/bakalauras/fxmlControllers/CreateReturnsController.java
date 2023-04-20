package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.*;
import com.uni.bakalauras.scripts.Create;
import com.uni.bakalauras.scripts.Delete;
import com.uni.bakalauras.util.MakeObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.round;
import static java.time.LocalDate.now;

public class CreateReturnsController {

    public TextField fldQuantity;

    public TableView<Products> tableOrderProductsReturn;

    public TableColumn<Products, String> colTypeReturn;
    public TableColumn<Products, String> colNameReturn;
    public TableColumn<Products, String> colMeasurementReturn;
    public TableColumn<Products, String> colColorReturn;
    public TableColumn<Products, Double> colCostReturn;
    public TableColumn<Products, Integer> colQuantityReturn;
    public TableColumn<Products, Double> colSumReturn;
    Orders selectedOrder;
    @FXML
    public Text orderNr = new Text();
    public Text client = new Text();
    public Text city = new Text();
    public Text address = new Text();
    public Text sum = new Text();

    public TableView<Products> tableOrderProducts;

    public TableColumn<Products, String> colType;
    public TableColumn<Products, String> colName;
    public TableColumn<Products, String> colMeasurement;
    public TableColumn<Products, String> colColor;
    public TableColumn<Products, Double> colCost;
    public TableColumn<Products, Integer> colQuantity;
    public TableColumn<Products, Double> colSum;

    List<Products> orderProductList = new ArrayList<>();

    List<Products> returnProductList = new ArrayList<>();

    public void setFormData(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;

        orderNr.setText(String.valueOf(selectedOrder.getId()));
        client.setText(selectedOrder.getClientName());
        city.setText(selectedOrder.getOrderCity());
        address.setText(selectedOrder.getOrderAddress());
        sum.setText(String.valueOf(selectedOrder.getSum()));

        for (Have have : OrdersOperations.findOrdersProduct(selectedOrder.getId())) {
            have.getProduct().setInStock(have.getQuantity());
            have.getProduct().setSum(round((have.getProduct().getSellCost() * have.getQuantity())*100.0)/100.0);
            orderProductList.add(have.getProduct());
        }

        fillTable();
    }

    public void fillTable() {
        colType.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("Measurement"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("SellCost"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("InStock"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("Sum"));

        tableOrderProducts.setItems(MakeObservable.MakeProductListObservable(orderProductList));
    }

    public void fillReturnTable() {
        colTypeReturn.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        colNameReturn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colMeasurementReturn.setCellValueFactory(new PropertyValueFactory<>("Measurement"));
        colColorReturn.setCellValueFactory(new PropertyValueFactory<>("Color"));
        colCostReturn.setCellValueFactory(new PropertyValueFactory<>("SellCost"));
        colQuantityReturn.setCellValueFactory(new PropertyValueFactory<>("InStock"));
        colSumReturn.setCellValueFactory(new PropertyValueFactory<>("Sum"));

        tableOrderProductsReturn.setItems(MakeObservable.MakeProductListObservable(returnProductList));
    }

    public void allToReturns(ActionEvent actionEvent) {
        for (Products product : orderProductList) {
            Products removeProduct = returnProductList.stream().filter(productReturn -> Objects.equals(productReturn.getId(), product.getId())).findFirst().orElse(null);
            if ( removeProduct != null) {
                returnProductList.remove(removeProduct);
                product.setInStock(product.getInStock() + removeProduct.getInStock());
                product.setSum(product.getSum() + removeProduct.getSum());
            }
        }
        returnProductList.addAll(orderProductList);
        orderProductList.clear();

        tableOrderProducts.refresh();
        tableOrderProductsReturn.refresh();

        fillTable();
        fillReturnTable();
    }

    public void selectedToReturns(ActionEvent actionEvent) {
        Integer quantity = getQuantity();

         if (tableOrderProducts.getSelectionModel().getSelectedItem() == null) {
         }
         else {

             Products removeProduct = returnProductList.stream().filter(product -> Objects.equals(product.getId(),
                     tableOrderProducts.getSelectionModel().getSelectedItem().getId())).findFirst().orElse(null);

             Products selectedProduct = new Products(tableOrderProducts.getSelectionModel().getSelectedItem());

             Products orderProduct = tableOrderProducts.getSelectionModel().getSelectedItem();

             if ( removeProduct != null) {
                 returnProductList.remove(removeProduct);
                 selectedProduct.setInStock(quantity + removeProduct.getInStock());
                 selectedProduct.setSum(round((selectedProduct.getSellCost() * quantity)*100.0)/100.0 + removeProduct.getSum());
             }
             else {
                 selectedProduct.setInStock(quantity);
                 selectedProduct.setSum(round((selectedProduct.getSellCost() * quantity)*100.0)/100.0);
             }

             returnProductList.add(selectedProduct);

             if (orderProduct.getInStock() > quantity) {
                 orderProductList.remove(orderProduct);

                 orderProduct.setInStock(orderProduct.getInStock() - quantity);
                 orderProduct.setSum(round((orderProduct.getSellCost() * orderProduct.getInStock())*100.0)/100.0);

                 orderProductList.add(orderProduct);
             }
             else {
                 orderProductList.remove(tableOrderProducts.getSelectionModel().getSelectedItem());
             }
             tableOrderProducts.refresh();
             tableOrderProductsReturn.refresh();
             fillTable();
             fillReturnTable();
         }
    }

    public void selectedToOrder(ActionEvent actionEvent) {
        Integer quantity = getQuantity();
        if (tableOrderProductsReturn.getSelectionModel().getSelectedItem() == null) {
        }
        else {
            Products removeProduct = orderProductList.stream().filter(product -> Objects.equals(product.getId(),
                    tableOrderProductsReturn.getSelectionModel().getSelectedItem().getId())).findFirst().orElse(null);

            Products selectedProduct = new Products(tableOrderProductsReturn.getSelectionModel().getSelectedItem());

            Products returnProduct = tableOrderProductsReturn.getSelectionModel().getSelectedItem();

            if ( removeProduct != null) {
                orderProductList.remove(removeProduct);
                selectedProduct.setInStock(quantity + removeProduct.getInStock());
                selectedProduct.setSum(round((selectedProduct.getSellCost() * quantity)*100.0)/100.0 + removeProduct.getSum());
            }
            else {
                selectedProduct.setInStock(quantity);
                selectedProduct.setSum(round((selectedProduct.getSellCost() * quantity)*100.0)/100.0);
            }

            orderProductList.add(selectedProduct);

            if (returnProduct.getInStock() > quantity) {
                returnProductList.remove(returnProduct);

                returnProduct.setInStock(returnProduct.getInStock() - quantity);
                returnProduct.setSum(round((returnProduct.getSellCost() * returnProduct.getInStock())*100.0)/100.0);

                returnProductList.add(returnProduct);
            }
            else {
                returnProductList.remove(tableOrderProductsReturn.getSelectionModel().getSelectedItem());
            }
            tableOrderProducts.refresh();
            tableOrderProductsReturn.refresh();
            fillTable();
            fillReturnTable();
        }
    }

    public void allToOrder(ActionEvent actionEvent) {
        for (Products product : returnProductList) {
            Products removeProduct = orderProductList.stream().filter(productReturn -> Objects.equals(productReturn.getId(), product.getId())).findFirst().orElse(null);
            if ( removeProduct != null) {
                orderProductList.remove(removeProduct);
                product.setInStock(product.getInStock() + removeProduct.getInStock());
                product.setSum(product.getSum() + removeProduct.getSum());
            }
        }
        orderProductList.addAll(returnProductList);
        returnProductList.clear();

        tableOrderProducts.refresh();
        tableOrderProductsReturn.refresh();

        fillTable();
        fillReturnTable();
    }

    public Integer getQuantity() {
        if (fldQuantity.getText() == "") {
            return 1;
        }
        else {
            return Integer.valueOf(fldQuantity.getText());
        }
    }

    public void createReturn(ActionEvent actionEvent) {
        Double OrderSum = 0.0;

        for (Products product : orderProductList) {
            OrderSum += product.getSum();
        }

        selectedOrder.setSum(OrderSum);
        Delete.delete(OrdersOperations.findOrdersProduct(selectedOrder.getId()));

        List<Orders> ordersList = new ArrayList<>();

        ordersList.add(selectedOrder);

        Create.createAllInList(ordersList);

        List<Have> haveList = new ArrayList<>();

        for (Products product : orderProductList) {
            Have h = new Have();
            h.setProduct(product);
            h.setOrder(selectedOrder);
            h.setQuantity(product.getInStock());

            haveList.add(h);
        }
        Create.createAllInList(haveList);

        Double returnSum = 0.0;

        for (Products product : returnProductList) {
            returnSum += product.getSum();
        }

        Returns returns =new Returns(selectedOrder, now(), returnSum, "sukurtas");

        List<Returns> returnsList = new ArrayList<>();

        returnsList.add(returns);

        Create.createAllInList(returnsList);

        List<Returning> returningList = new ArrayList<>();

        for (Products product : returnProductList) {
            Returning r = new Returning();
            r.setReturns(returns);
            r.setProduct(product);
            r.setQuantity(product.getInStock());

            returningList.add(r);
        }

        Create.createAllInList(returningList);
    }
}

