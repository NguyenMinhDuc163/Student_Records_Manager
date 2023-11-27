package service;

import dao.UserDAO;
import model.User;

public class ChangePassWordHandl {
    public static boolean updatePassWord(String oldPassWord, String newPassWord, String confirmPass, String userAllowed,
                                         String captchaRoot, String captcha) {
        User user = null;
        if(userAllowed.matches("^B21DCCN\\d{3}$"))
            user = UserDAO.getInstance().selectByID(userAllowed); // check student
        else user = UserDAO.getInstance().selectTeacherByID(userAllowed);
        if(!user.getPassWord().equals(oldPassWord)) return false;
        if(oldPassWord.isEmpty() || newPassWord.isEmpty() || confirmPass.isEmpty()) return false;
        if(!oldPassWord.equals(user.getPassWord()) && !newPassWord.equals(confirmPass)) return false;
        if(!captchaRoot.equals(captcha)) return false;
        user.setPassWord(newPassWord);
        if(userAllowed.matches("^B21DCCN\\d{3}$")){
            return UserDAO.getInstance().update(user) > 0;
        }
        else return UserDAO.getInstance().updateUserTeacher(user) > 0;
    }
}
