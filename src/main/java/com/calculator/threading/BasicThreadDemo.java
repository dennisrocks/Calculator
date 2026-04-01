package com.calculator.threading;

/**
 * Demonstrates creating threads by extending {@link Thread}
 * and by implementing {@link Runnable}.
 */
public class BasicThreadDemo {

    public static void run() throws InterruptedException {
        // 1a. Extending Thread
        Thread counter = new CounterThread("Counter-A", 5);
        counter.start();

        // 1b. Implementing Runnable
        Thread runner = new Thread(new CounterRunnable("Counter-B", 5));
        runner.start();

        // 1c. Lambda-based Runnable
        Thread lambda = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.printf("  [Lambda]   i=%d%n", i);
                sleep(80);
            }
        });
        lambda.start();

        // Wait for all threads to finish
        counter.join();
        runner.join();
        lambda.join();

        System.out.println("  All basic threads finished.");
    }

    /** Thread subclass that counts up to {@code limit}. */
    private static class CounterThread extends Thread {
        private final int limit;

        CounterThread(String name, int limit) {
            super(name);
            this.limit = limit;
        }

        @Override
        public void run() {
            for (int i = 1; i <= limit; i++) {
                System.out.printf("  [%s] i=%d%n", getName(), i);
                BasicThreadDemo.sleep(100);
            }
        }
    }

    /** Runnable implementation that counts up to {@code limit}. */
    private static class CounterRunnable implements Runnable {
        private final String name;
        private final int limit;

        CounterRunnable(String name, int limit) {
            this.name = name;
            this.limit = limit;
        }

        @Override
        public void run() {
            for (int i = 1; i <= limit; i++) {
                System.out.printf("  [%s] i=%d%n", name, i);
                BasicThreadDemo.sleep(90);
            }
        }
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
