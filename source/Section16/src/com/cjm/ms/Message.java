package com.cjm.ms;

import java.util.Random;

public class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while(empty) {
            try {
                // wait() is called within a loop as an interruption may wake it up prematurely.
                wait();
            } catch(InterruptedException e) {

            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while(!empty) {
            try {
                wait();
            } catch(InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Writer implements Runnable {
    private Message message;

    @Override
    public void run() {
        String messages[] = {
                "Humpty Dumpty sat on a wall",
                "Humpy Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };
        Random random = new Random();
        for(int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch(InterruptedException e) {

            }
        }
        message.write("Finished");
    }

    public Writer(Message message) {
        this.message = message;
    }
}

class Reader implements Runnable {
    private Message message;

    @Override
    public void run() {
        Random random = new Random();
        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch(InterruptedException e) {

            }
        }
    }

    public Reader(Message message) {
        this.message = message;
    }
}
