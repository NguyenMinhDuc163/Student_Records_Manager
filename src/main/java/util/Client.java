package util;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {


    private TextArea chatScreen, comment;
    private ListView<String> onlineUsersListView;
    private String clientId, clientName;
    private OutputStream outputStream;

    public Client(TextArea chatScreen, TextArea comment, ListView<String> onlineUsersListView, String clientName, OutputStream outputStream) {
        this.chatScreen = chatScreen;
        this.comment = comment;
        this.onlineUsersListView = onlineUsersListView;
        this.clientName = clientName;
        this.outputStream = outputStream;
    }

    public void handleServerMessages(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println(message);
                if (message.startsWith("CONNECTED:")) {
//                    clientId = Integer.parseInt(message.split(":")[1]);
                    clientId = message.split(":")[1];
                } else if (message.startsWith("USER_JOINED:")) {
                    String joinedUserName = message.split(":")[1];
                    Platform.runLater(() -> {
                        chatScreen.appendText(joinedUserName + " has joined the meeting.\n");
                        updateOnlineUsersList(message);
                    });
                } else if (message.startsWith("USER_LEFT:")) {
                    String leftUserName = message.split(":")[1];
                    Platform.runLater(() -> {
                        chatScreen.appendText(leftUserName + " has left the meeting.\n");
                        updateOnlineUsersList(message);
                    });
                } else if (message.startsWith("USERS_LIST:")) {
                    updateOnlineUsersList(message);
                } else if (message.startsWith("USER_")) {
                    Platform.runLater(() -> chatScreen.appendText(message.replaceAll("USER_\\d+ :", "").trim() + "\n"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOnlineUsersList(String message) {
        String[] parts = message.split(":");
        if (parts.length == 2) {
            String[] users = parts[1].split(",");
            Platform.runLater(() -> {
                onlineUsersListView.getItems().clear();
                onlineUsersListView.getItems().addAll(users);
            });
        }
    }

    public void sendMessage() {
        try {
            String message = clientName + ": " + comment.getText();
            chatScreen.appendText( message + "\n");
            outputStream.write(message.getBytes());
            outputStream.flush();
            comment.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}