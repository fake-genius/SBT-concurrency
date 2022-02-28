package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.ThreadID;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class LockTwo implements ILock {
    private volatile int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        victim = i;
        System.out.println("id: " + i + " is trying to get in lock");
        while (victim == i) {}
        System.out.println("id: " + i + " is in lock");
    }


    @Override
    public void unlock() {
        System.out.println("id: " + ThreadID.get() + " is out of lock");
    }
}
