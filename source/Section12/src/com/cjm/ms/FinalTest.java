package com.cjm.ms;

// Stops the class from being subclassed.
public final class FinalTest {
    private static int classCounter = 0;
    // final fields can be assigned during declaration OR in the constructor, never again.
    private final int instanceNumber;
    private final String name;

    public FinalTest(String name) {
        this.name = name;
        classCounter++;
        instanceNumber = classCounter;
        System.out.println(name + " created, instance is " + instanceNumber + ".");
    }

    // Redundant due to final class, but otherwise it would prevent subclasses from
    // overriding this method.
    public final int getInstanceNumber() {
        return instanceNumber;
    }
}
