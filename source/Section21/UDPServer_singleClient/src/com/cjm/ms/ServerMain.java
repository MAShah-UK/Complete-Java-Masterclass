package com.cjm.ms;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerMain {
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(5000)) {
            while(true) {
                System.out.println("Waiting for message...");
                byte[] buffer = new byte[50];
                DatagramPacket receiver = new DatagramPacket(buffer, buffer.length);
                // Blocks until data is received.
                socket.receive(receiver);
                String message = new String(buffer);
                String acknowledgement = "Message received: " + message;
                System.out.println(acknowledgement);

                System.out.println("Sending acknowledgement...");
                DatagramPacket sender = new DatagramPacket(acknowledgement.getBytes(),
                        acknowledgement.length(), receiver.getAddress(), receiver.getPort());
                socket.send(sender);
                System.out.println();
            }
        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
