package Test;

import controllers.MainScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            MainScreenController screenController = new MainScreenController();
            primaryStage.setScene(screenController.setScene());
            primaryStage.setTitle("Student Records Manager");
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
