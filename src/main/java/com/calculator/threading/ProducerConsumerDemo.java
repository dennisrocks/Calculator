package com.calculator.threading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Classic producer-consumer pattern using a {@link BlockingQueue}.
 * One producer thread generates items; two consumer threads process them.
 */
public class ProducerConsumerDemo {

    private static final int ITEM_COUNT = 10;
    private static final String POISON_PILL = "DONE";

    public static void run() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(4);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= ITEM_COUNT; i++) {
                    String item = "item-" + i;
                    queue.put(item);
                    System.out.printf("  [Producer]   put %s%n", item);
                    Thread.sleep(50);
                }
                // Signal each consumer to stop
                queue.put(POISON_PILL);
                queue.put(POISON_PILL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer1 = createConsumer(queue, "Consumer-1");
        Thread consumer2 = createConsumer(queue, "Consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();

        producer.join();
        consumer1.join();
        consumer2.join();

        System.out.println("  Producer-Consumer finished.");
    }

    private static Thread createConsumer(BlockingQueue<String> queue, String name) {
        return new Thread(() -> {
            try {
                while (true) {
                    String item = queue.take();
                    if (POISON_PILL.equals(item)) {
                        break;
                    }
                    System.out.printf("  [%s] processed %s%n", name, item);
                    Thread.sleep(120);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, name);
    }
}
