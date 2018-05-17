package ru.otus.homework08;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.homework08.converter.MyGson;
import ru.otus.homework08.model.ObjectWithString;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.FileNotFoundException;
import java.io.StringWriter;

public class TestMyGson {

    @Test
    public void testObjectWithString() throws FileNotFoundException {
        ObjectWithString objectWithString = new ObjectWithString("testName");

        JsonObject myPrepatedJsonObject = MyGson.objectToJsonObject(objectWithString);
        String myString = writeToString(myPrepatedJsonObject);

        Gson gson = new Gson();
        String gsonString = gson.toJson(objectWithString);

        Assert.assertEquals(myString, gsonString);
    }

    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }
        return stWriter.toString();
    }
}
