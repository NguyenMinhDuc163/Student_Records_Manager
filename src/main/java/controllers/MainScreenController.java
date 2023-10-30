package controllers;


import app.Main;
import dao.ClassesDAO;
import dao.CourseDAO;
import dao.GradeDAO;
import dao.StudentDAO;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import model.Classes;
import model.Course;
import model.Grade;
import model.Student;
import service.ChangePassWordHandl;
import service.LoginHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class MainScreenController implements Initializable {
    @FXML private Label name, studentID, classRoom, majors, year, user;
    @FXML private Button profile, home, courseBtt, gradebbt,extrabbt;
    @FXML private AnchorPane profileForm, main, courseForm, gradePane, ChangePassWordPane;
    @FXML private TableView<Course> tableCourse;
    @FXML private TableView<Grade> tableGrade;
    @FXML private TableColumn<Course, String> courseId, courseName;
    @FXML private TableColumn<Grade, String> nameSV, course, assignmentScore, examScore, practicalScore, attendanceScore, finalExamScore, componentScore, letterGrade;
    @FXML private TextField msv;
    @FXML private PasswordField oldPass, newPass, confirmPass,captcha;
    Student student;
    public Scene setScene() throws IOException {
        URL url = new File("src/main/resources/view/MainScreens.fxml").toURI().toURL();
        URL css = new File("src/main/resources/css/cssMainScreen.css").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(css).toExternalForm());
        return scene;
    }
    public void setActionMenuBar(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == profile){
            profileForm.setVisible(true);
            main.setVisible(false);
            courseForm.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
        }
        else if(event.getSource() == home){
            profileForm.setVisible(false);
            main.setVisible(true);
            courseForm.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
        }
        else if(event.getSource() == courseBtt){
            courseForm.setVisible(true);
            profileForm.setVisible(false);
            main.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
        }
        else if(event.getSource() == gradebbt){
            gradePane.setVisible(true);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
        }
        else if(event.getSource() == extrabbt){
            gradePane.setVisible(false);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
        }
    }

    public void setLogout(ActionEvent event) throws IOException {
        SwitchController switchController = new SwitchController();
        switchController.switchToSceneLogin(event);
    }
    public void setSearch(ActionEvent event){
        ObservableList<Grade> gradeList = null;
        if(msv.getText().equals("all")){
            gradeList = FXCollections.observableArrayList(GradeDAO.getInstance().selectAll());
        }
        else {
            gradeList = FXCollections.observableArrayList(GradeDAO.getInstance().selectByID(msv.getText().toUpperCase()));
        }
        nameSV.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudent().getFirstName() + " "
                +  data.getValue().getStudent().getLastName()));
        course.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCourse().getCourseName()));
        assignmentScore.setCellValueFactory(new PropertyValueFactory<Grade, String>("assignmentScore"));
        examScore.setCellValueFactory(new PropertyValueFactory<Grade, String>("examScore"));
        practicalScore.setCellValueFactory(new PropertyValueFactory<Grade, String>("practicalScore"));
        attendanceScore.setCellValueFactory(new PropertyValueFactory<Grade, String>("attendanceScore"));
        finalExamScore.setCellValueFactory(new PropertyValueFactory<Grade, String>("finalExamScore"));
        componentScore.setCellValueFactory(new PropertyValueFactory<Grade, String>("componentScore"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<Grade, String>("letterGrade"));
        tableGrade.setItems(gradeList);
    }
    public void setChangePassWord(ActionEvent event){
        profileForm.setVisible(false);
        ChangePassWordPane.setVisible(true);
    }
    public void setConfirm(ActionEvent event){
            String oldPassWord = oldPass.getText();
            String newPassWord = newPass.getText();
            String confirmPassWord = confirmPass.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thay đổi mật khẩu");
            alert.setHeaderText("Thông báo:");
            if(ChangePassWordHandl.updatePassWord(oldPassWord, newPassWord, confirmPassWord, student.getStudentID())){
                alert.setContentText("Đổi mật khẩu thành công");
                clearData();
            }
            else {
                alert.setContentText("Đổi mật khẩu thất bai, vui lòng kiểm tra lại thông tin");
                clearData();
            }
            alert.show();
    }
    public void setCancel(ActionEvent event){
        ChangePassWordPane.setVisible(false);
        profileForm.setVisible(true);
    }

    public void clearData(){
        oldPass.clear();
        newPass.clear();
        confirmPass.clear();
        captcha.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main.setVisible(true);
        profileForm.setVisible(false);
        courseForm.setVisible(false);
        gradePane.setVisible(false);
        ChangePassWordPane.setVisible(false);

        String ID = LoginHandler.getUser();
        student = StudentDAO.getInstance().selectByID(ID);
        Classes classes= ClassesDAO.getInstance().selectByID(ID);
        name.setText(student.getFirstName() + " " + student.getLastName());
        studentID.setText(student.getStudentID());
        classRoom.setText(classes.getClassID());
        majors.setText("Công nghệ thông tin");
        year.setText("D21");
        user.setText(student.getFirstName() + " " + student.getLastName());


        ObservableList<Course> coursesList = FXCollections.observableArrayList(CourseDAO.getInstance().selectAll());
        courseId.setCellValueFactory(new PropertyValueFactory<Course, String>("courseID"));
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        tableCourse.setItems(coursesList);

    }
}
