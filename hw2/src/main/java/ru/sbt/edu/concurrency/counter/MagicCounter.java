package java.ru.sbt.edu.concurrency.counter;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.locks.theory.FilterLock;

public class MagicCounter implements Counter {
    private final ILock lock;
    private long count;

    public MagicCounter(int n) {
        this.lock =  new FilterLock(n);
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