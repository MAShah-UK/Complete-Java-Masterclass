package com.cjm.ms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        ServerMain main = new ServerMain();
        try(ServerSocket server = new ServerSocket(5000)) {
            System.out.print("Waiting for clients to connect...\n\n");
            while(true) {
                Socket client = server.accept();
                ClientThread clientThread = new ClientThread(client);
                clientThread.setOnClientDisconnect(main::onClientDisconnect);
                clientThread.start();
            }
        } catch(IOException e) {
            SynchConsole.println("Server error: " + e.getMessage());
        }
    }

    public void onClientDisconnect() {
        if(ClientThread.getInstances() == 0) {
            System.out.println("All clients disconnected. Closing server.");
            System.exit(0);
        }
    }
}
