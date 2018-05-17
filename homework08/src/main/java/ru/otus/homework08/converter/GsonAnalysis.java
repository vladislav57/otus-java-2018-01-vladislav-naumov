package ru.otus.homework08.converter;

import com.google.gson.Gson;
import ru.otus.homework08.model.GsonTestObject;

public class GsonAnalysis {

    public static void main(String[] args) {

        GsonTestObject testObject = new GsonTestObject(1, "testName");
        Gson gson = new Gson();
        String json = gson.toJson(testObject);

        System.out.println(json);

        GsonTestObject object = gson.fromJson(json, GsonTestObject.class);

        System.out.println(object);
    }
}
