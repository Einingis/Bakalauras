package com.uni.bakalauras.fxmlControllers;

import com.uni.bakalauras.model.Orders;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class OrderDetailsView {
    @FXML
    public Text orderNr = new Text();
    public Text client = new Text();
    public Text city = new Text();
    public Text address = new Text();
    public Text sum = new Text();


    public void setFormData(Orders selectedOrder) {

        orderNr.setText(String.valueOf(selectedOrder.getId()));
        client.setText(selectedOrder.getClientName());
        city.setText(selectedOrder.getOrderCity());
        address.setText(selectedOrder.getOrderAddress());
        sum.setText(String.valueOf(selectedOrder.getSum()));
    }
}
