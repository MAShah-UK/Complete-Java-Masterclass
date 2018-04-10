package com.cjm.ms;

public class CountdownThread extends Thread {
    private Countdown cd;
    private String colour;
    CallMethod method;

    public enum CallMethod {
        EXEC_LOCAL,
        EXEC_INSTANCE,
        EXEC_INSTANCE_SYNC,
        EXEC_INSTANCE_SYNC_BLOCK
    }

    @Override
    public void run() {
        switch (method) {
            case EXEC_LOCAL:
                cd.execLocal(colour);
                break;
            case EXEC_INSTANCE:
                cd.execInstance(colour);
                break;
            case EXEC_INSTANCE_SYNC:
                cd.execInstanceSync(colour);
                break;
            case EXEC_INSTANCE_SYNC_BLOCK:
                cd.execInstanceSyncBlock(colour);
                break;
        }
    }

    public CountdownThread(String name, Countdown cd, String colour, CallMethod method) {
        super(name);
        this.cd = cd;
        this.colour = colour;
        this.method = method;
    }
}

class Countdown {
    private int i;

    public void execLocal(String colour) {
        // Each thread has its own local copy of i since each thread maintains its own thread stack.
        for(int i = 10; i > 0; i--) {
            System.out.println(colour + Thread.currentThread().getName() + ": i = " + i);
        }
    }

    public void execInstance(String colour) {
        // Each thread will share instance variables - race condition.
        for(i = 10; i > 0; i--) {
            System.out.println(colour + Thread.currentThread().getName() + ": i = " + i);
        }
    }

    public synchronized void execInstanceSync(String colour) {
        // Each thread is synchronized. Only one will access the shared resource at a time.
        for(i = 10; i > 0; i--) {
            System.out.println(colour + Thread.currentThread().getName() + ": i = " + i);
        }
    }

    public void execInstanceSyncBlock(String colour) {
        // Each thread is synchronized. Only one will access the shared resource at a time.
        synchronized(this) {
            for(i = 10; i > 0; i--) {
                System.out.println(colour + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}
