package ru.sbt.edu.concurrency.counter;


import ru.sbt.edu.concurrency.locks.ILock;

public class ILockCounter implements Counter {
    private final ILock lock;
    private long count;

    public ILockCounter(ILock lock) {
        this.lock = lock;
        this.count = 0;
    }

    @Override
    public void increment() {
        lock.lock();
        try {
            long temp = count;
            count = temp + 1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}
