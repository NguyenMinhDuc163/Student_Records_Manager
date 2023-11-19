package service;

import dao.StudentDAO;
import dao.UserDAO;
import javafx.scene.control.Alert;
import model.Student;
import model.User;

public class RegisterHandler {
    public boolean isRegister(String name, String username, String password, String confirm, String studentID, String email) {
        Student student = StudentDAO.getInstance().selectByID(studentID);
        if(password.equals(confirm) && !password.isEmpty() && !name.isEmpty() && !username.isEmpty() && student != null){
            User user = new User(username, password, studentID, email, 5);
            UserDAO.getInstance().insert(user);
            return true;
        }
        return false;
    }
}
