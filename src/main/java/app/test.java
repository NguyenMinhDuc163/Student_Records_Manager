//package app;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//
//public class test extends Application {
//
//    public static class ClassSchedule {
//        private String time;
//        private String monday;
//        private String tuesday;
//        private String wednesday;
//        private String thursday;
//        private String friday;
//
//        public ClassSchedule(String time, String monday, String tuesday, String wednesday, String thursday, String friday) {
//            this.time = time;
//            this.monday = monday;
//            this.tuesday = tuesday;
//            this.wednesday = wednesday;
//            this.thursday = thursday;
//            this.friday = friday;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public String getMonday() {
//            return monday;
//        }
//
//        public String getTuesday() {
//            return tuesday;
//        }
//
//        public String getWednesday() {
//            return wednesday;
//        }
//
//        public String getThursday() {
//            return thursday;
//        }
//
//        public String getFriday() {
//            return friday;
//        }
//    }
//
//    @Override
//    public void start(Stage stage) {
//        TableView<ClassSchedule> timetable = new TableView<>();
//        ObservableList<ClassSchedule> data = FXCollections.observableArrayList(
//                new ClassSchedule("8:00 AM", "Class A", "Class B", "Class C", "Class D", "Class E"),
//                new ClassSchedule("9:00 AM", "Class F", "Class G", "Class H", "Class I", "Class J")
//                // Add more data as needed
//        );
//
//        TableColumn<ClassSchedule, String> timeColumn = new TableColumn<>("Time");
//        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
//        timetable.getColumns().add(timeColumn);
//
//        TableColumn<ClassSchedule, String> mondayColumn = new TableColumn<>("Monday");
//        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("monday"));
//        timetable.getColumns().add(mondayColumn);
//
//        TableColumn<ClassSchedule, String> tuesdayColumn = new TableColumn<>("Tuesday");
//        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
//        timetable.getColumns().add(tuesdayColumn);
//
//        TableColumn<ClassSchedule, String> wednesdayColumn = new TableColumn<>("Wednesday");
//        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
//        timetable.getColumns().add(wednesdayColumn);
//
//        TableColumn<ClassSchedule, String> thursdayColumn = new TableColumn<>("Thursday");
//        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursday"));
//        timetable.getColumns().add(thursdayColumn);
//
//        TableColumn<ClassSchedule, String> fridayColumn = new TableColumn<>("Friday");
//        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("friday"));
//        timetable.getColumns().add(fridayColumn);
//
//        timetable.setItems(data);
//
//        Scene scene = new Scene(timetable);
//        stage.setScene(scene);
//        stage.setTitle("Weekly Timetable");
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
