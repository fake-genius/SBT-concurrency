package ru.sbt.edu.concurrency.locks;

import org.junit.jupiter.api.Test;
import ru.sbt.edu.concurrency.counter.*;
import ru.sbt.edu.concurrency.locks.theory.LockOne;
import ru.sbt.edu.concurrency.locks.theory.LockTwo;
import ru.sbt.edu.concurrency.locks.theory.LockZero;
import ru.sbt.edu.concurrency.locks.theory.PetersonLock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

import static org.junit.jupiter.api.Assertions.*;

public class ILockTest {
    @Test
    public void testTheoryLock()  {
        ILock lock0 = new LockZero();
        ILock lock1 = new LockOne();
        ILock lock2 = new LockTwo();
        ILock lockP = new PetersonLock();

        Counter counter0 = new ILockCounter(lock0);
        Counter counter1 = new ILockCounter(lock1);
        Counter counter2 = new ILockCounter(lock2);
        Counter counterP = new ILockCounter(lockP);
        //try: 1, 2, 10, 100, 1000
        int iters = 10;
        System.out.println("Testing TheoryLock, iters = " + iters);
        //testCounter(counter0, iters);
        //testCounter(counter1, iters);
        testCounter(counter2, iters);
        //testCounter(counterP, iters);
        System.out.println("Ended testing TheoryLock\n");
    }

    @Test
    public void testMutexCounter() {
        Counter counter = new MutexCounter();
        testCounter(counter, 100000);
    }

    @Test
    public void testLockCounter() {
        Counter counter = new LockCounter();
        testCounter(counter, 10000);
    }

    @Test
    public void testNaiveCounter()  {
        Counter counter = new SeqCounter();
        int iters = 1000;
        System.out.println("Testing SeqCounter, iters = " + iters);
        testCounter(counter, iters);
        System.out.println("Ended testing SeqCounter\n");
    }

    private void testCounter(Counter counter, int iters) {
        Runnable increment = () -> {
            System.out.println(TwoThreadIds.me());
            for (int i = 0; i < iters; i++) {
                counter.increment();
            }
        };

        Thread t0 = new Thread(increment);
        Thread t1 = new Thread(increment);
        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long count = counter.getValue();
        System.out.println("expected: " + iters * 2);
        System.out.println("actual:   " + count);

        boolean flag = true;
        try {
            assertEquals(iters * 2, count);
            flag = false;
        } finally {
            if (flag) {
                System.out.println("Oops! Unexpected Behaviour!");
            }
        }
    }
}