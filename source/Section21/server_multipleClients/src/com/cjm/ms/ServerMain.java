package com.cjm.ms;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(5000)) {
            System.out.print("Waiting for clients to connect...\n\n");
            Thread[] clients = {
                    new ClientThread(server),
                    new ClientThread(server),
                    new ClientThread(server)
            };
            StartJoinThreads.exec(clients);
        } catch(IOException e) {
            SynchConsole.println("Server error: " + e.getMessage());
        }
    }
}
