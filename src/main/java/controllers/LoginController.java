package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import service.LoginHandler;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private Button loginButton;
    private LoginHandler loginHandler = new LoginHandler();
    public Scene setScene() throws IOException {
        URL url = new File("src/main/resources/view/loginScreen.fxml").toURI().toURL();
        URL css = new File("src/main/resources/css/cssLogin.css").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(css).toExternalForm());
        return scene;
    }

    public void bttLogIn (ActionEvent event) throws IOException {
        String username = userName.getText();
        String password = passWord.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Login Notification");
        if (loginHandler.isValidLogin(username, password)) {
            alert.setContentText("Logged in successfully");
            SwitchController switchController = new SwitchController();
            switchController.switchToScene1(event);
        } else {
            alert.setContentText("Account or password is incorrect");
        }
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setDisable(true);
        // cai dat mac dinh
        userName.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
    }
}
