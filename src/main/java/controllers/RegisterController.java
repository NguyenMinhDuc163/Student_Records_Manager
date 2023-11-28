package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.RegisterHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class RegisterController {
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField studentId;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPass;
    @FXML
    private TextField email;

    private final RegisterHandler registerHandler = new RegisterHandler();
    public Scene setScene() throws IOException {
        URL url = new File("src/main/resources/view/RegisterScreen.fxml").toURI().toURL();
        URL css = new File("src/main/resources/css/cssRegister.css").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(css).toExternalForm());
        return scene;
    }
    public void setSubmit (ActionEvent event) throws IOException, SQLException {
        String nameText = name.getText();
        String usernameText = username.getText();
        String studentID = studentId.getText().toUpperCase();
        String passWordText = password.getText();
        String confirmPassText = confirmPass.getText();
        String Email = email.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeight(250);
        alert.setHeaderText("Thông báo đăng kí!");
        if (registerHandler.isRegister(nameText, usernameText, passWordText, confirmPassText, studentID, Email)) {
            alert.setContentText("Bạn đã đăng kí tài khoản thành công");
            // set hanh dong sau khi an ok o alert
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
            alert.setContentText("Đăng kí thất bại, vui lòng kiểm tra lại thông tin\nLưu ý: mã sinh viên đúng chuẩn PTIT B21DCCN___ " +
                    "\n mã giảng viên theo định dạng GV__");
        }
        alert.show();
    }
    public void setCancel(ActionEvent event) throws IOException {
        SwitchController switchController = new SwitchController();
        switchController.switchToSceneLogin(event);
    }
}
