package com.calculator.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates thread pools via {@link ExecutorService} and
 * {@link Future} for collecting results from concurrent tasks.
 */
public class ThreadPoolDemo {

    private static final int TASK_COUNT = 8;

    public static void run() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 1; i <= TASK_COUNT; i++) {
            final int taskId = i;
            Callable<String> task = () -> {
                String name = Thread.currentThread().getName();
                System.out.printf("  [%s] starting task %d%n", name, taskId);
                Thread.sleep(150);
                return String.format("Result of task %d (by %s)", taskId, name);
            };
            futures.add(pool.submit(task));
        }

        // Collect all results
        System.out.println("  Waiting for results...");
        for (Future<String> future : futures) {
            try {
                System.out.printf("  -> %s%n", future.get());
            } catch (ExecutionException e) {
                System.err.printf("  Task failed: %s%n", e.getCause().getMessage());
            }
        }

        pool.shutdown();
        boolean terminated = pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.printf("  Thread pool shut down cleanly: %s%n", terminated);
    }
}
