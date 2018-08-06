package com.cjm.ms;

public class StartJoinThreads {
    public static void exec(Thread[] threads) {
        for(Thread thread: threads) {
            thread.start();
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch(InterruptedException e) {
                System.out.println("Thread error: " + e.getMessage());
            }
        }
    }
}
