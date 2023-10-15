module com.example.studentrecordsmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


//    opens com.example.studentrecordsmanager to javafx.fxml;
//    exports com.example.studentrecordsmanager;

    opens controllers to javafx.fxml;
    exports controllers;

    opens app to javafx.fxml;
    exports app;

}