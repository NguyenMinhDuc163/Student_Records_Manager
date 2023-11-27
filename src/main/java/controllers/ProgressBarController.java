package controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Objects;

public class ProgressBarController extends Task<Void> {
    private Stage progressStage;
    private ProgressBar progressBar;
    private Label progressLabel;
    private Long time;
    public ProgressBarController(Long time) {
        this.time = time;
        initializeUI();
    }
    public static ProgressBarController getInstance(Long time){
        return new ProgressBarController(time);
    }
    private void initializeUI() {
        progressStage = new Stage();
        progressStage.setTitle("Processing");
        try {
            progressStage.getIcons().add(new Image(String.valueOf(new File("src/main/resources/images/Logo_PTIT.png").toURI().toURL())));
        }catch (Exception e){
            e.printStackTrace();
        }

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);

        progressLabel = new Label("0%");

        StackPane progressLayout = new StackPane(progressBar, progressLabel);
        Scene progressScene = new Scene(progressLayout, 400, 100);
        progressStage.setScene(progressScene);
    }

    public void showProgressBar() {
        progressStage.show();
        // Gọi class ProgressBarTask để thực hiện công việc
        new Thread(this).start();
    }

    public void closeProgressBar() {
        Platform.runLater(() -> progressStage.close());
    }

@Override
protected Void call() throws Exception {
    long startTime = System.currentTimeMillis();

    while (System.currentTimeMillis() - startTime < time) {

        double progressValue = (System.currentTimeMillis() - startTime) / (double) time;

        Platform.runLater(() -> {
            try {
                // Cập nhật ProgressBar và Label trong luồng ứng dụng JavaFX
                progressBar.setProgress(progressValue);
                String progressText = String.format("%.0f%%", progressValue * 100);
                progressLabel.setText(progressText);
            } catch (Exception ignored) {
            }
        });

        // Ngủ một khoảng thời gian ngắn (ví dụ: 50 mili giây)
        Thread.sleep(50);
    }

    closeProgressBar(); // Gọi method để đóng ProgressBar khi công việc hoàn thành
    return null;
}
}
