package controllers;


import app.Main;
import dao.*;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import service.ChangePassWordHandl;
import service.LoginHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainScreenController implements Initializable {
    @FXML private Label name, studentID, classRoom, majors, year, user;
    @FXML private Button profile, home, courseBtt, gradebbt,extrabbt;
    @FXML private AnchorPane profileForm, main, courseForm, gradePane, ChangePassWordPane;
    @FXML private TableView<Course> tableCourse;
    @FXML private TableView<Grade> tableGrade;
    @FXML private TableColumn<Course, String> courseId, courseName;
    @FXML private TableColumn<Grade, String> nameSV, course, assignmentScore, examScore, practicalScore, attendanceScore;
    @FXML private TableColumn<Grade, String> finalExamScore, componentScore, letterGrade;
    @FXML private TextField msv;
    @FXML private PasswordField oldPass, newPass, confirmPass,captcha;
    @FXML private BorderPane borderPaneGrade;
    @FXML private ScrollPane grandePane;
    @FXML private Text notice;
    @FXML private GridPane gridPane;
    @FXML private ImageView imageView, imageLogo;
    private Map<Label, ChoiceBox> choiceList = new LinkedHashMap<>();
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
            borderPaneGrade.setVisible(false);
        }
        else if(event.getSource() == home){
            profileForm.setVisible(false);
            main.setVisible(true);
            courseForm.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
        }
        else if(event.getSource() == courseBtt){
            courseForm.setVisible(true);
            profileForm.setVisible(false);
            main.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
        }
        else if(event.getSource() == gradebbt){
            gradePane.setVisible(true);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
        }
        else if(event.getSource() == extrabbt){
            gradePane.setVisible(false);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(true);
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

    public void setImageView(ActionEvent event){
        Stage stage = (Stage) profileForm.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fc.getExtensionFilters().add(imageFilter);
        File file = fc.showOpenDialog(stage);
        if (file != null){
            Image image = new Image(file.toURI().toString(),320, 213, false, true);
            imageView.setImage(image);
            imageLogo.setImage(image);
        }
    }

    public void clearData(){
        oldPass.clear();
        newPass.clear();
        confirmPass.clear();
        captcha.clear();
    }

    public void setGrande(){
        ArrayList<Subject> subjects = SubjectDAO.getInstance().selectAll();
        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));
        // Setting column constraints to make columns equally sized
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Iterating over each subject to create labels and choice boxes
        for (int i = 0; i < subjects.size(); i++) {
            Label label = new Label(subjects.get(i).getSubjectName());
            label.setWrapText(true);
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceList.put(label, choiceBox);
            choiceBox.getItems().addAll("A+", "A", "B+", "B", "C+", "C", "D+", "D", "F");
            gridPane.add(label, 0, i);
            gridPane.add(choiceBox, 1, i);
        }

        // Creating a ScrollPane and setting the GridPane as its content
        grandePane.setContent(gridPane);
        grandePane.setFitToWidth(true);
        grandePane.setFitToHeight(true);
    }

    public void calcGrade() {
        Map<String, Double> gradeMap = Map.of("A+", 4.0, "A", 3.7, "B+", 3.5, "B", 3.0,
                "C+", 2.5, "C", 2.0, "D+", 1.5, "D", 1.0, "F", 0.0);
        double totalScore = 0, credit = 0;
        for (var e : choiceList.entrySet()) {
            if (e.getValue().getValue() != null) {
                Subject subject = SubjectDAO.getInstance().selectByID(e.getKey().getText());
                totalScore += gradeMap.get(e.getValue().getValue()) * Double.parseDouble(subject.getCredit());
                credit += Double.parseDouble(subject.getCredit());
            }
        }
        notice.setText("Số điểm bạn đạt dược trên thang điểm 4 là: " + String.format("%.2f",totalScore / credit));
    }

    public void setResetGrande(ActionEvent event){
        notice.setText("Hãy nhập điểm mà bạn dự định sẽ đạt được:");
        for(var x: choiceList.entrySet()){
            x.getValue().getSelectionModel().clearSelection();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main.setVisible(true);
        profileForm.setVisible(false);
        courseForm.setVisible(false);
        gradePane.setVisible(false);
        ChangePassWordPane.setVisible(false);
        borderPaneGrade.setVisible(false);
        setGrande();

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
