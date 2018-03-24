package com.cjm.ms;

// Static initialisation block.
public class SIBTest {
    public static final String owner;

    // Not often used, but SIBs run when the class is loaded and can be used to
    // initialise static final fields. You can create multiple SIBs and they
    // run in the order declared, but before any other constructor/method.
    // SIBs will only run the first time new Class() is called.
    static {
        owner = "Tim";
        System.out.println("SIBTest static initialisation block called.");
    }

    public SIBTest() {
        System.out.println("SIB constructor called.");
    }

    static {
        System.out.println("2nd initiation block called.");
    }

    public void someMethod() {
        System.out.println("someMethod called.");
    }
}
