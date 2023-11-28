package controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ForgotPasswordHandl;
import util.EmailHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ForgotPassController {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField studentid;
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
        String emailText = email.getText();
        String personIDcode  = studentid.getText().toUpperCase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
//         kiem tra dieu kien
        boolean isCheck = ForgotPasswordHandl.getInstance().checkSendMail(userName, personIDcode , emailText);
        if (isCheck) ProgressBarController.getInstance(13226L).showProgressBar();
        Task<Boolean> sendMailTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                boolean isCheckConditions = ForgotPasswordHandl.getInstance().checkSendMail(userName, personIDcode , emailText);
                if(!isCheckConditions) return false;
                return ForgotPasswordHandl.confirmPasswordResetRequest(userName, personIDcode , emailText);
            }
        };

        sendMailTask.setOnSucceeded(send -> {
            boolean isSend = sendMailTask.getValue();
            if (isSend) {

                alert.setContentText("Cảm ơn bạn đã cung cấp thông tin. Chúng tôi đang xử lý yêu cầu của bạn.\n" +
                        "Vui lòng kiểm tra hộp thư đến của bạn để nhận mật khẩu mới.\n " +
                        "Nếu bạn không nhận được email trong vòng vài phút, vui lòng kiểm tra thư mục Spam hoặc liên hệ với " +
                        "chúng tôi để được hỗ trợ.");
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
                alert.setContentText("Xin lỗi, có vẻ như thông tin bạn đã nhập không đúng.\n" +
                        "Vui lòng kiểm tra lại và nhập thông tin chính xác.");
            }
            alert.show();
        });
        new Thread(sendMailTask).start();
    }

    public void setButtonCancel(ActionEvent event) throws IOException {
        SwitchController switchController = new SwitchController();
        switchController.switchToSceneLogin(event);
    }
}
