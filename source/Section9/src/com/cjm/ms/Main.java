package com.cjm.ms;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //createArray();
        //createSortedArray();
        createGroceryList();
    }

    // Practice creating array.
    public static void createArray() {
        System.out.println("\nBEGIN: createArray");

        // Stores a single value.
        int val = 5;
        /* Arrays can store multiple values.
        They are fixed length so you can't directly add more values to the array.
        Syntax 1 & 2 automatically determine how many elements are in array.*/
        int[] vals1 = {5, 5, 7}; // Syntax 1.
        int[] vals2 = new int[]{5, 5, 7}; // Syntax 2.
        int[] vals3 = new int[3]; // Syntax 3.
        vals3[0] = vals3[1] = 5; // Zero-based index.
        vals3[2] = 7;
        System.out.println("Third element of array is at index 2 and has value " + vals3[2] + ".");
        //vals3[3] = 9; // Throws ArrayIndexOutOfBoundsException.

        System.out.println("vals1 contains the following values:");
        for (int i = 0; i < vals1.length; i++) { // Indexed for loop.
            System.out.println(vals1[i]);
        }

        System.out.println("vals2 contains the following values:");
        for (int value : vals2) { // For each loop.
            System.out.println(value);
        }

        // Calculate average of user input.
        int[] inputs = getUserInput();
        int sum = 0;
        for(int i : inputs) {
            sum += i;
        }
        System.out.println("The average is: " + (double)sum/inputs.length);
    }

    // Practice getting input from user and storing in array.
    public static int[] getUserInput() {
        // Use scanner to get user input.
        Scanner sc = new Scanner(System.in);
        System.out.println("How many values in the array? ");
        int arrLen = sc.nextInt(); // Prompts for an integer.
        // Array length can be a literal or an expression.
        int[] inputs = new int[arrLen];

        for (int i = 0; i < arrLen; i++) {
            System.out.println("Enter value for index " + i + ":");
            inputs[i] = sc.nextInt();
        }

        return inputs;
    }

    public static void createSortedArray() {
        System.out.println("\nBEGIN: createSortedArray");

        ArraySorter aSort = new ArraySorter();
        int[] input = aSort.getIntegers();
        aSort.printArray(input);
        System.out.println();
        int[] sorted = aSort.sortIntegers(input);
        aSort.printArray(sorted);
    }

    // Practice working with ArrayLists.
    public static void createGroceryList() {
        System.out.println("\nBEGIN: createGroceryList");

        Scanner sc = new Scanner(System.in);
        GroceryList gList = new GroceryList();
        gList.printInstructions();

        int position;
        String item;
        int choice;
        boolean quit = false;
        while(!quit) {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    gList.printInstructions();
                    break;
                case 1:
                    gList.printGroceryList();
                    break;
                case 2:
                    System.out.print("Enter item: ");
                    item = sc.next();
                    gList.addGroceryItem(item);
                    break;
                case 3:
                    System.out.print("Enter position, and item: ");
                    position = sc.nextInt();
                    item = sc.next();
                    gList.modifyGroceryItem(position, item);
                    break;
                case 4:
                    System.out.print("Enter position: ");
                    position = sc.nextInt();
                    gList.removeGroceryItem(position);
                    break;
                case 5:
                    System.out.print("Enter item: ");
                    item = sc.next();
                    gList.findItem(item);
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
    }
}
