package service;

import dao.UserDAO;
import model.User;

public class ChangePassWordHandl {
    public static boolean updatePassWord(String oldPassWord, String newPassWord, String confirmPass, String studentID) {
        User user = UserDAO.getInstance().selectByID(studentID);
        if(oldPassWord.isEmpty() || newPassWord.isEmpty() || confirmPass.isEmpty()) return false;
        if(!oldPassWord.equals(user.getPassWord()) && !newPassWord.equals(confirmPass)) return false;
        user.setPassWord(newPassWord);
        return UserDAO.getInstance().update(user) > 0;
    }
}
