package ru.sbt.edu.concurrency.counter;

public class MutexCounter implements Counter{
    private int counter = 0;
    private final Object lock = new Object();

    @Override
    public void increment() {
        synchronized(lock) {
            int temp = counter;
            counter = temp + 1;
        }
    }

    @Override
    public long getValue() {
        synchronized(lock) {
            return counter;
        }
    }
}
