package com.cjm.ms;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerMain {
    public static void main(String[] args) {
        try(DatagramSocket server = new DatagramSocket(5000)) {
            byte[] buffer = new byte[50];
            while(true) {
                System.out.println("Waiting for message...");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                // Blocks until data is received.
                server.receive(packet);
                String message = new String(buffer);
                System.out.println("Message received: " + message);
            }
        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
