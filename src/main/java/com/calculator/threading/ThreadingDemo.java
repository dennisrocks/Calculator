package com.calculator.threading;

/**
 * Main entry point that demonstrates various Java threading concepts.
 * Each demo runs sequentially to keep console output readable.
 */
public class ThreadingDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Java Threading Demo ===\n");

        runBasicThreadDemo();
        runSynchronizationDemo();
        runProducerConsumerDemo();
        runThreadPoolDemo();

        System.out.println("\n=== All demos complete ===");
    }

    private static void runBasicThreadDemo() throws InterruptedException {
        System.out.println("--- 1. Basic Threads & Runnable ---");
        BasicThreadDemo.run();
        System.out.println();
    }

    private static void runSynchronizationDemo() throws InterruptedException {
        System.out.println("--- 2. Synchronization ---");
        SynchronizationDemo.run();
        System.out.println();
    }

    private static void runProducerConsumerDemo() throws InterruptedException {
        System.out.println("--- 3. Producer-Consumer (BlockingQueue) ---");
        ProducerConsumerDemo.run();
        System.out.println();
    }

    private static void runThreadPoolDemo() throws InterruptedException {
        System.out.println("--- 4. Thread Pool (ExecutorService) ---");
        ThreadPoolDemo.run();
        System.out.println();
    }
}
