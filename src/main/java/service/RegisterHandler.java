package service;

import dao.StudentDAO;
import dao.TeacherDao;
import dao.UserDAO;
import javafx.scene.control.Alert;
import model.Student;
import model.Teachers;
import model.User;

public class RegisterHandler {
    public boolean isRegister(String name, String username, String password, String confirm, String identifier, String email) {
        Teachers teachers = null;
        Student student = null;
        if(identifier.startsWith("GV")){
            teachers = TeacherDao.getInstance().selectByID(identifier);
        }
        else {
            student = StudentDAO.getInstance().selectByID(identifier);
        }

        if(password.equals(confirm) && !password.isEmpty() && !name.isEmpty() && !username.isEmpty() && (student != null || teachers != null)){
            User user = new User(username, password, identifier, email, 5);
            UserDAO.getInstance().insert(user);
            return true;
        }
        return false;
    }
}
