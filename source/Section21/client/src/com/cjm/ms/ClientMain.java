package com.cjm.ms;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        // Connects client to the server.
        // IP and port number must be the same as server.
        // Can be loopback address ("localhost"/"127.0.0.1"), LAN address (192.x.x.x)
        // or Internet address (x.x.x.x).
        try(Socket socket = new Socket("localhost", 5000)) {
            BufferedReader receiver = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter sender = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            Scanner scanner = new Scanner(System.in);
            String userInput;
            do {
                System.out.println();
                System.out.print("Enter string to send: ");
                userInput = scanner.nextLine();
                // Can call PrintWriter.println() to do it in one line.
                sender.write(userInput + "\n");
                sender.flush();
                if(!userInput.equals("exit")) {
                    String message = receiver.readLine();
                    System.out.println(message);
                }
            } while(!userInput.equals("exit"));
        } catch(IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
