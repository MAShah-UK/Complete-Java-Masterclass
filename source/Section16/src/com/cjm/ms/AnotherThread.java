package com.cjm.ms;

import static com.cjm.ms.Colour.ANSI_BLUE;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        // Don't call getName() directly since it will just return the name given to this thread,
        // not the name of the actual thread running the code.
        String name = currentThread().getName();
        System.out.println(ANSI_BLUE + "Hello from " + name + ".");

        // Let the main thread exit and continue outputting to console.
        if (currentThread().getName().equals("main")) {
            return;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(ANSI_BLUE + name + " was prematurely woken up.");
            return; // Sine it doesn't make sense to output the next message if it's already woken up.
        }

        // Not guaranteed to run after three seconds since another thread might wake this thread up,
        // and because the timing is operating system / JVM dependant.
        System.out.println(ANSI_BLUE + "Three seconds have passed and " + name + " is awake.");
    }
}
