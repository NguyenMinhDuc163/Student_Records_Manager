package service;

import dao.UserDAO;
import model.User;
import util.EmailHandler;
import java.util.UUID;
public class ForgotPasswordHandl {
    public static String generateRandomPassword() {
        String uuid = UUID.randomUUID().toString();
        // Replace special characters and digits to get a shorter password
        return uuid.replaceAll("[^a-zA-Z]", "").substring(0, 8);
    }
    public static boolean confirmPasswordResetRequest(String userName, String studentID, String email) {
        try {
            if (userName.isEmpty() || studentID.isEmpty() || email.isEmpty()) return false;
            String newPassWord = generateRandomPassword();
            User user = new User(userName, newPassWord, studentID);
            int count = UserDAO.getInstance().update(user);
            return count > 0 && EmailHandler.sendMail(email, newPassWord, studentID);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
