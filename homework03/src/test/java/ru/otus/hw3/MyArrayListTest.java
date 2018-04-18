package ru.otus.hw3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class MyArrayListTest {

    MyArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void canAddAndRetrieveObject() {
        list.add(5);
        list.add(4);
        Integer res1 = list.get(0);
        Integer res2 = list.get(1);
        Assert.assertEquals(res1, new Integer(5));
        Assert.assertEquals(res2, new Integer(4));
    }

    @Test
    public void canRemoveObject() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(0);
        list.remove(1);
        list.remove(2);
        Integer res1 = list.get(0);
        Integer res2 = list.get(1);
        Assert.assertEquals(res1, new Integer(2));
        Assert.assertEquals(res2, new Integer(4));
    }

    @Test
    public void canSort() {
        list.add(7);
        list.add(2);
        list.add(8);
        list.add(4);
        list.add(0);
        Collections.sort(list);
        Integer res1 = list.get(0);
        Integer res2 = list.get(1);
        Integer res3 = list.get(2);
        Integer res4 = list.get(3);
        Integer res5 = list.get(4);
        Assert.assertEquals(res1, new Integer(0));
        Assert.assertEquals(res2, new Integer(2));
        Assert.assertEquals(res3, new Integer(4));
        Assert.assertEquals(res4, new Integer(7));
        Assert.assertEquals(res5, new Integer(8));
    }

    @Test
    public void canCopy() {
        list.add(7);
        list.add(2);
        list.add(8);
        MyArrayList<Integer> copy = new MyArrayList<>();
        copy.add(1);
        copy.add(1);
        copy.add(1);
        Collections.copy(copy, list);
        Integer res1 = copy.get(0);
        Integer res2 = copy.get(1);
        Integer res3 = copy.get(2);
        Assert.assertEquals(res1, new Integer(7));
        Assert.assertEquals(res2, new Integer(2));
        Assert.assertEquals(res3, new Integer(8));
    }

    @Test()
    public void canAddAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        Collections.addAll(list, 5, 7);
        Integer res1 = list.get(0);
        Integer res2 = list.get(1);
        Integer res3 = list.get(2);
        Integer res4 = list.get(3);
        Integer res5 = list.get(4);
        Assert.assertEquals(res1, new Integer(1));
        Assert.assertEquals(res2, new Integer(2));
        Assert.assertEquals(res3, new Integer(3));
        Assert.assertEquals(res4, new Integer(5));
        Assert.assertEquals(res5, new Integer(7));
    }
}
