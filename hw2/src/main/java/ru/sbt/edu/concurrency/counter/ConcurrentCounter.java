package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.Semaphore;

public class ConcurrentCounter implements Counter {
    private int counter = 0;
    private final Semaphore semaphore = new Semaphore(1, true);

    @Override
    public void increment() {
        try {
            semaphore.acquire();
            int temp = counter;
            counter = temp + 1;
            semaphore.release();
        } catch (InterruptedException e) {}
    }

    @Override
    public long getValue() {
        return counter;
    }
}
