import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InetAddress address = InetAddress.getLoopbackAddress();
        try(DatagramSocket socket = new DatagramSocket()) {
            while(true) {
                System.out.print("Enter message: ");
                byte[] buffer = scanner.nextLine().getBytes();
                DatagramPacket sender = new DatagramPacket(
                        buffer, buffer.length, address, 5000);
                socket.send(sender);

                System.out.println("Waiting for acknowledgement...");
                buffer = new byte[50];
                DatagramPacket receiver = new DatagramPacket(
                        buffer, buffer.length);
                // Blocks until data is received.
                socket.receive(receiver);
                System.out.println(new String(buffer));
                System.out.println();
            }
        } catch(IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
