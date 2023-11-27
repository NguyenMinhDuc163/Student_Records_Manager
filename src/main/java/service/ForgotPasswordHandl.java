package service;

import dao.StudentDAO;
import dao.UserDAO;
import model.Student;
import model.User;
import util.EmailHandler;
import java.util.UUID;
public class ForgotPasswordHandl {

    public static ForgotPasswordHandl getInstance(){
        return new ForgotPasswordHandl();
    }
    public static String generateRandomPassword() {
        String uuid = UUID.randomUUID().toString();
        // Replace special characters and digits to get a shorter password
        return uuid.replaceAll("[^a-zA-Z]", "").substring(0, 8);
    }
    public static boolean confirmPasswordResetRequest(String userName, String studentID, String email) {
        try {
            if (userName.isEmpty() || studentID.isEmpty() || email.isEmpty()) return false;
            String newPassWord = generateRandomPassword();
            User user = new User(userName, newPassWord, studentID, email, 5);
            int count = UserDAO.getInstance().update(user);
            return count > 0 && EmailHandler.sendMail(email, newPassWord, studentID);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkSendMail(String userName, String studentID, String email){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (userName.isEmpty() || studentID.isEmpty() || email.isEmpty()) return false;
        User user = UserDAO.getInstance().selectByUserName(userName);
        Student student = StudentDAO.getInstance().selectByID(studentID.toUpperCase());
        return user != null && student !=  null && email.matches(EMAIL_REGEX);
    }
}
