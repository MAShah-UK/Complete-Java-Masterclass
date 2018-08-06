package com.cjm.ms;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        // Server must be listening before client attempts to connect.
        // Create socket with port 5000.
        // Must be between 0 to 65,535 and not already in use.
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Waiting for client to connect...");
            // Blocks until client connects to server.
            // Each call to accept() will wait for another client.
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            BufferedReader receiver = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter sender = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            while(true) {
                System.out.println();
                System.out.println("Waiting for client message.");
                // Blocks until server receives message.
                String message = receiver.readLine();
                System.out.println("Received message: " + message);
                if(message.equals("exit")) {
                    break;
                }

                System.out.println("Sending server acknowledgement.");
                // Can call PrintWriter.println() to do this in one line.
                sender.write("From server: " + message);
                sender.newLine();
                sender.flush();
            }
        } catch(IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }

    }
}
