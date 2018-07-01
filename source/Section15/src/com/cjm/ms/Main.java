package com.cjm.ms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Exceptions handling.
//        System.out.println("BEGIN: Exception handling");
//
//        System.out.println("The result of 50/0 is: ");
//        System.out.println(divideLBYL(50, 0));
//        System.out.println();
//
//        System.out.println("The result of 50/0 is: ");
//        System.out.println(divideEAFP(50, 0));
//        System.out.println();
//
//        System.out.println("User input:");
//        System.out.println(getInt());
//        System.out.println();
//
//        System.out.println("User input:");
//        System.out.println(getIntLBYL());
//        System.out.println();
//
//        System.out.println("User inputs:");
//        try {
//            System.out.println(userDivide());
//        } catch (ArithmeticException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();

        // IO.
//        System.out.println("BEGIN: IO");
//        createAdventureGame();
        func();
    }
    /*
        Uses 'look before you leap' concept in which you ensure
        that the arguments are valid before using them.
     */
    private static int divideLBYL(int x, int y) {
        // Division by zero is undefined.
        if(y != 0) {
            return x/y;
        } else {
            System.out.println("Can't divide by zero.");
            // Returning 0 is technically incorrect.
            return 0;
        }
    }
    /*
        Uses 'easy to ask for forgiveness than permission' concept
        in which you execute code then deal with potential fallout.
     */
    private static int divideEAFP(int x, int y) {
        try {
            return x/y;
        } catch(ArithmeticException e) {
            System.out.println("Can't divide by zero.");
            return 0;
        }
    }
    // Gets input from user. Expects integer, otherwise will crash.
    private static int getInt() {
        System.out.print("Enter an integer (no validation, might crash): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    // Validates input. Returns 0 for invalid input.
    private static int getIntLBYL() {
        System.out.print("Enter an integer (validated, won't crash): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for(char symbol : input.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                System.out.println("Invalid input.");
                return 0;
            }
        }
        System.out.println("Valid input.");
        return Integer.parseInt(input);
    }
    // Also validates input, but uses exceptions.
    private static int getIntEAFP() {
        System.out.print("Enter an integer (validated, won't crash): ");
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        try {
            value = scanner.nextInt();
            System.out.println("Valid input.");
        } catch(InputMismatchException e) {
            System.out.println("Invalid input.");
        }
        return value;
    }
    // Practice handling multiple exceptions.
    private static double userDivide() {
        double value = 0;
        boolean isValid = false;
        // Forces user to enter valid input.
        while(!isValid) {
            try {
                double x = getInt();
                double y = getInt();
                value = x/y;
                isValid = true;
            } catch(InputMismatchException e) { // Can catch multiple exceptions in one statement using |.
                System.out.println("Invalid input: Only digits are accepted.");
            } catch(ArithmeticException e) {
                // Propagates exception to allow caller to deal with it.
                throw new ArithmeticException("Invalid input: Can't divide by zero.");
            }
        }
        System.out.println("Valid input.");
        return value;
    }
    // Practice working with input and output data using IO and NIO packages.
    private static void createAdventureGame() throws IOException {
        Locations locations;
        try {
            locations = new Locations();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        Location currentLocation = locations.get(64);
        while(true) {
            System.out.println(currentLocation.getDescription());

            if(currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                currentLocation = locations.get(currentLocation.getExits().get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();
    }
    // Practice working with input and output data using more of the NIO package.
    private static void func() { // TODO: Rename.
        try {
//            FileInputStream file = new FileInputStream("data.txt");
//            // FileChannel is input or output only depending on which FileXStream is used.
//            FileChannel channel = file.getChannel(); // Input only.
            // Write and read data using Files class.
            Path dataPath = FileSystems.getDefault().getPath("data.txt");
            Files.write(dataPath, "\nLine 7".getBytes(), StandardOpenOption.APPEND);
            // Use Files to sequentially read or write data using NIO.
            List<String> lines = Files.readAllLines(dataPath);
            for(String line: lines) {
                System.out.println(line);
            }

            // Write data using file channel.
            FileOutputStream binFile = new FileOutputStream("data.dat");
            FileChannel binChannel = binFile.getChannel();
            byte[] outputBytes = "Hello World!".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes); // Resets position to 0.
            binChannel.write(buffer);
            binChannel.close(); // Should really use try-with-resources statement.

            // Write integer data using file channel.
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip(); // Resets position to 0.
            binChannel.write(intBuffer);

            // Do it again, but overwrite the contents of the buffer first.
            intBuffer.flip(); // Have to flip due to writing buffer contents.
            intBuffer.putInt(-98765); // New integer value overwrites old integer value.
            intBuffer.flip(); // Have to flip again since we wrote to the buffer.
            binChannel.write(intBuffer);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
