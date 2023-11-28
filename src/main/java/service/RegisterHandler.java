package service;

import dao.StudentDAO;
import dao.TeacherDao;
import dao.UserDAO;
import model.Student;
import model.Teachers;
import model.User;

public class RegisterHandler {
    public int isRegister(String name, String username, String password, String confirm, String identifier, String email) {
        Teachers teachers = null;
        Student student = null;
        User userCheck = null;
        System.out.println(username + " " + identifier);
        if(identifier.startsWith("GV")){
            teachers = TeacherDao.getInstance().selectByID(identifier);
            userCheck = UserDAO.getInstance().selectTeacherByID(identifier);
        }
        else {
            student = StudentDAO.getInstance().selectByID(identifier);
            userCheck = UserDAO.getInstance().selectByID(identifier);
        }
        if(userCheck != null){
            return 2;
        }
        userCheck = UserDAO.getInstance().selectByUserName(username);
        if(userCheck != null) return 3;
        if(password.equals(confirm) && !password.isEmpty() && !name.isEmpty() && !username.isEmpty() && (student != null || teachers != null)){
            User user = new User(username, password, identifier, email, 5);
            UserDAO.getInstance().insert(user);
            return 1;
        }
        return 0;
    }
}
