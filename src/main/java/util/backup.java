//package util;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//
//public class Client extends Application {
//
//    private static final String SERVER_HOST = "localhost";
//    private static final int SERVER_PORT = 5001;
//
//    private TextArea chatArea;
//    private TextField messageField;
//    private Button sendButton;
//    private ListView<String> onlineUsersListView;
//
//    private int clientId;
//    private String clientName;
//    private OutputStream outputStream;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Chat Client");
//
//        chatArea = new TextArea();
//        chatArea.setEditable(false);
//
//        messageField = new TextField();
//        sendButton = new Button("Send");
//        onlineUsersListView = new ListView<>();
//
//        VBox vBox = new VBox(chatArea, messageField, sendButton, onlineUsersListView);
//
//        Scene scene = new Scene(vBox, 400, 300);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        try {
//            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
//            outputStream = socket.getOutputStream();
//
//            new Thread(() -> handleServerMessages(socket)).start();
//
//            sendButton.setOnAction(event -> sendMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleServerMessages(Socket socket) {
//        try {
//            InputStream input = socket.getInputStream();
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//
//            while ((bytesRead = input.read(buffer)) != -1) {
//                String message = new String(buffer, 0, bytesRead);
//                System.out.println(message);
//                if (message.startsWith("CONNECTED:")) {
////                    clientId = Integer.parseInt(message.split(":")[1]);
//                    clientName = message.split(":")[1];
//                } else if (message.startsWith("USER_JOINED:")) {
//                    String joinedUserName = message.split(":")[1];
//                    Platform.runLater(() -> {
//                        chatArea.appendText(joinedUserName + " has joined the meeting.\n");
//                        updateOnlineUsersList(message);
//                    });
//                } else if (message.startsWith("USER_LEFT:")) {
//                    String leftUserName = message.split(":")[1];
//                    Platform.runLater(() -> {
//                        chatArea.appendText(leftUserName + " has left the meeting.\n");
//                        updateOnlineUsersList(message);
//                    });
//                } else if (message.startsWith("USERS_LIST:")) {
//                    updateOnlineUsersList(message);
//                } else if (message.startsWith("USER_")) {
//                    Platform.runLater(() -> chatArea.appendText(message + "\n"));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateOnlineUsersList(String message) {
//        String[] parts = message.split(":");
//        if (parts.length == 2) {
//            String[] users = parts[1].split(",");
//            Platform.runLater(() -> {
//                onlineUsersListView.getItems().clear();
//                onlineUsersListView.getItems().addAll(users);
//            });
//        }
//    }
//
//    private void sendMessage() {
//        try {
//            String message = messageField.getText();
//            chatArea.appendText(message + "\n");
//            outputStream.write(message.getBytes());
//            outputStream.flush();
//            messageField.clear();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}