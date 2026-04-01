package com.calculator.threading;

/**
 * Demonstrates the difference between unsynchronized and synchronized
 * access to shared mutable state.
 */
public class SynchronizationDemo {

    private static final int INCREMENTS = 100_000;

    public static void run() throws InterruptedException {
        // Unsynchronized counter — result is often incorrect
        UnsafeCounter unsafe = new UnsafeCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                unsafe.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                unsafe.increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.printf("  Unsafe counter (expected %d): %d%n",
                2 * INCREMENTS, unsafe.getCount());

        // Synchronized counter — always correct
        SafeCounter safe = new SafeCounter();
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                safe.increment();
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                safe.increment();
            }
        });
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.printf("  Safe counter   (expected %d): %d%n",
                2 * INCREMENTS, safe.getCount());
    }

    /** Counter without synchronization — exhibits race conditions. */
    private static class UnsafeCounter {
        private int count;

        void increment() {
            count++;
        }

        int getCount() {
            return count;
        }
    }

    /** Counter using {@code synchronized} to prevent race conditions. */
    private static class SafeCounter {
        private int count;

        synchronized void increment() {
            count++;
        }

        synchronized int getCount() {
            return count;
        }
    }
}
