package service;

import dao.StudentDAO;
import dao.TeacherDao;
import dao.UserDAO;
import javafx.scene.control.TextArea;
import model.Student;
import model.Teachers;
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
    public static boolean confirmPasswordResetRequest(String userName, String personIDcode , String email) {
        try {
            if (userName.isEmpty() || personIDcode .isEmpty() || email.isEmpty()) return false;
            String newPassWord = generateRandomPassword();
            User user = new User(userName, newPassWord, personIDcode , email, 5);
            int count;
            if(personIDcode .matches("^B21DCCN\\d{3}$"))
                count = UserDAO.getInstance().update(user);
            else count = UserDAO.getInstance().updateUserTeacher(user);
            return count > 0 && EmailHandler.sendMail(email, newPassWord, personIDcode );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkSendMail(String userName, String personIDcode , String email){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (userName.isEmpty() || personIDcode .isEmpty() || email.isEmpty()) return false;
        User user = UserDAO.getInstance().selectByUserName(userName);
        Student student =  null;
        Teachers teachers = null;
        if(personIDcode .matches("^B21DCCN\\d{3}$"))
            student = StudentDAO.getInstance().selectByID(personIDcode .toUpperCase());
        else teachers = TeacherDao.getInstance().selectByID(personIDcode .toUpperCase());
        return user != null && (student !=  null  || teachers != null) && email.matches(EMAIL_REGEX);
    }
}
