package ru.otus.homework08;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.homework08.converter.MyGson;
import ru.otus.homework08.model.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMyGson {

    @Test
    public void testObjectWithString() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithString objectWithString = new ObjectWithString("testName");
        compareGoogleGsonStringAndMyGsonString(objectWithString);
    }

    @Test
    public void testObjectWithTwoStrings() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithTwoStrings objectWithTwoStrings = new ObjectWithTwoStrings("firstName", "lastName");
        compareGoogleGsonStringAndMyGsonString(objectWithTwoStrings);
    }

    @Test
    public void testEightPrimitiveTypes() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithEightBasicTypes objectWithEightBasicTypes = new ObjectWithEightBasicTypes(true, 'a', 1.1f, 1.5d, (byte) 1, (short) 10, 100, 1000L);
        compareGoogleGsonStringAndMyGsonString(objectWithEightBasicTypes);
    }

    @Test
    public void testByteArray() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithByteArray objectWithByteArray = new ObjectWithByteArray(new byte[100]);
        compareGoogleGsonStringAndMyGsonString(objectWithByteArray);
    }

    @Test
    public void testEightPrimitivesArrays() throws IllegalAccessException, ClassNotFoundException {
        ObjectWithEightPrimitiveArrays objectWithEightPrimitiveArrays = new ObjectWithEightPrimitiveArrays(new boolean[10], new char[10], new float[10], new double[10], new byte[10], new short[10], new int[10], new long[10]);
        compareGoogleGsonStringAndMyGsonString(objectWithEightPrimitiveArrays);
    }

    @Test
    public void testArrayList() throws ClassNotFoundException, IllegalAccessException {
        ArrayList<Integer> listInteger = new ArrayList<>();
        listInteger.add(1);
        listInteger.add(2);
        ObjectWithArrayList objectWithIntegerArrayList = new ObjectWithArrayList(listInteger);
        compareGoogleGsonStringAndMyGsonString(objectWithIntegerArrayList);
        ArrayList<Float> listFloat = new ArrayList<>();
        listFloat.add(1.0f);
        listFloat.add(2.0f);
        ObjectWithArrayList objectWithFloatArrayList = new ObjectWithArrayList(listFloat);
        compareGoogleGsonStringAndMyGsonString(objectWithFloatArrayList);
    }

    @Test
    public void testGsonInteger() {
        Gson gson = new Gson();
        String gsonString = gson.toJson(new Integer(5));
        System.out.println(gsonString);
    }

    @Test
    public void testGsonArray() {
        Gson gson = new Gson();
        String gsonString = gson.toJson(Arrays.asList(1, 2, 3));
        System.out.println(gsonString);
    }

    private void compareGoogleGsonStringAndMyGsonString(Object object) throws IllegalAccessException, ClassNotFoundException {
        JsonObject myPrepatedJsonObject = MyGson.objectToJsonObject(object);
        String myString = writeToString(myPrepatedJsonObject);

        Gson gson = new Gson();
        String gsonString = gson.toJson(object);

        Assert.assertEquals(myString, gsonString);
    }

    private String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }
        return stWriter.toString();
    }
}
