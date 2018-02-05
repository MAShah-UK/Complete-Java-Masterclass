package com.cjm.ms;

// Exercise:
// Create a program using arrays that sorts a list of integers in descending order.
// Descending order is highest value to lowest.
// In other words if the array had the values [106, 26, 81, 5, 15] your program should
// ultimately have an array with values [106, 81, 26, 15, 5].
// Set up the program so that the numbers to sort are read in from the keyboard.
// Implement the following methods - getIntegers, printArray, and sortIntegers:
//    getIntegers returns an array of entered integers from keyboard.
//    printArray prints out the contents of the array.
//    sortIntegers should sort the array and return a new array containing the sorted numbers.
// You will have to figure out how to copy the array elements from the passed array into a new
// array and sort them and return the new sorted array.

import java.util.Scanner;

public class ArraySorter {
    public int[] getIntegers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many integers in the array?");
        int len = sc.nextInt();
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            System.out.println("Enter integer for index " + i + ": ");
            arr[i] = sc.nextInt();
        }

        return arr;
    }

    public void printArray(int[] arr) {
        System.out.println("Values of array are: ");
        // System.out.println(arr.toString()); // Simple solution.
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public int[] sortIntegers(int[] arr) {
        int[] sort;
        // Simple solution:
        // sort = Arrays.copyOf(arr, arr.length);
        // Arrays.sort(sort);
        // return sort;
        sort = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sort[i] = arr[i];
        }

        // Implement descending bubble sort.
        for (int i = 0; i < sort.length - 1; i++) {
            if (sort[i] < sort[i+1]) {
                int tmp = sort[i];
                sort[i] = sort[i+1];
                sort[i+1] = tmp;
                i = 0; // Restart loop if a local sort was required.
            }
        }

        return sort;
    }
}
