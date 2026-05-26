package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.SharedData;

public class PaymentController {

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label itemsLabel;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label gstLabel;

    @FXML
    public void initialize() {

        itemsLabel.setText(
                "Items : " +
                SharedData.totalItems
        );

        subtotalLabel.setText(
                "Subtotal : ₹" +
                SharedData.subtotal
        );

        gstLabel.setText(
                "GST : ₹" +
                SharedData.gst
        );

        totalAmountLabel.setText(
                "Total : ₹" +
                SharedData.totalAmount
        );
    }
    public void goToReceipt(ActionEvent event)
        throws Exception {

    Parent root =
            FXMLLoader.load(
            getClass().getResource(
            "/views/receipt.fxml"));

    Stage stage = (Stage)
            totalAmountLabel.getScene()
            .getWindow();

    stage.setScene(
            new Scene(root)
    );

    stage.show();
}
}