package com.cjm.ms;

import java.io.*;

import java.net.Socket;

public class ClientThread extends Thread {
    private static int instances = 0;
    private final Socket client;
    private int clientId;
    private String address;
    private Executable onClientDisconnect;

    @Override
    public synchronized void run() {
        clientId = ++instances;
        address = client.getInetAddress().toString();

        System.out.println("Client connected:" +
                " [id]: " + clientId +
                " [address]: " + address);
        try(client) {
            BufferedReader receiver = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            BufferedWriter sender = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()));

            while(true) {
                String message = receiver.readLine();
                if(message.equals("exit")) {
                    clientDisconnected("Exit message received.");
                    break;
                }
                SynchConsole.println("Message received:" +
                        " [id]: " + clientId +
                        " [address]: " + address +
                        " [message]: " + message);
                sender.write("Server received: " + message);
                sender.newLine();
                sender.flush();
            }
        } catch(IOException e) {
            clientDisconnected("Server error: " + e.getMessage() + ".");
        }
    }

    public ClientThread(Socket client) {
        this.client = client;
    }

    public static int getInstances() {
        return instances;
    }

    public void setOnClientDisconnect(Executable onClientDisconnect) {
        this.onClientDisconnect = onClientDisconnect;
    }

    public void clientDisconnected(String reason) {
        instances--;
        SynchConsole.println("Client disconnected:" +
                " [id]: " + clientId +
                " [address]: " + address +
                " [reason]: " + reason);
        if(onClientDisconnect != null) {
            onClientDisconnect.run();
        }
    }
}
