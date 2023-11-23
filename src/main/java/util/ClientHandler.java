package util;

import controllers.MainScreenController;

import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private static final AtomicInteger nextClientId = new AtomicInteger(1);

    private Socket clientSocket;
    private Server server;
    private int clientId;
    private String clientName;
    public ClientHandler(Socket clientSocket, Server server, String name) {
        this.clientSocket = clientSocket;
        this.server = server;
//        this.clientId = nextClientId.getAndIncrement();
        this.clientName = name;
    }


    @Override
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

//            output.write(("CONNECTED:" + clientId).getBytes());
            output.write(("CONNECTED:" + clientName).getBytes());

            server.broadcastUserList();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
//                server.broadcastMessage("USER_" + clientId + ": " + message, this);
                server.broadcastMessage("USER_" + clientName + ": " + message, this);
            }
        } catch (IOException e) {
            // Handle exceptions as needed
        } finally {
            server.removeClient(this);
        }
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void sendMessage(String message) {
        try {
            OutputStream output = clientSocket.getOutputStream();
            output.write(message.getBytes());
            output.flush();
        } catch (IOException e) {
            // Handle exceptions as needed
        }
    }
}
