package com.cjm.ms;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //createArray();
        //createSortedArray();
        //createGroceryList();
        //createMobilePhone();
        //useAutoboxing();
        //createBank();
        //createCustomers();
        createLinkedList();
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

    // Practice ArrayList via exercise.
    public static void createMobilePhone() {
        System.out.println("\nBEGIN: createMobilePhone");

        MobilePhone phone = new MobilePhone();
        Scanner sc = new Scanner(System.in);

        phone.printInstructions();

        String name;
        String number;
        int choice = 0;
        boolean quit = false;
        while (!quit) {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    phone.printInstructions();
                    break;
                case 1:
                    phone.listContacts();
                    break;
                case 2:
                    System.out.print("Enter name and number: ");
                    name = sc.next() + " " + sc.next();
                    number = sc.next();
                    phone.addContact(name, number);
                    break;
                case 3:
                    System.out.print("Enter name and new number: ");
                    name = sc.next() + " " + sc.next();
                    number = sc.next();
                    phone.modifyContact(name, number);
                    break;
                case 4:
                    System.out.print("Enter name: ");
                    name = sc.next() + " " + sc.next();
                    phone.removeContact(name);
                    break;
                case 5:
                    System.out.print("Enter name: ");
                    name = sc.next() + " " + sc.next();
                    phone.findNumber(name);
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
    }

    // Practice autoboxing.
    public static void useAutoboxing() {
        System.out.println("\nBEGIN: useAutoboxing");

        int[] intArr = new int[5]; // Creates int array.
        String[] strArr = new String[5]; // Creates string array.
        //List<int> intList = new ArrayList<>(); // Error: can't use primitive type.
        List<String> strList = new ArrayList<>(); // Creates string list.

        // Autoboxing is when you convert a primitive type to its wrapper class.
        Boolean booleanWC = Boolean.valueOf(true); // Autoboxes boolean.
        Character charWC = Character.valueOf('c'); // Autoboxes char.
        Byte byteWC = Byte.valueOf((byte)2); // Autoboxes byte.
        Short shortWC = Short.valueOf((short)5); // Autoboxes short.
        Integer intWC = Integer.valueOf(5); // Autoboxes integer.
        Long longWC = Long.valueOf(200); // Autoboxes long.
        Float floatWC = Float.valueOf(5.0f); // Autoboxes float.
        Double doubleWC = Double.valueOf(10.0); // Autoboxes double.

        // Autoboxing shortcut. The compiler converts it to the above.
        Boolean booleanWC2 = true; // Autoboxes boolean.
        Character charWC2 = 'c'; // Autoboxes char.
        Byte byteWC2 = 2; // Autoboxes byte.
        Short shortWC2 = 5; // Autoboxes short.
        Integer intWC2 = 5; // Autoboxes integer.
        Long longWC2 = 200L; // Autoboxes long.
        Float floatWC2 = 5.0f; // Autoboxes float.
        Double doubleWC2 = 10.0; // Autoboxes double.

        // Unboxing is when you convert a wrapper class to its primitive type.
        boolean booleanPT = booleanWC.booleanValue(); // Unboxes boolean.
        char charPT = charWC.charValue(); // Unboxes char.
        byte bytePT = byteWC.byteValue(); // Unboxes byte.
        short shortPT = shortWC.shortValue(); // Unboxes short.
        int intPT = intWC.intValue(); // Unboxes integer.
        long longPT = longWC.longValue(); // Unboxes long.
        float floatPT = floatWC.floatValue(); // Unboxes float.
        double doublePT = doubleWC.doubleValue(); // Unboxes double.

        // Unboxing shortcut. The compiler converts it to the above.
        boolean booleanPT2 = booleanWC2; // Unboxes boolean.
        char charPT2 = charWC2; // Unboxes char.
        byte bytePT2 = byteWC2; // Unboxes byte.
        short shortPT2 = shortWC2; // Unboxes short.
        int intPT2 = intWC2; // Unboxes integer.
        long longPT2 = longWC2; // Unboxes long.
        float floatPT2 = floatWC2; // Unboxes float.
        double doublePT2 = doubleWC2; // Unboxes double.

        List<Integer> intList = new ArrayList<>();
    }

    // Practice autoboxing via exercise.
    public static void createBank() {
        System.out.println("\nBEGIN: createBank");

        Bank bank = new Bank("NatWest Markets");
        bank.addBranch("Tottenham Court Road");
        bank.addBranch("Tavistock Square");

        bank.addTransaction("Tottenham Court Road", "John Doe", 50);
        bank.addTransaction("Tottenham Court Road", "Jane Doe", 100);
        bank.addTransaction("Tavistock Square", "Jack Doe", 20);
        bank.addTransaction("Tavistock Square", "Jo Doe", 15);
        bank.addTransaction("Tottenham Court Road", "John Doe", 100);
        bank.addTransaction("Tavistock Square", "Jack Doe", -5);
        bank.addTransaction("Tavistock Square", "Bill Gates", 9999999);
        bank.addTransaction("Tavistock Squares", "Jack Doe", -5); // Should fail.

        System.out.println();
        bank.listCustomers(true);
    }

    // Practice working with value types and reference types.
    public static void createCustomers() {
        System.out.println("\nBEGIN: createCustomers");

        // new Customer creates Customer object in heap memory.
        // Customer cust1 creates a reference variable type on the stack.
        Customer cust1 = new Customer("John Doe", 50);
        // cust2 points to the same memory location in the heap as cust1.
        Customer cust2 = cust1;
        // Since cust1 and cust2 point to the same location, changing one
        // is the same as changing the other.
        cust2.addTransaction(50);
        System.out.println("cust1 balance: " + cust1.getBalance()); // Both print 100.
        System.out.println("cust2 balance: " + cust2.getBalance());
        // We can create a new Customer object in memory and have
        // cust2 point to that instead so that it changes a different
        // object to cust1.
        cust2 = new Customer("Jane Doe", 200);
        System.out.println("cust1 balance: " + cust1.getBalance()); // Prints John's balance.
        System.out.println("cust2 balance: " + cust2.getBalance()); // Prints Jane's balance.
        // When you pass a reference type (e.g. through a method) they both change the same
        // object in memory. When you pass a value type a copy is made and doesn't
        // affect the original. Value types and reference types exist on the stack,
        // but a reference type points to an object in the heap.
        int val1 = 10;
        int val2 = val1; // Copies the value since it's a value type.
        System.out.println("val1: " + val1);
        System.out.println("val2: " + val2);
        val2 = 20; // val2 changes independently to val1.
        System.out.println("val1: " + val1);
        System.out.println("val2: " + val2);
        // Declaring a variable with a primitive datatype creates a value type.
    }

    // Practice LinkedLists.
    public static void createLinkedList() {
        List<String> places = new LinkedList<>();
        places.add("Sydney");
        places.add("Melbourne");
        places.add("Brisbane");
        places.add("Perth");
        places.add("Canberra");
        places.add("Adelaide");
        places.add("Darwin");

        Iterator<String> iter = places.iterator();

    }
}
