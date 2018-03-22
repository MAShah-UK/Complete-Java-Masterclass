package com.cjm.ms;

public class StaticTest {
    private String name;             // Instance variable.
    private static int numInstances; // Static field / class variable.

    public StaticTest(String name) {
        this.name = name;
        numInstances++;
    }

    public void nonStaticMethod() {

    }

    // Since this method only deals with static fields and/or local variables
    // it doesn't matter what instance it's called by as the output is unaffected.
    // Therefore marking it as static makes sense. It won't be able to access
    // instance variables/methods however, only other static variables/methods.
    public static int getNumInstances() { // Static method / class method.
        // nonStatcMethod(); // Inaccessible.
        return numInstances;
    }

    public String getName() {
        return name;
    }
}
