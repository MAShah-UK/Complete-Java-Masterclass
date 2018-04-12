package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.cjm.ms.Colour.*;
import static com.cjm.ms.CountdownThread.CallMethod.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //createThreads();
        //createCountdown();
        //createReaderWriter();
        //createProducerConsumer();
        //createExecSrv();
        //createBlockingQueue();
        //createDeadlock1();
        //createDeadlock2();
        createStarvation();
        createFairLock();
        createLivelock();
    }

    // Practice creating threads using various methods.
    public static void createThreads() throws InterruptedException {
        System.out.println(ANSI_RESET + "BEGIN: createThreads");

        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        // Must be marked final since its being used by an inner class.
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
        // Should join each thread instead.
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

    // Practice working with threads to implement producer-consumer architecture.
    public static void createReaderWriter() {
        System.out.println(ANSI_RESET + "\nBEGIN: createReaderWriter");

        Message message = new Message();
        new Thread(new Writer(message)).start();
        new Thread(new Reader(message)).start();
    }

    // Practice working with ReentrantLock to counter the disadvantages of using synchronized code.
    public static void createProducerConsumer() throws InterruptedException {
        System.out.println(ANSI_RESET + "\nBEGIN createProducerConsumer");

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        ALProducer producer = new ALProducer(buffer, bufferLock, ANSI_GREEN);
        ALConsumer consumer1 = new ALConsumer(buffer, bufferLock, ANSI_BLUE);
        ALConsumer consumer2 = new ALConsumer(buffer, bufferLock, ANSI_CYAN);

        Thread t1 = new Thread(producer); t1.start();
        Thread t2 = new Thread(consumer1); t2.start();
        Thread t3 = new Thread(consumer2); t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    public static void joinExecutorService(ExecutorService srv) {
        while(true) {
            try {
                if (srv.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
                    break;
                }
            } catch(InterruptedException e) {

            }
        }
    }

    // Practice working with thread pools / executor service.
    public static void createExecSrv() {
        System.out.println(ANSI_RESET + "\nBEGIN createExecSrv");

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService srv = Executors.newFixedThreadPool(4);
        ALProducer producer = new ALProducer(buffer, bufferLock, ANSI_GREEN);
        ALConsumer consumer1 = new ALConsumer(buffer, bufferLock, ANSI_BLUE);
        ALConsumer consumer2 = new ALConsumer(buffer, bufferLock, ANSI_CYAN);

        srv.execute(producer);
        srv.execute(consumer1);
        srv.execute(consumer2);

        // Will wait for other threads to complete if nThreads is <= 3.
        Future<String> future = srv.submit(new Callable<String>() {
            @Override
            public String call() {
                return ANSI_GREEN + "This is the callable result.";
            }
        });

        try {
            // get() blocks the calling thread until a result is returned.
            System.out.println(future.get());
        } catch(ExecutionException e) {
            System.out.println("Something went wrong.");
        } catch(InterruptedException e) {
            System.out.println("Thread running the task was interrupted.");
        }

        // Waits for threads to finish executing and doesn't accept new threads.
        srv.shutdown();
        joinExecutorService(srv);
    }

    // Practice working with ArrayBlockingQueue.
    public static void createBlockingQueue() {
        System.out.println(ANSI_RESET + "\nBEGIN: createBlockingQueue");

        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);

        ExecutorService srv = Executors.newFixedThreadPool(4);
        ABQProducer producer = new ABQProducer(buffer, ANSI_GREEN);
        ABQConsumer consumer1 = new ABQConsumer(buffer, ANSI_BLUE);
        ABQConsumer consumer2 = new ABQConsumer(buffer, ANSI_CYAN);

        srv.execute(producer);
        srv.execute(consumer1);
        srv.execute(consumer2);

        Future<String> future = srv.submit(new Callable<String>() {
            @Override
            public String call() {
                return ANSI_GREEN + "This is the callable result.";
            }
        });

        try {
            System.out.println(future.get());
        } catch(ExecutionException e) {
            System.out.println("Something went wrong.");
        } catch(InterruptedException e) {
            System.out.println("Thread running the task was interrupted.");
        }

        srv.shutdown();
        joinExecutorService(srv);
    }

    // Practice avoiding deadlocks.
    public static void createDeadlock1() throws InterruptedException {
        System.out.println(ANSI_RESET + "\nBEGIN createDeadlock");

        Thread t1 = new Deadlock.Thread1();
        t1.start();
        // Will result in two threads that each require an unobtainable lock - deadlock.
        // To prevent deadlocks, use minimal locks, and make sure they're used in the same order,
        // e.g. lock 1, then lock 2, etc.
        // Thread t2 = new Deadlock.UnsafeThread2();
        // t2.start();
        Thread t2 = new Deadlock.SafeThread2();
        t2.start();

        t1.join();
        t2.join();
    }

    // Practice avoiding deadlocks.
    public static void createDeadlock2() {
        System.out.println(ANSI_RESET + "\nBEGIN: createDeadlock2");

        class PolitePerson {
            private final String name;
            public PolitePerson(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }
            public synchronized void sayHello(PolitePerson otherP) {
                System.out.format("%s: %s" + " has said hello to me.%n", name, otherP.name);
                otherP.sayHelloBack(this);
            }
            public synchronized void sayHelloBack(PolitePerson otherP) {
                System.out.format("%s: %s" + " has said hello back to me.%n", name, otherP.name);
            }
        }
        // Marked final since they are being used by inner classes.
        final PolitePerson jane = new PolitePerson("Jane");
        final PolitePerson john = new PolitePerson("John");

        // This results in a deadlock since each thread will be blocked by the other.
        // This is because each thread is holding a lock the other needs.
        // This could have been avoided if the locks weren't obtained in opposite order.
        new Thread(new Runnable() {
            @Override
            public void run() {
                jane.sayHello(john);
            }
        }).start();

          // Commented out to prevent deadlock, it's still here as an example.
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                john.sayHello(jane);
//            }
//        }).start();
    }

    // Practice working with thread priority.
    public static void createStarvation() throws InterruptedException {
        System.out.println(ANSI_RESET + "\nBEGIN: createStarvation");

        Object lock = new Object();

        class Worker implements Runnable {
            private int runCount = 1;
            private String threadColor;
            public Worker(String threadColor) {
                this.threadColor = threadColor;
            }
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    synchronized(lock) {
                        System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                        // Imagine this contains critical section of code.
                    }
                }
            }
        }

        // All threads competing for one lock, called lock.
        Thread[] t = new Thread[5];
        t[0] = new Thread(new Worker(ANSI_RED), "Priority 10");
        t[1] = new Thread(new Worker(ANSI_BLUE), "Priority 8");
        t[2] = new Thread(new Worker(ANSI_GREEN), "Priority 6");
        t[3] = new Thread(new Worker(ANSI_CYAN), "Priority 4");
        t[4] = new Thread(new Worker(ANSI_PURPLE), "Priority 2");

        // Sets priority 2 to 10 for threads 1 to 5.
        // The priority is just a suggestion to the OS.
        // There's no guarantee it'll run more often.
        for(int i = 0; i < 5; i++) {
            t[i].setPriority((i+1)*2);
        }

        for(Thread thread: t) {
            thread.start();
        }

        for(Thread thread: t) {
            thread.join();
        }
    }

    // Practice working with fair reentrant lock.
    public static void createFairLock() throws InterruptedException {
        System.out.println(ANSI_RESET + "\nBEGIN: createFairLock");

        // Sets the lock to be fair and provide the lock of a first come first serve basis.
        // - tryLock() will ignore the fair argument.
        // - Reduced performance for large number of threads. Class needs to decide which thread gets lock.
        ReentrantLock lock = new ReentrantLock(true);

        class Worker implements Runnable {
            private int runCount = 1;
            private String threadColor;
            public Worker(String threadColor) {
                this.threadColor = threadColor;
            }
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    lock.lock();
                    try {
                        System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                        // Imagine this contains critical section of code.
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        // All threads competing for one lock, called lock.
        Thread[] t = new Thread[5];
        t[0] = new Thread(new Worker(ANSI_RED), "Thread 1");
        t[1] = new Thread(new Worker(ANSI_BLUE), "Thread 2");
        t[2] = new Thread(new Worker(ANSI_GREEN), "Thread 3");
        t[3] = new Thread(new Worker(ANSI_CYAN), "Thread 4");
        t[4] = new Thread(new Worker(ANSI_PURPLE), "Thread 5");

        for(Thread thread: t) {
            thread.start();
        }

        for(Thread thread: t) {
            thread.join();
        }
    }

    // Practice avoiding live locks.
    public static void createLivelock() throws InterruptedException {
        System.out.println(ANSI_RESET + "\nBEGIN: createLiveLock");

        final Worker worker1 = new Worker("Worker 1", true);
        final Worker worker2 = new Worker("Worker 2", true);
        final SharedResource sharedResource = new SharedResource(worker1);

//        // Threads will get stuck in a livelock and both will keep passing the resource to each other
//        // rather than completing. Commented out to allow program to complete.
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                worker1.work(sharedResource, worker2);
//            }
//        });
//        t1.start();
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                worker2.work(sharedResource, worker1);
//            }
//        });
//        t2.start();
//
//        t1.join();
//        t2.join();
    }
}
