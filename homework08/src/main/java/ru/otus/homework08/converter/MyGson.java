package ru.otus.homework08.converter;

import javax.json.*;
import java.lang.reflect.Field;

public class MyGson {
    public static JsonObject objectToJsonObject(Object object) throws NoSuchFieldException, IllegalAccessException {

        Field[] fields = object.getClass().getDeclaredFields();
        Field field0 = fields[0];

        Field field1 = object.getClass().getDeclaredField(field0.getName());
        field1.setAccessible(true);

        JsonObject model = Json.createObjectBuilder()
                .add(field1.getName(), (String) field1.get(object))
                .build();

        return model;
    }
}
