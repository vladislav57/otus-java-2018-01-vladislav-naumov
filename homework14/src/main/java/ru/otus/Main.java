package ru.otus;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int size_one = 100;
        int threads = 4;

        int[] random = IntStream.generate(() -> new Random().nextInt(100)).limit(size_one*threads).toArray();

        List<ThreadSorter> threadSorterList = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            threadSorterList.add(new ThreadSorter(Arrays.copyOfRange(random, i*size_one, (i+1)*size_one)));
        }

        List<Thread> threadList = new ArrayList<>();

        for (ThreadSorter threadSorter : threadSorterList) {
            Thread thread = new Thread(threadSorter);
            threadList.add(thread);
            thread.run();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        MergeSorter mergeSorter = new MergeSorter();

        for(ThreadSorter threadSorter : threadSorterList) {
            mergeSorter.add(threadSorter.getToSort());
        }

        Arrays.sort(random);

        Assert.assertArrayEquals(random, mergeSorter.getTotal());

    }
}
