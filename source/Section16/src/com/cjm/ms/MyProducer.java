package com.cjm.ms;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable {
    private List<String> buffer;
    private ReentrantLock bufferLock;
    private String color;

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        for(String num: nums) {
            System.out.println(color + "Adding: " + num);
            bufferLock.lock();
            try {
                buffer.add(num);
            } finally {
                bufferLock.unlock();
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch(InterruptedException e) {
                System.out.println("Producer was interrupted.");
            }
        }
        System.out.println(color + "Adding EOF and exiting.");
        bufferLock.lock();
        try {
            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }
    }

    public MyProducer(List<String> buffer, ReentrantLock bufferLock, String color) {
        this.buffer = buffer;
        this.bufferLock = bufferLock;
        this.color = color;
    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    @Override
    public void run() {
        while(true) {
//            // Poor code: bufferLock.unlock() is everywhere.
//            bufferLock.lock();
//            if(buffer.isEmpty()) {
//                bufferLock.unlock();
//                continue;
//            }
//            if(buffer.get(0).equals("EOF")) {
//                System.out.println(color + "Exiting");
//                bufferLock.unlock();
//                break;
//            } else {
//                System.out.println(color + "Removed " + buffer.remove(0));
//            }
//            bufferLock.unlock();

            int counter = 0;
            while(true) {
                if(bufferLock.tryLock()) {
                    try {
                        if(buffer.isEmpty()) {
                            continue;
                        }
                        System.out.println(color + "The counter = " + counter);
                        counter = 0;
                        if(buffer.get(0).equals("EOF")) {
                            System.out.println(color + "Exiting");
                            break;
                        } else {
                            System.out.println(color + "Removed " + buffer.remove(0));
                        }
                    } finally {
                        bufferLock.unlock();
                    }
                } else {
                    // Can do something else until lock is available.
                    counter++;
                }
            }
        }
    }

    public MyConsumer(List<String> buffer, ReentrantLock bufferLock, String color) {
        this.buffer = buffer;
        this.bufferLock = bufferLock;
        this.color = color;
    }
}
