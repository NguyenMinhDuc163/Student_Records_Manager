package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ForgotPassController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button button;
    public Scene setScene() throws IOException {
        URL url = new File("src/main/resources/view/ForgotPassword.fxml").toURI().toURL();
        URL css = new File("src/main/resources/css/cssForgotPass.css").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(css).toExternalForm());
        return scene;
    }
    public void setButtonSubmit(ActionEvent event){
        String userName = username.getText();
        String passWord = password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        // kiem tra dieu kien
        if (!userName.isEmpty() && !passWord.isEmpty()) {
            alert.setContentText("The request to reset your password has been received\nPassword will be sent to your email");
            alert.setOnCloseRequest(e -> {
                // neu thanh cong se ve man hinh login
                SwitchController switchController = new SwitchController();
                try {
                    switchController.switchToSceneLogin(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            alert.setContentText("reset your password failed");
        }
        alert.show();
    }

    public void setButtonCancel(ActionEvent event) throws IOException {
        SwitchController switchController = new SwitchController();
        switchController.switchToSceneLogin(event);
    }
}
