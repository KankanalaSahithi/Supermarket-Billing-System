package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.HashMap;
import models.SharedData;

public class CustomerController {

    @FXML
    private VBox cartItemsBox;

    @FXML
    private Label totalLabel;

    private int total = 0;

    // Store quantities

    private HashMap<String, Integer> quantities =
            new HashMap<>();

    // Store cart item rows

    private HashMap<String, HBox> cartRows =
            new HashMap<>();

    // Store quantity labels

    private HashMap<String, Label> qtyLabels =
            new HashMap<>();

    // Store price labels

    private HashMap<String, Label> priceLabels =
            new HashMap<>();

    // MAIN METHOD

    private void addItem(
            String name,
            int price,
            String imagePath
    ) {

        // Update quantity

        int qty = quantities.getOrDefault(name, 0);

        qty++;

        quantities.put(name, qty);

        // If item already exists

        if(cartRows.containsKey(name)) {

            qtyLabels.get(name)
                    .setText("x" + qty);

            priceLabels.get(name)
                    .setText("₹" + (qty * price));

        }

        // New item

        else {

            HBox itemBox = new HBox(15);

            itemBox.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.08);" +
                    "-fx-background-radius: 15;" +
                    "-fx-padding: 12;" +
                    "-fx-alignment: center-left;"
            );

            // Image

            ImageView imageView =
                    new ImageView();

            imageView.setFitHeight(50);

            imageView.setFitWidth(50);

            imageView.setPreserveRatio(true);

            Image image = new Image(
                getClass().getResourceAsStream(
                "/images/" + imagePath
                )
            );

            imageView.setImage(image);

            // Product Name

            Label nameLabel =
                    new Label(name);

            nameLabel.setStyle(
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;"
            );

            // Quantity

            Label qtyLabel =
                    new Label("x1");

            qtyLabel.setStyle(
                    "-fx-text-fill: #cbd5e1;" +
                    "-fx-font-size: 14px;"
            );

            // Price

            Label priceLabel =
                    new Label("₹" + price);

            priceLabel.setStyle(
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;"
            );

            // Spacer

            Region spacer = new Region();

            HBox.setHgrow(
                    spacer,
                    Priority.ALWAYS
            );

            // Left side

            Button minusBtn = new Button("-");

            minusBtn.setStyle(
                    "-fx-background-color: #ef4444;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 20;"
            );

            Button plusBtn = new Button("+");

            plusBtn.setStyle(
                    "-fx-background-color: #22c55e;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 20;"
            );

            HBox qtyBox = new HBox(10);

// PLUS BUTTON

plusBtn.setOnAction(e -> {

    int currentQty =
            quantities.get(name);

    currentQty++;

    quantities.put(name, currentQty);

    qtyLabel.setText(
            "x" + currentQty
    );

    priceLabel.setText(
            "₹" + (currentQty * price)
    );

    total += price;

    totalLabel.setText(
            "Total : ₹" + total
    );
});

// MINUS BUTTON

minusBtn.setOnAction(e -> {

    int currentQty =
            quantities.get(name);

    if(currentQty > 1) {

        currentQty--;

        quantities.put(name, currentQty);

        qtyLabel.setText(
                "x" + currentQty
        );

        priceLabel.setText(
                "₹" + (currentQty * price)
        );

        total -= price;

        totalLabel.setText(
                "Total : ₹" + total
        );
    }

    else {

        cartItemsBox.getChildren()
                .remove(itemBox);

        quantities.remove(name);

        cartRows.remove(name);

        qtyLabels.remove(name);

        priceLabels.remove(name);

        total -= price;

        totalLabel.setText(
                "Total : ₹" + total
        );
    }
});

// Add buttons + qty

qtyBox.getChildren().addAll(
        minusBtn,
        qtyLabel,
        plusBtn
);

            VBox details =
                    new VBox(5);

            details.getChildren().addAll(
                    nameLabel,
                    qtyBox
            );

            // Add all

            itemBox.getChildren().addAll(
                    imageView,
                    details,
                    spacer,
                    priceLabel
            );

            // Add to cart

            cartItemsBox.getChildren()
                    .add(itemBox);

            // Store references

            cartRows.put(name, itemBox);

            qtyLabels.put(name, qtyLabel);

            priceLabels.put(name, priceLabel);
        }

        // Update total

        total += price;

        totalLabel.setText(
                "Total : ₹" + total
        );
    }

    // PRODUCTS

    public void addLays() {

        addItem(
                "Lays",
                20,
                "lays.png"
        );
    }

    public void addEggs() {

        addItem(
                "Fresh Eggs",
                40,
                "eggs.png"
        );
    }

    public void addDetergent() {

        addItem(
                "Detergent",
                360,
                "det.png"
        );
    }

    public void addSoap() {

        addItem(
                "Baby Soap",
                40,
                "joh.png"
        );
    }

    public void addCrispello() {

        addItem(
                "Crispello",
                20,
                "cris.png"
        );
    }

    public void addPotato() {

        addItem(
                "Potato",
                30,
                "pot.png"
        );
    }

    public void goToPayment(ActionEvent event)
        throws Exception {

    // Save total

    SharedData.subtotal = total;

    SharedData.gst = total / 10;

    SharedData.totalAmount =
            SharedData.subtotal +
            SharedData.gst;

    SharedData.totalItems =
            quantities.size();

    // Load payment page

    Parent root =
            FXMLLoader.load(
            getClass().getResource(
            "/views/payment.fxml"));

    Stage stage = (Stage)
            totalLabel.getScene()
            .getWindow();

    stage.setScene(
            new Scene(root)
    );

    stage.show();
}

@FXML
public void logout(ActionEvent event)
        throws Exception {

    Parent root =
            FXMLLoader.load(
            getClass().getResource(
            "/views/login.fxml"));

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