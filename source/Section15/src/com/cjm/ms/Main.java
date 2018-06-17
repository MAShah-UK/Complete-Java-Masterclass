package com.cjm.ms;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("BEGIN: Exceptions");

        System.out.println("The result of 50/0 is: ");
        System.out.println(divideLBYL(50, 0));
        System.out.println();

        System.out.println("The result of 50/0 is: ");
        System.out.println(divideEAFP(50, 0));
        System.out.println();

        System.out.println("User input:");
        System.out.println(getInt());
        System.out.println();

        System.out.println("User input:");
        System.out.println(getIntLBYL());
        System.out.println();

        System.out.println("User inputs:");
        try {
            System.out.println(userDivide());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("\nBEGIN: IO");

        createAdventureGame();
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
    // Handles multiple exceptions.
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
    private static void createAdventureGame() {
        Locations locations = new Locations();

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
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
                loc = exits.get(direction);

            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
