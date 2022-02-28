package ru.sbt.edu.concurrency.counter;

public class SeqCounter implements Counter {
    private long value;
    @Override
    public void increment() {
        value++;
    }

    @Override
    public long getValue() {
        return value;
    }
}
