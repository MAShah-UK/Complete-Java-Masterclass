package com.cjm.ms;

import static com.cjm.ms.Colour.*;
import static com.cjm.ms.CountdownThread.CallMethod.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        createThreads();
        createCountdown();
    }

    // Practice creating threads using various methods.
    public static void createThreads() throws InterruptedException {
        System.out.println(ANSI_RESET + "BEGIN: createThreads");

        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        final AnotherThread anotherThread = new AnotherThread();
        anotherThread.setName("AnotherThread");
        // WRONG: Directly calls run() on the current thread since it's just a regular method call.
        anotherThread.run();
        // CORRECT: Creates a new thread which then calls AnotherClass.run() automatically.
        anotherThread.start();
        // anotherThread.interrupt();

        // May appear before "Hello from AnotherThread." since it takes a bit of time to start it.
        System.out.println(ANSI_PURPLE + "Hello from the main thread x 2.");

        // Can't call start() again, throws IllegalThreadStateException.
        //anotherThread.start();

        // Create an anonymous thread.
        new Thread() {
            @Override
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from an anonymous thread.");
            }
        }.start();

        // Create a thread from a Runnable subclass.
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        // Create an anonymous Runnable subclass thread.
        Thread anonRunnable = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ANSI_CYAN + "Hello from an anonymous Runnable subclass thread.");

                try {
                    // Waits for anotherThread to return from run().
                    anotherThread.join(); // Can specify a timeout to prevent this thread from freezing.
                    System.out.println("anonRunnable can continue since anotherThread returned.");
                } catch (InterruptedException e) {
                    System.out.println("anonRunnable was interrupted before joining anotherThread.");
                }
            }
        });
        anonRunnable.start();

        // Assumes main won't be interrupted, not good practice.
        // Could just join each thread instead.
        Thread.sleep(4000);
    }

    // Practice working with threads.
    public static void createCountdown() throws InterruptedException {
        System.out.println(ANSI_RESET + "\nBEGIN: createCountdown");

        // Demonstrates that the execution order is unpredictable/chaotic, but that the output is consistent
        // when using local variables.
        System.out.println(ANSI_RESET + "Using local variable:");
        Countdown cd = new Countdown();
        CountdownThread t1 = new CountdownThread("Thread 1", cd, ANSI_BLUE, EXEC_LOCAL);
        CountdownThread t2 = new CountdownThread("Thread 2", cd, ANSI_RED, EXEC_LOCAL);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Demonstrates that execution order AND output is unpredictable/chaotic when using shared instance variables.
        System.out.println(ANSI_RESET + "\nUsing instance variable: ");
        t1 = new CountdownThread("Thread 1", cd, ANSI_BLUE, EXEC_INSTANCE);
        t2 = new CountdownThread("Thread 2", cd, ANSI_RED, EXEC_INSTANCE);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Demonstrates that the execution order is unpredictable/chaotic, but that the output is consistent
        // when using shared instance variables, but limiting access via synchronization.
        System.out.println(ANSI_RESET + "\nUsing instance variable (synchronized method): ");
        t1 = new CountdownThread("Thread 1", cd, ANSI_BLUE, EXEC_INSTANCE_SYNC);
        t2 = new CountdownThread("Thread 2", cd, ANSI_RED, EXEC_INSTANCE_SYNC);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Same as before, but uses a synchronization block instead of a synchronized method.
        System.out.println(ANSI_RESET + "\nUsing instance variable (synchronization block): ");
        t1 = new CountdownThread("Thread 1", cd, ANSI_BLUE, EXEC_INSTANCE_SYNC_BLOCK);
        t2 = new CountdownThread("Thread 2", cd, ANSI_RED, EXEC_INSTANCE_SYNC_BLOCK);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
