package ru.otus.homework04;

import java.lang.management.ManagementFactory;
import java.util.LinkedList;
import java.util.List;

public class GC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        Thread.sleep(30000);
        System.out.println("Wait for jconsole ended");
        List<Object> list = new LinkedList<>();
        while(true) {
            for(int i=0; i<1000; i++) {
                list.add(new Object());
            }
            int index = list.size();
            for(int i=0; i<500; i++) {
                list.remove(index-1);
                index--;
            }
            Thread.sleep(10);
        }
    }
}
