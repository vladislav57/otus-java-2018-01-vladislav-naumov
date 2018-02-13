package ru.otus.hw2;

import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int base_array_size = 100_000_000;

        int max_collection_size = 100_000;
        //нужно определить рост размера контейнера в зависимости от числа элементов
        //аналогично посмотрим на размер отдельного объекта
        for(int current_size = 1; current_size < max_collection_size; current_size *= 10 ) {
            long mem = usedMemory();
            System.out.println("Used memory before " + mem);

            Object[] array = new Object[current_size];

            long mem2 = usedMemory();
            double averageRef = 1.0d * (mem2 - mem)/current_size;
            System.out.println("Difference in memory " + (mem2 - mem));
            System.out.println("average memory per object reference " + averageRef);

            for (int i = 0; i < base_array_size; i++) {
                //array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]); //without String pool
                //array[i] = new ArrayList<>();
                //array[i] = new LinkedList<>();
                //array[i] = new HashSet<>();
                //array[i] = new TreeSet<>();
                //array[i] = new HashMap<>();
                //array[i] = new TreeMap<>();
            }

            long mem3 = usedMemory();
            double averageObj = 1.0d * (mem3 - mem2)/current_size;
            System.out.println("average memory per object " + averageObj);

            System.out.println("Created " + current_size + " objects.");

            for(int i = 0; i < current_size; i++) {
                array[i] = null;
            }
            array = null;

            Thread.sleep(1000); //wait for 1 sec
        }

    }

    public static long usedMemory() throws InterruptedException {
        System.gc();
        Thread.sleep(100);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

}
