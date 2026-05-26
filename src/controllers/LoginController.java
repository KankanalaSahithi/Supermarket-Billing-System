package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleBox;

    @FXML
    private Label messageLabel;

    @FXML
    public void login(ActionEvent event)
            throws Exception {

        String username =
                usernameField.getText();

        String password =
                passwordField.getText();

        String role =
                roleBox.getValue();

        // ADMIN LOGIN

        if(username.equals("admin")
                && password.equals("admin123")
                && role.equals("Admin")) {

            Parent root =
                    FXMLLoader.load(
                    getClass().getResource(
                    "/views/admin.fxml"));

            Stage stage =
                    (Stage) usernameField
                    .getScene()
                    .getWindow();

            stage.setScene(
                    new Scene(root)
            );

            stage.show();
        }

        // CUSTOMER LOGIN

        else if(username.equals("customer")
                && password.equals("customer123")
                && role.equals("Customer")) {

            Parent root =
                    FXMLLoader.load(
                    getClass().getResource(
                    "/views/customer.fxml"));

            Stage stage =
                    (Stage) usernameField
                    .getScene()
                    .getWindow();

            stage.setScene(
                    new Scene(root)
            );

            stage.show();
        }

        else {

            messageLabel.setText(
                    "Invalid Credentials"
            );
        }
    }
}