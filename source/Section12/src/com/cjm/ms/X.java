package com.cjm.ms;

// Exercise:
// Write a small program to read an integer from the keyboard
// (using Scanner) and print out the times table for that number.
// The table should run from 1 to 12.
//
// You are allowed to use one variable called scanner for your
// Scanner instance. You can use as many other variables as you
// need, but they must must all be called x. That includes any
// class instances and loop control variables that you may decide
// to use.
//
// If you use a class, the class can be called X (capital), but
// any instances of it must be called x (lower case).
//
// Any methods you create must also be called x.
//
// Optional Extra:
// Change your program so that ALL variables (including the scanner
// instance) are called x.

import java.util.Scanner;

public class X {
    // Tim's solution didn't store the scanner instance so he could use a for loop
    // with an index variable called x.
    private Scanner x;

    public X() {
        this.x = new Scanner(System.in);
    }

    public void x() {
        System.out.print("Enter value to get its multiplication table from 1 to 12: ");
        int x = this.x.nextInt();
        System.out.println(x + " x " + 1 + " = " + x);
        System.out.println(x + " x " + 2 + " = " + (x*2));
        System.out.println(x + " x " + 3 + " = " + (x*3));
        System.out.println(x + " x " + 4 + " = " + (x*4));
        System.out.println(x + " x " + 5 + " = " + (x*5));
        System.out.println(x + " x " + 6 + " = " + (x*6));
        System.out.println(x + " x " + 7 + " = " + (x*7));
        System.out.println(x + " x " + 8 + " = " + (x*8));
        System.out.println(x + " x " + 9 + " = " + (x*9));
        System.out.println(x + " x " + 10 + " = " + (x*10));
        System.out.println(x + " x " + 11 + " = " + (x*11));
        System.out.println(x + " x " + 12 + " = " + (x*12));
    }
}
