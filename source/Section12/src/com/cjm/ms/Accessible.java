package com.cjm.ms;

// Exercise:
// In the following interface declaration, what is the visibility of:
// 1. The accessible interface? default (package-private).
// 2. The int variable SOME_CONSTANT? public (access anywhere).
// 3. methodA? Redundant public (access-anywhere).
// 4. methodB and methodC? public (access-anywhere).
// Hint: Think back to the lecture on the interfaces before answering.

// Since interface is default access, so are the fields/methods. The fields/methods
// can be access in another package if the interface is implemented by a public class
// in the same package which is then extended by a class in another package.
interface Accessible {       // default: Can only be accessed within com.cjm.ms package.
    int SOME_CONSTANT = 100; // Interface fields:  public static final.
    public void methodA();   // Interface methods: public by default.
    void methodB();          // Interface methods: public by default.
    boolean methodC();       // Interface methods: public by default.
}
