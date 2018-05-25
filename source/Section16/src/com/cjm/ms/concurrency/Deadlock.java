package com.cjm.ms.concurrency;

public class Deadlock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized(lock1) {
                System.out.println("Thread 1: Has lock 1.");
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {

                }
                System.out.println("Thread 1: Waiting for lock 2.");
                synchronized(lock2) {
                    System.out.println("Thread 1: Has lock 1 and lock 2.");
                }
                System.out.println("Thread 1: Released lock 2.");
            }
            System.out.println("Thread 1: Released lock 1. Exiting...");
        }
    }

    // POOR CODE: Locks not being used in the same order, prone to deadlock.
    public static class UnsafeThread2 extends Thread {
        @Override
        public void run() {
            synchronized(lock2) {
                System.out.println("Thread 2: Has lock 2.");
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {

                }
                System.out.println("Thread 2: Waiting for lock 1.");
                synchronized(lock1) {
                    System.out.println("Thread 2: Has lock 1 and lock 2.");
                }
                System.out.println("Thread 2: Released lock 1.");
            }
            System.out.println("Thread 2: Released lock 2. Exiting...");
        }
    }

    // Good code: Locks being used in order, can't deadlock.
    public static class SafeThread2 extends Thread {
        @Override
        public void run() {
            synchronized(lock1) {
                System.out.println("Thread 2: Has lock 1.");
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {

                }
                System.out.println("Thread 2: Waiting for lock 2.");
                synchronized(lock2) {
                    System.out.println("Thread 2: Has lock 1 and lock 2.");
                }
                System.out.println("Thread 2: Released lock 2.");
            }
            System.out.println("Thread 2: Released lock 1. Exiting...");
        }
    }
}
