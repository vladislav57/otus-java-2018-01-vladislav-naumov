package ru.otus.hw2;

import java.lang.management.ManagementFactory;

public class Empty {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int base_array_size = 10_000_000;

        Object[] array = new Object[base_array_size];

        long mem = usedMemory();

        for (int i = 0; i < base_array_size; i++) {
            array[i] = new Object();
            //array[i] = new String(""); //String pool
            //array[i] = new String(new char[0]); //without String pool
            //array[i] = new ArrayList<>();
            //array[i] = new LinkedList<>();
            //array[i] = new HashSet<>();
            //array[i] = new TreeSet<>();
            //array[i] = new HashMap<>();
            //array[i] = new TreeMap<>();
        }

        long mem2 = usedMemory();
        double averageObj = 1.0d * (mem2 - mem)/base_array_size;
        System.out.println("average memory per object " + averageObj);

        System.out.println("Created " + base_array_size + " objects.");

        for(int i = 0; i < base_array_size; i++) {
            array[i] = null;
        }
        array = null;

        Thread.sleep(1000); //wait for 1 sec

    }

    public static long usedMemory() throws InterruptedException {
        System.gc();
        Thread.sleep(100);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

}
