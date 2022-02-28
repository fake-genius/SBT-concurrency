package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.ThreadID;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class PetersonLock implements ILock {
    private final boolean[] flag = new boolean[2];
    private volatile int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while(flag[j] && victim == i) {}
    }

    @Override
    public void unlock() {
        int i = ThreadID.get();
        flag[i] = false;
    }

}
