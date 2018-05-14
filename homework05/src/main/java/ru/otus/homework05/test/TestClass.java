package ru.otus.homework05.test;

import ru.otus.homework05.annotations.After;
import ru.otus.homework05.annotations.Before;
import ru.otus.homework05.annotations.Test;

public class TestClass {

    @Before
    public void before() {
        System.out.println("Before");
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @Test
    public void testMethod1() {
        System.out.println("Test method 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test method 2");
    }
}
