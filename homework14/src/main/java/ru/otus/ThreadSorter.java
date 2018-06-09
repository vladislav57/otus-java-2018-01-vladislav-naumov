package ru.otus;

import java.util.Arrays;

public class ThreadSorter implements Runnable {

    private int[] toSort;

    public ThreadSorter(int[] toSort) {
        this.toSort = toSort;
    }

    public int[] getToSort() {
        return toSort;
    }

    @Override
    public void run() {
        Arrays.sort(toSort);
    }
}
