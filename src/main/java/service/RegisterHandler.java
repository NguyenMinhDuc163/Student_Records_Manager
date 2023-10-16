package service;

import dao.UserDAO;
import model.User;

public class RegisterHandler {
    public boolean isRegister(String name, String username, String password, String confirm, String studentID) {
        if(password.equals(confirm) && !password.isEmpty() && !name.isEmpty() && !username.isEmpty()){
            // luu dl
            User user = new User("312312",username, password, studentID);
            UserDAO.getInstance().insert(user);
        }else{
            return false;
        }
        return true;
    }
}
