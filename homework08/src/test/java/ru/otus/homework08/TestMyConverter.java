package ru.otus.homework08;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.homework08.converter.MyConverter;
import ru.otus.homework08.model.*;

import java.util.ArrayList;
import java.util.HashSet;

public class TestMyConverter {

    @Test
    public void testInteger() throws ClassNotFoundException, IllegalAccessException {
        Integer object = new Integer(5);
        compareGoogleGsonStringAndMyConverterString(object);
    }

    @Test
    public void testFloat() throws ClassNotFoundException, IllegalAccessException {
        Float object = new Float(1.1f);
        compareGoogleGsonStringAndMyConverterString(object);
    }

    @Test
    public void testInt() throws ClassNotFoundException, IllegalAccessException {
        int number = 5;
        compareGoogleGsonStringAndMyConverterString(number);
    }

    @Test
    public void testArrayInteger() throws ClassNotFoundException, IllegalAccessException {
        Integer[] array = new Integer[10];
        for(int i = 0; i<10; i++)
            array[i] = new Integer(1);
        compareGoogleGsonStringAndMyConverterString(array);
    }

    @Test
    public void testArrayInt() throws ClassNotFoundException, IllegalAccessException {
        int[] array = new int[10];
        for(int i:array)
            i = 0;
        compareGoogleGsonStringAndMyConverterString(array);
    }

    @Test
    public void testObjectWithString() throws ClassNotFoundException, IllegalAccessException {
        ObjectWithString objectWithString = new ObjectWithString("testSTring");
        compareGoogleGsonStringAndMyConverterString(objectWithString);
    }

    @Test
    public void testObjectWithTwoStrings() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithTwoStrings objectWithTwoStrings = new ObjectWithTwoStrings("firstName", "lastName");
        compareGoogleGsonStringAndMyConverterString(objectWithTwoStrings);
    }

    @Test
    public void testObjectWithEightPrimitiveTypes() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithEightBasicTypes objectWithEightBasicTypes = new ObjectWithEightBasicTypes(true, 'a', 1.1f, 1.5d, (byte) 1, (short) 10, 100, 1000L);
        compareGoogleGsonStringAndMyConverterString(objectWithEightBasicTypes);
    }

    @Test
    public void testObjectWithByteArray() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithByteArray objectWithByteArray = new ObjectWithByteArray(new byte[10]);
        compareGoogleGsonStringAndMyConverterString(objectWithByteArray);
    }

    @Test
    public void testObjectWithEightPrimitivesArrays() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithEightPrimitiveArrays objectWithEightPrimitiveArrays = new ObjectWithEightPrimitiveArrays(new boolean[10], new char[10], new float[10], new double[10], new byte[10], new short[10], new int[10], new long[10]);
        compareGoogleGsonStringAndMyConverterString(objectWithEightPrimitiveArrays);
    }

    @Test
    public void testObjectWithArrayList() throws ClassNotFoundException, IllegalAccessException {
        ArrayList<Integer> listInteger = new ArrayList<>();
        listInteger.add(1);
        listInteger.add(2);
        ObjectWithArrayList objectWithIntegerArrayList = new ObjectWithArrayList(listInteger);
        compareGoogleGsonStringAndMyConverterString(objectWithIntegerArrayList);
        ArrayList<Float> listFloat = new ArrayList<>();
        listFloat.add(1.0f);
        listFloat.add(2.0f);
        ObjectWithArrayList objectWithFloatArrayList = new ObjectWithArrayList(listFloat);
        compareGoogleGsonStringAndMyConverterString(objectWithFloatArrayList);
    }

    @Test
    public void testObjectWithObjectWithString() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithString objectWithString = new ObjectWithString("testSTring");
        ObjectWithObjectWithString objectWithObjectWithString = new ObjectWithObjectWithString(objectWithString);
        compareGoogleGsonStringAndMyConverterString(objectWithObjectWithString);
    }

    @Test
    public void testObjectWithHashSet() throws IllegalAccessException, ClassNotFoundException {
        HashSet<String> set = new HashSet<>();
        set.add("Name");
        set.add("LastName");
        ObjectWithHashSet object = new ObjectWithHashSet(set);
        compareGoogleGsonStringAndMyConverterString(object);
    }

    private void compareGoogleGsonStringAndMyConverterString(Object object) throws ClassNotFoundException, IllegalAccessException {
        Gson gson = new Gson();
        String gsonString = gson.toJson(object);
        System.out.println(gsonString);

        String myString  = MyConverter.objectToJsonString(object);

        Assert.assertEquals(myString, gsonString);
    }
}
