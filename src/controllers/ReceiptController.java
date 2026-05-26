package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ReceiptController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label itemsLabel;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label gstLabel;

    @FXML
    private Label totalLabel;

    @FXML
    public void initialize() {

        dateLabel.setText(
                "Date : " +
                LocalDateTime.now()
        );

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

        totalLabel.setText(
                "Total : ₹" +
                SharedData.totalAmount
        );
    }

    @FXML
public void backToShopping(ActionEvent event)
        throws Exception {

    Parent root =
            FXMLLoader.load(
            getClass().getResource(
            "/views/customer.fxml"));

    Stage stage =
            (Stage) totalLabel
            .getScene()
            .getWindow();

    stage.setScene(
            new Scene(root)
    );

    stage.show();
}
}