package service;

import dao.UserDAO;
import javafx.scene.control.Alert;
import model.User;

public class RegisterHandler {
    public boolean isRegister(String name, String username, String password, String confirm, String studentID) {
        if(password.equals(confirm) && !password.isEmpty() && !name.isEmpty() && !username.isEmpty()){
            // luu dl
            User user = new User(username, password, studentID);
            UserDAO.getInstance().insert(user);
            return true;
        }else{
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Register");
//            alert.setHeaderText("Login Notification");
//            alert.setContentText("Ma sinh vien khong ton tai");
            return false;
        }
    }
}
