package util;

import controllers.MainScreenController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int PORT = 5001;
    private List<ClientHandler> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    private String clientName;
    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
//                    MainScreenController controller = new MainScreenController();
//                    clientName = controller.getClientName();

                ClientHandler clientHandler = new ClientHandler(clientSocket, this, System.currentTimeMillis() + " ");
//                ClientHandler clientHandler = new ClientHandler(clientSocket, this, clientName + " ");
                clients.add(clientHandler);

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastUserList();
    }

    public void broadcastUserList() {
        StringBuilder userList = new StringBuilder("USERS_LIST:");
        for (ClientHandler client : clients) {
//            userList.append(client.getClientId()).append(",");
            userList.append(client.getClientName()).append(",");
        }
        for (ClientHandler client : clients) {
            client.sendMessage(userList.toString());
        }
    }

//    public static void main(String[] args) {
//        Server server = new Server();
//        server.startServer();
//    }
}