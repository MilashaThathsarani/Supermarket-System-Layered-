package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    public AnchorPane logInContext;
    public TextField txtUserName;
    public TextField txtPassword;

    public void createNewAccountOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) logInContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/User.fxml"))));
        window.centerOnScreen();
    }

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("a") && txtPassword.getText().equalsIgnoreCase("1")) {
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardAdmin.fxml"))));
            window.centerOnScreen();

        }
        if (txtUserName.getText().equalsIgnoreCase("c") && txtPassword.getText().equalsIgnoreCase("0")) {
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardCashier.fxml"))));
            window.centerOnScreen();
        }
    }

    public void userOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
