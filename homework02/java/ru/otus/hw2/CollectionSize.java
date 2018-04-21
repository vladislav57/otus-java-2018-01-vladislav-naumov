package ru.otus.hw2;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class CollectionSize {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int collections_number = 1;

        int max_collection_size = 10_000_000;

        Object o = new Object();
        Object[] objectpool = new Object[max_collection_size];
        for(int i=0; i<max_collection_size; i++) {
            objectpool[i] = new Object();;
        }
        String[] stringpool = new String[max_collection_size];
        for(int i=0; i<max_collection_size; i++) {
            stringpool[i] = new String(new char[0]);
        }

        for(int collection_size = 10; collection_size < max_collection_size; collection_size = (int) (1.5 * collection_size) ) {

            Object[] array = new Object[collections_number];

            long mem = usedMemory();

            for (int i = 0; i < collections_number; i++) {

                ArrayList e = new ArrayList<>();
                array[i] = e;
                for(int j=0; j<collection_size; j++) {
                    e.add(objectpool[j]);
                }

                /*LinkedList e = new LinkedList<>();
                array[i] = e;
                for(int j=0; j<collection_size; j++) {
                    e.add(objectpool[j]);
                }*/

                /*HashSet e = new HashSet<>();
                array[i] = e;
                for(int j=0; j<collection_size; j++) {
                    e.add(objectpool[j]);
                }*/

                /*TreeSet e = new TreeSet();
                array[i] = e;
                for(int j=0; j<collection_size; j++) {
                    e.add(j);
                }*/

                /*HashMap e = new HashMap<>(collection_size);
                array[i] = e;
                for(int j=0; j<collection_size; j++) {
                    e.put(objectpool[j], objectpool[j]);
                }*/

                /*TreeMap e = new TreeMap();
                array[i] = e;
                for(int j=0; j<collection_size; j++) {
                    e.put(j, objectpool[j]);
                }*/



            }

            long mem2 = usedMemory();
            double collectionSizeInMemory = 1.0d * (mem2 - mem)/collections_number;
            System.out.println("collection of size " + collection_size + " takes memory: " + collectionSizeInMemory);
            double growthRate = collectionSizeInMemory / collection_size;
            System.out.println("Growth koefficient " + growthRate);

            for(int i = 0; i < collections_number; i++) {
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
