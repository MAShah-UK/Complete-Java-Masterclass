package com.cjm.ms;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        // Connects client to the server.
        // IP and port number must be the same as server.
        // Can be loopback address ("localhost"/"127.0.0.1"), LAN address (192.x.x.x)
        // or Internet address (x.x.x.x).
        try(Socket server = new Socket("localhost", 5000)) {
            // Client will timeout if no message received for 5 seconds.
            server.setSoTimeout(5000);

            BufferedReader receiver = new BufferedReader(
                    new InputStreamReader(server.getInputStream()));
            BufferedWriter sender = new BufferedWriter(
                    new OutputStreamWriter(server.getOutputStream()));

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
                    // Blocks until client receives message.
                    String message = receiver.readLine();
                    System.out.println(message);
                }
            } while(!userInput.equals("exit"));
        } catch(SocketTimeoutException e) {
            System.out.println("Socket timed out.");
        } catch(IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
