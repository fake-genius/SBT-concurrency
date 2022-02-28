package ru.sbt.edu.concurrency.util;

public final class TwoThreadIds {
    public static int me() {
        return ThreadID.get();
    }

    public static int not(int me) {
        return me == 0 ? 1 : 0;
    }
}
