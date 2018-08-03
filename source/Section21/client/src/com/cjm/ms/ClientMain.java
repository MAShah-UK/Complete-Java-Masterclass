package com.cjm.ms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        // Connects client to the server.
        // IP and port number must be the same as server.
        // Can be loopback address ("localhost"/"127.0.0.1"), LAN address (192.x.x.x)
        // or Internet address (x.x.x.x).
        try(Socket socket = new Socket("localhost", 5000)) {
            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                stringToEcho.println(echoString);
                if(!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while(!echoString.equals("exit"));
        } catch(IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
