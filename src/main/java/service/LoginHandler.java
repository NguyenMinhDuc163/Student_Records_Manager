package service;

import dao.UserDAO;
import model.User;

public class LoginHandler {
    public boolean isValidLogin(String username, String password) {
        User user = UserDAO.getInstance().selectByUserNamePassWord(username, password);
        return user.getUserName().equals(username) && user.getPassWord().equals(password);
    }
}
