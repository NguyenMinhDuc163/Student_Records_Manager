package service;

import dao.UserDAO;
import model.User;

public class LoginHandler {
    private static User user = null;

    public static String getUser() {
        return user.getStudentID();
    }

    public boolean isValidLogin(String username, String password) {
        try {
            user = UserDAO.getInstance().selectByUserNamePassWord(username, password);
            return user.getUserName().equals(username) && user.getPassWord().equals(password);
        }catch (Exception e){
            // do user co the null
            System.out.println("Nhập thông tin không đúng !!!");
            return false;
        }
    }
}
