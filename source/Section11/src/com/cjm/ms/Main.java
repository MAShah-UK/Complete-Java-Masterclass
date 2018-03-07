package com.cjm.ms;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        printNumbers();
    }

    // Practice work with generics.
    public static void printNumbers() {
        // If type parameter isn't specified it defaults to Object.
        // The list can now accept any datatype due to upcasting,
        // but this is not recommended since it breaks type checking.
        Arraylist items = new ArrayList();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);

        for (Object i : n) {
            System.out.println((Integer) i * 2); // 2, 4, 6, 8, 10.
        }
    }
}
