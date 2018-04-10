package com.cjm.ms;

import static com.cjm.ms.Colour.ANSI_RED;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable.");
    }
}
