package service;

import dao.UserDAO;
import model.User;

public class LoginHandler {
    private static User user = null;

    public static String getUser() {
        return user.getStudentID();
    }

    public boolean isValidLogin(String username, String password) {
        user = UserDAO.getInstance().selectByUserNamePassWord(username, password);
        return user.getUserName().equals(username) && user.getPassWord().equals(password);
    }
}
