package ru.otus.hw2;

import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int max_array_size = 10_000_000;

        //нужно определить рост размера контейнера в зависимости от числа элементов
        //аналогично посмотрим на размер отдельного объекта
        for(int current_size = 1; current_size < max_array_size; current_size *= 10 ) {
            System.gc();
            Thread.sleep(100);
            Runtime runtime = Runtime.getRuntime();
            long mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println(mem);

            Object[] array = new Object[current_size];

            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println(mem2 - mem);
            System.out.println((mem2 - mem)/current_size);

            for (int i = 0; i < current_size; i++) {
                array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]); //without String pool
                //array[i] = new MyClass();
            }

            long mem3 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println((mem3 - mem2)/current_size);

            System.out.println("Created " + current_size + " objects.");

            Thread.sleep(1000); //wait for 1 sec
        }

    }

}
