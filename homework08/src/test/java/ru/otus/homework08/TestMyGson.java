package ru.otus.homework08;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.homework08.converter.MyGson;
import ru.otus.homework08.model.ObjectWith8BasicTypes;
import ru.otus.homework08.model.ObjectWithByteArray;
import ru.otus.homework08.model.ObjectWithString;
import ru.otus.homework08.model.ObjectWithTwoStrings;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.StringWriter;

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
    public void test8types() throws IllegalAccessException, ClassNotFoundException {
        ObjectWith8BasicTypes objectWith8BasicTypes = new ObjectWith8BasicTypes(true, 'a', 1.1f, 1.5d, (byte) 1, (short) 10, 100, 1000L);
        compareGoogleGsonStringAndMyGsonString(objectWith8BasicTypes);
    }

    @Test
    public void testMyteArray() throws IllegalAccessException, ClassNotFoundException {
        byte[] array = new byte[100];
        for(int i=0; i<100; i++) {
            array[i] = 0;
        }
        ObjectWithByteArray objectWithByteArray = new ObjectWithByteArray(array);
        System.out.println();
        compareGoogleGsonStringAndMyGsonString(objectWithByteArray);
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
