# Calculator — Java Threading Demo

A collection of Java threading examples covering core concurrency concepts.

## Demos Included

| # | Class | Concept |
|---|-------|---------|
| 1 | `BasicThreadDemo` | Creating threads via `Thread`, `Runnable`, and lambdas |
| 2 | `SynchronizationDemo` | Race conditions and `synchronized` keyword |
| 3 | `ProducerConsumerDemo` | Producer-consumer with `BlockingQueue` |
| 4 | `ThreadPoolDemo` | `ExecutorService`, `Callable`, and `Future` |

## Requirements

- Java 8 or later

## Build & Run

```bash
# Compile
javac -d out src/main/java/com/calculator/threading/*.java

# Run
java -cp out com.calculator.threading.ThreadingDemo
```
