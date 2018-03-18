package com.cjm.ms;

public class ScopeCheck {
    // Access modifiers restrict scope of field.
    public int publicVar = 0;
    private int privateVar = 1;

    public ScopeCheck() {
        System.out.println("ScopeCheck created: publicVar = " + publicVar + ", privateVar = " + privateVar);
    }

    public int getPrivateVar() {
        return privateVar;
    }

    public void timesTwo() {
        // If this line is removed the for loop will use 'ScopeCheck.privateVar'.
        int privateVar = 2; // Shadows ScopeCheck.privateVar.
        for (int i = 0; i < 10; i++) {
            // To use ScopeCheck.privateVar, type this.privateVar.
            System.out.println(i + " times two is " + i * privateVar);
        }
        // Error because i is not defined in current code block or enclosing code blocks.
        // System.out.println("Value of i is now " + i);
    }

    public class InnerClass {
        public int privateVar = 3;

        public InnerClass() {
            System.out.println("InnerClass created, privateVar is " + privateVar + ".");
        }

        public void timesTwo() {
            // To call ScopeCheck.timesTwo(): ScopeCheck.this.timesTwo().
            for (int i = 0; i < 10; i++) {
                // InnerClass.privateVar: this.privateVar, ScopeCheck.privateVar: ScopeCheck.this.privateVar.
                System.out.println(i + " times two is " + i * privateVar);
            }
        }
    }
}
