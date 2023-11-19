package model;

public class User {
    private String userID;
    private String userName;
    private String passWord;
    private String studentID;
    private String email;

    public User(String userName, String passWord, String studentID, String email, int time) {
        this.userName = userName;
        this.passWord = passWord;
        this.studentID = studentID;
        this.email = email;
    }

    public User(String userID, String userName, String passWord, String studentID) {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
        this.studentID = studentID;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return String.join(" ", userID, userName, passWord, studentID);
    }
}
