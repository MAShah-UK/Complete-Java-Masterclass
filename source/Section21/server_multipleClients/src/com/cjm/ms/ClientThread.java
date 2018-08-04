package com.cjm.ms;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread {
    private static int instances = 0;
    private ServerSocket server;

    @Override
    public synchronized void run() {
        try {
            Socket client = server.accept();
            final int clientId = ++instances;
            final String address = client.getInetAddress().toString();

            System.out.println("Client connected:" +
                    " [id]: " + clientId +
                    " [address]: " + address);

            BufferedReader receiver = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            BufferedWriter sender = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()));

            while(true) {
                String message = receiver.readLine();
                if(message.equals("exit")) {
                    SynchConsole.println("Client disconnected:" +
                            " [id]: " + clientId +
                            " [address]: " + address);
                    break;
                }
                SynchConsole.println("Message received:" +
                        " [id]: " + (clientId + "/" + instances) +
                        " [address]: " + address +
                        " [message]: " + message);
                sender.write("Server received: " + message);
                sender.newLine();
                sender.flush();
            }
        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public ClientThread(ServerSocket server) {
        this.server = server;
    }
}
