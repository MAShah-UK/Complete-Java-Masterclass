package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.basicDebugging();
        main.bankAccount();
    }
    // Practice basic debugging concepts.
    public void basicDebugging() {
        System.out.println("BEGIN: basicDebugging");

        StringUtilities utilities = new StringUtilities();

        // Used debugger and its UI, breakpoints, watches, and watchpoints.
        StringBuilder builder = new StringBuilder();
        // Infinite loop leads to OutOfMemoryError.
        while(builder.length() < 10) {
            utilities.addChar(builder, 'a');
        }
        System.out.println(builder);

        // Used smart step into
        String str = "abcdefg";
        String result = utilities.upperAndPrefix(utilities.addSuffix(str));
    }
    // Practice unit testing via JUnit.
    public void bankAccount() {
        System.out.println("\nBEGIN: bankAccount");


    }
}
