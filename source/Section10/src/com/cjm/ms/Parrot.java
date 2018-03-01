package com.cjm.ms;

public class Parrot extends Bird {
    public Parrot(String name) {
        super(name);
    }

    /* Eat and breath can be overridden, but it's optional since Bird
       has already done so. */
}
