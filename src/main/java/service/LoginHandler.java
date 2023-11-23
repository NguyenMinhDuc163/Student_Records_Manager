package service;

import dao.UserDAO;
import model.User;

public class LoginHandler {
    private static User user = null;

    public static String getUser() {
        if(user.getStudentID()  != null)
            return user.getStudentID();
        else return user.getTeacherID();
    }
    public static LoginHandler getInstance(){
        return new LoginHandler();
    }

    public boolean isValidLogin(String username, String password) {
        try {
            System.out.println(username + " " + password);
            user = UserDAO.getInstance().selectByUserNamePassWord(username, password);
            System.out.println(user);
            return user != null && user.getUserName().equals(username) && user.getPassWord().equals(password);
        }catch (Exception e){
            // do user co the null
            e.printStackTrace();
            System.out.println("Nhập thông tin không đúng !!!");
            return false;
        }
    }
    public boolean checkRole(){
        return user.getStudentID() == null;
    }
}
