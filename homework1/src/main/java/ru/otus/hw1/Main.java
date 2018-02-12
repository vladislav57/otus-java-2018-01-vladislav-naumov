package ru.otus.hw1;

import com.google.common.base.Joiner;

public class Main {
    public static void main(String[] args) {
        testGuavaLibrary(args);
    }

    private static void testGuavaLibrary(String[] args) {
        if(args.length == 0) {
            System.out.println("Please, enter some arguments");
        } else {
            System.out.println("You entered: " + Joiner.on("; ").join(args));
        }
    }
}
