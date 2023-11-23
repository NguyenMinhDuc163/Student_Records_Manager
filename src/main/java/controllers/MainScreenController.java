package controllers;


import dao.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import service.UpdateDataHandle;
import util.Client;
import util.Server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class MainScreenController implements Initializable {
    @FXML private Label name, studentID, classRoom, majors, year, user;
    @FXML private Label identifyLb, facultyLb, majorLb, yearLb;
    @FXML private Button profile, home, courseBtt, gradebbt,extrabbt, editbbt, commentbbt;
    @FXML private AnchorPane profileForm, main, courseForm, gradePane, ChangePassWordPane;
    @FXML private TableView<Subject> tableSubject;
    @FXML private TableView<Grade> tableGrade;
    @FXML private TableColumn<Subject, String> subjectID, subjectName, creditCol;
    @FXML private TableColumn<Grade, String> nameSV, course, assignmentScore, examScore, practicalScore, attendanceScore;
    @FXML private TableColumn<Grade, String> finalExamScore, componentScore, letterGrade;
    @FXML private TextField msv;
    @FXML private PasswordField oldPass, newPass, confirmPass,captcha;
    @FXML private BorderPane borderPaneGrade, editPane, chatRoom;
    @FXML private ScrollPane grandePane;
    @FXML private Text  fileAddress;
    @FXML private GridPane gridPane;
    @FXML private ImageView imageView, imageLogo;
    @FXML private TextField msvn, ho, ten, maMon, tenMon, maLop, cc, thi, bt, kt, TH, TBHP, heChu;
    @FXML private TextField msvNew, maMonNew;
    @FXML private AnchorPane addData, update, deleteData, addDataAll, rolePane;
    @FXML private Button add, up, del, addAll,cancel;
    @FXML private TextArea notify, thongBao;
    @FXML private TextField msvx, mmh, attendance,finalExam, assignment,exam, practical, component, letter;
    @FXML private TableView<ClassSchedule> scheduleTable;
    @FXML private TableColumn<ClassSchedule, String> time, monday, tuesday, wednesday, thursday, friday,saturday;
    @FXML private TextArea chatScreen, comment, notice;
    @FXML private ListView<String> onlineUsersListView;
    @FXML private Button sendButton;
    private OutputStream outputStream;

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
            editPane.setVisible(false);
            chatRoom.setVisible(false);
        }
        else if(event.getSource() == home){
            profileForm.setVisible(false);
            main.setVisible(true);
            courseForm.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
            editPane.setVisible(false);
            chatRoom.setVisible(false);
        }
        else if(event.getSource() == courseBtt){
            courseForm.setVisible(true);
            profileForm.setVisible(false);
            main.setVisible(false);
            gradePane.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
            editPane.setVisible(false);
            chatRoom.setVisible(false);
        }
        else if(event.getSource() == gradebbt){
            gradePane.setVisible(true);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
            editPane.setVisible(false);
            chatRoom.setVisible(false);
        }
        else if(event.getSource() == extrabbt){
            gradePane.setVisible(false);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(true);
            editPane.setVisible(false);
            chatRoom.setVisible(false);
        }
        else if(event.getSource() == editbbt){
            gradePane.setVisible(false);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
            editPane.setVisible(true);
            chatRoom.setVisible(false);
        }
        else if(event.getSource() == commentbbt){
            chatRoom.setVisible(true);
            gradePane.setVisible(false);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
            editPane.setVisible(false);
        }
    }

    public void setLogout(ActionEvent event) throws IOException {
        SwitchController switchController = new SwitchController();
        switchController.switchToSceneLogin(event);
    }
    public void setSearch(ActionEvent event){
        ObservableList<Grade> gradeList = null;
        Student student1 = StudentDAO.getInstance().selectByID(msv.getText().toUpperCase());
        if(student1 == null) {
            System.out.println("Nhập sai mã sinh viên");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập sai thông tin");
            alert.setHeaderText("Kiểm tra lại thông tin:");
            alert.setContentText("Thông tin đăng nhập không chính xác. Vui lòng kiểm tra lại đúng cú pháp mã sinh viên của PTIT.");
            alert.show();
            return;
        }

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
        notice.setWrapText(true);
        notice.setText("Số điểm bạn đạt dược trên thang điểm 4 là: " + String.format("%.2f",totalScore / credit));
        notice.appendText("\n\n Chúc bạn đạt được mục tiêu trong kì tới !");
    }

    public void setResetGrande(ActionEvent event){
        notice.setText("Hãy nhập điểm mà bạn dự định sẽ đạt được:");

        for(var x: choiceList.entrySet()){
            x.getValue().getSelectionModel().clearSelection();
        }
    }

    public boolean isCheckInput(String studentID, String courseID){
        Student student1 = StudentDAO.getInstance().selectByID(studentID);
        Course course1 = CourseDAO.getInstance().selectByID(courseID);
        return student1 != null && course1 != null;
    }
    public void getDataEdit(ActionEvent event){
        UpdateDataHandle update = new UpdateDataHandle();
        String studentID = msvNew.getText().toUpperCase();
        String firstName = ho.getText();
        String lastName = ten.getText();
        String courseID = maMonNew.getText().toUpperCase();
        String courseName = tenMon.getText();
        String classID = maLop.getText().toUpperCase();
        if(isCheckInput(studentID, courseID)){
            update.createProfile(studentID, firstName, lastName, courseID, courseName, classID,
                    cc.getText(), thi.getText(), bt.getText(), kt.getText(), TH.getText(), TBHP.getText(), heChu.getText());
            notify.appendText("Đã cập nhật thông tin cho sinh viên: " + studentID + "\n");
        }
        else notify.appendText("Dữ liệu nhập vào không đúng,  Hãy kiểm tra lại mã sinh viên hoặc mã khoá học\n");
    }
    public void setDeleteData(ActionEvent event){
        String studentID = msvn.getText();
        String courseID = maMon.getText();
        if(isCheckInput(studentID, courseID)){
            UpdateDataHandle update = new UpdateDataHandle();
            update.deleteData(studentID, courseID);
            notify.appendText("Đã xoá điểm môn học " + courseID + " của sinh viên " + studentID + "\n");
        }
        else notify.appendText("Dữ liệu nhập vào không đúng,  Hãy kiểm tra lại mã sinh viên hoặc mã khoá học\n");

    }

    public void setGradeData(){
        UpdateDataHandle update = new UpdateDataHandle();
        String studentID = msvx.getText().toUpperCase();
        String courseID = mmh.getText().toUpperCase();
        if(isCheckInput(studentID, courseID)){
            update.updateGrade(studentID, courseID, attendance.getText(), finalExam.getText(), assignment.getText(),
                    exam.getText(), practical.getText(), component.getText(), letter.getText());
            notify.appendText("Đã cập nhật điểm môn học " + courseID + " cho sinh viên " + studentID + "\n");
        }
        else notify.appendText("Dữ liệu nhập vào không đúng,  Hãy kiểm tra lại mã sinh viên hoặc mã khoá học\n");


    }

    public void setUpdateAll(ActionEvent event){
        Stage stage = (Stage) editPane.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a image");
        FileChooser.ExtensionFilter fileReader = new FileChooser.ExtensionFilter("All Files", "*.*");
        fc.getExtensionFilters().add(fileReader);
        File file = fc.showOpenDialog(stage);
        if(file !=  null){
            notify.appendText("Bạn đã chọn file " + file.getName() + "\n");
            fileAddress.setText(file.getPath());
        }else notify.appendText("Bạn đã chọn không tồn tại \n");


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Tinh năng:");
        alert.setContentText("Tính năng đang gặp một số lỗi vui lòng thử lại sau.");
        alert.show();

    }
    public void setNotice(){
        thongBao.setWrapText(true);
        thongBao.appendText("1, PTIT là trường đầu tiên trở thành Đại học số và có văn phòng tại nước ngoài\n\n");
        thongBao.appendText("2, Mỗi thầy cô PTIT là một tấm gương nhanh nhạy nắm bắt các xu hướng chuyển dịch\n\n");
        thongBao.appendText("3, Học viện Công nghệ Bưu chính Viễn thông tổ chức Lễ tốt nghiệp năm 2023-đợt 2\n\n");
        thongBao.appendText("4, Phiên họp thứ 14 của Hội đồng Học viện Học viện Công nghệ Bưu chính Viễn thông\n\n");
        thongBao.appendText("5, Tưng bừng các hoạt động tri ân chào mừng kỷ niệm ngày Nhà Giáo Việt Nam\n\n");
        thongBao.appendText("6, Bộ trưởng Nguyễn Mạnh Hùng: “Muốn học tốt thì hãy hỏi nhiều hơn\n\n");

    }

    public void setSeenMessage(){

    }
    public void setScheduleTable(){

        ObservableList<ClassSchedule> schedulesList = FXCollections.observableArrayList(
                new ClassSchedule("7:00 AM", "", "", "", "", "", "Mạng máy tính"),
                new ClassSchedule("9:00 AM", "Lập trình C++", "", "", "", "", "Mạng máy tính"),
                new ClassSchedule("10:00 AM", "", "", "Tiếng Anh", "", "", "Lịch sử Đảng"),
                new ClassSchedule("13:00 PM", "", "", "", "Lập trình hướng đối tượng", "", "Python"),
                new ClassSchedule("15:00 PM", "", "", "", "Lập trình hướng đối tượng", "", "Python"),
                new ClassSchedule("16:00 PM", "", "DSA", "", "", "", "Hệ điều hành"),
                new ClassSchedule("17:00 PM", "", "", "", "", "", "Hệ điều hành"),
                new ClassSchedule("19:00 PM", "", "", "", "", "", "Cơ sở dữ liệu")

        );
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        monday.setCellValueFactory(new PropertyValueFactory<>("monday"));
        tuesday.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        wednesday.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        thursday.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        friday.setCellValueFactory(new PropertyValueFactory<>("friday"));
        saturday.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        scheduleTable.setItems(schedulesList);
    }
    public void setBbtUpdate(ActionEvent event){
        if(event.getSource() == add){
            addData.setVisible(true);
            update.setVisible(false);
            deleteData.setVisible(false);
            addDataAll.setVisible(false);
        }
        else if(event.getSource() == up){
            addData.setVisible(false);
            update.setVisible(true);
            deleteData.setVisible(false);
            addDataAll.setVisible(false);
        }
        else if(event.getSource() == del){
            addData.setVisible(false);
            update.setVisible(false);
            deleteData.setVisible(true);
            addDataAll.setVisible(false);
        }
        else if(event.getSource() == cancel){
            gradePane.setVisible(true);
            courseForm.setVisible(false);
            profileForm.setVisible(false);
            main.setVisible(false);
            ChangePassWordPane.setVisible(false);
            borderPaneGrade.setVisible(false);
            editPane.setVisible(false);
        }
        else if(event.getSource() == addAll){
            addData.setVisible(false);
            update.setVisible(false);
            deleteData.setVisible(false);
            addDataAll.setVisible(true);
        }
    }

    private  boolean isServerRunning(String serverHost, int serverPort) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(serverHost, serverPort), 1000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!isServerRunning("localhost", 5001)){
            new Thread(() ->{
                Server server = new Server();
                server.startServer();
            }).start();

        }


        main.setVisible(true);
        profileForm.setVisible(false);
        courseForm.setVisible(false);
        gradePane.setVisible(false);
        ChangePassWordPane.setVisible(false);
        borderPaneGrade.setVisible(false);
        editPane.setVisible(false);
        notify.setEditable(false);
        chatRoom.setVisible(false);
        setGrande();
        setScheduleTable();
        setNotice();
        // check quyen

        if(LoginHandler.getInstance().checkRole()){
            rolePane.setVisible(true);
        }
        String ID = LoginHandler.getUser();
        if(ID.startsWith("GV")){
            identifyLb.setText("Mã Giảng Viên");
            facultyLb.setText("Chuyên Ngành");
            majorLb.setText("Phòng ban");
            yearLb.setVisible(false);
            year.setVisible(false);
            Teachers teachers = TeacherDao.getInstance().selectByID(ID);
            name.setText(teachers.getTeacherName());
            user.setText(teachers.getTeacherName());
            studentID.setText(teachers.getTeacherID());
            classRoom.setText(teachers.getFaculty());
            majors.setText(teachers.getDepartment());
        }else {
            student = StudentDAO.getInstance().selectByID(ID);
            Classes classes= ClassesDAO.getInstance().selectByID(ID);
            name.setText(student.getFirstName() + " " + student.getLastName());
            studentID.setText(student.getStudentID());
            classRoom.setText(classes.getClassID());
            majors.setText("Công nghệ thông tin");
            year.setText("D21");
            user.setText(student.getFirstName() + " " + student.getLastName());
        }


        ObservableList<Subject> coursesList = FXCollections.observableArrayList(SubjectDAO.getInstance().selectAll());
        subjectID.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectID"));
        subjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectName"));
        creditCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("credit"));
        tableSubject.setItems(coursesList);



        try {
            Socket socket = new Socket("localhost", 5001);
            outputStream = socket.getOutputStream();
            Client client = new Client(chatScreen, comment, onlineUsersListView, name.getText(), outputStream);
            new Thread(() -> client.handleServerMessages(socket)).start();

            sendButton.setOnAction(event -> client.sendMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
