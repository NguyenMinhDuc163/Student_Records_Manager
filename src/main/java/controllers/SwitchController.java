package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
public class SwitchController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneMain(ActionEvent event) throws IOException {
        // set scene o controller
        MainScreenController screenController = new MainScreenController();
        scene = screenController.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setX(250);
        stage.setY(40);
        stage.show();
    }
    public void switchToSceneLogin(ActionEvent event) throws IOException {
        // set scene o controller
        LoginController loginController = new LoginController();
        scene = loginController.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSceneRegister(ActionEvent event) throws IOException {
        // set scene o controller
        RegisterController registerConntroller = new RegisterController();
        scene = registerConntroller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneForgotPassWord(ActionEvent event) throws IOException {
        ForgotPassController forgotPassController = new ForgotPassController();
        scene = forgotPassController.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
