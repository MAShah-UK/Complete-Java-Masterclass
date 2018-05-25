package com.cjm.ms.concurrency;

import static com.cjm.ms.concurrency.Colour.ANSI_RED;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable.");
    }
}
