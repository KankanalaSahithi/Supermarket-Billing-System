package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private VBox inventoryContainer;

    @FXML
    public void showAddProductDialog() {

        // INPUTS

        TextInputDialog nameDialog =
                new TextInputDialog();

        nameDialog.setHeaderText(
                "Enter Product Name"
        );

        String name =
                nameDialog.showAndWait()
                .orElse("");

        TextInputDialog priceDialog =
                new TextInputDialog();

        priceDialog.setHeaderText(
                "Enter Product Price"
        );

        String price =
                priceDialog.showAndWait()
                .orElse("");

        TextInputDialog stockDialog =
                new TextInputDialog();

        stockDialog.setHeaderText(
                "Enter Product Stock"
        );

        String stock =
                stockDialog.showAndWait()
                .orElse("");

        // CREATE ROW

        HBox row = new HBox(80);

        row.getStyleClass()
                .add("inventory-row");

        Label nameLabel =
                new Label(name);

        Label priceLabel =
                new Label("₹" + price);

        Label stockLabel =
                new Label("Stock : " + stock);

        row.getChildren().addAll(
                nameLabel,
                priceLabel,
                stockLabel
        );

        // ADD TO INVENTORY

        inventoryContainer.getChildren()
                .add(row);
    }

    @FXML
public void logout(ActionEvent event)
        throws Exception {

    Parent root =
            FXMLLoader.load(
            getClass().getResource(
            "/views/login.fxml"));

    Stage stage =
            (Stage) inventoryContainer
            .getScene()
            .getWindow();

    stage.setScene(
            new Scene(root)
    );

    stage.show();
}
}