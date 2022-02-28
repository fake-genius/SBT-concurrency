package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter{
    private int counter = 0;
    private final Lock lock = new ReentrantLock();;

    @Override
    public void increment() {
        lock.lock();
        try {
            int temp = counter;
            counter = temp + 1;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public long getValue() {
        return counter;
    }
}
