package com.cjm.ms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            Socket socket = serverSocket.accept();
            socket.
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
                // Blocks until server receives message.
                String echoString = input.readLine();
                if(echoString.equals("exit")) {
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
        } catch(IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }

    }
}
