package com.cjm.ms;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

// The classes are designed to work with an ArrayBlockingQueue.
class ABQProducerConsumer {
}

class ABQProducer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        for(String num: nums) {
            try {
                System.out.println(color + "Adding: " + num);
                buffer.put(num);
                Thread.sleep(random.nextInt(1000));
            } catch(InterruptedException e) {
                System.out.println("Producer was interrupted.");
            }
        }
        System.out.println(color + "Adding EOF and exiting.");

        try {
            buffer.put("EOF");
        } catch(InterruptedException e) {

        }
    }

    public ABQProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }
}

class ABQConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    @Override
    public void run() {
        while(true) {
            // Required because the other consumer thread may read data and
            // make the buffer empty before we can read it.
            synchronized(buffer) {
                try {
                    if(buffer.isEmpty()) {
                        continue;
                    }
                    if(buffer.peek().equals("EOF")) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch(InterruptedException e) {

                }
            }
        }
    }

    public ABQConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }
}
