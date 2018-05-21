package ru.otus.homework08.converter;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MyGson {
    public static JsonObject objectToJsonObject(Object object) throws IllegalAccessException, ClassNotFoundException {

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
        }

        JsonObjectBuilder model = Json.createObjectBuilder();

        for(Field field : fields) {
            System.out.println(field.getType());
            if(field.getType().equals(String.class)) {
                model.add(field.getName(), (String) field.get(object));
            } else if(field.getType().equals(boolean.class)) {
                model.add(field.getName(), field.getBoolean(object));
            } else if(field.getType().equals(char.class)) {
                model.add(field.getName(), "" + field.getChar(object));
            } else if(field.getType().equals(float.class)) {
                model.add(field.getName(), Double.valueOf("" + field.getFloat(object)));
            } else if(field.getType().equals(double.class)) {
                model.add(field.getName(), field.getDouble(object));
            } else if(field.getType().equals(byte.class)) {
                model.add(field.getName(), field.getByte(object));
            } else if(field.getType().equals(short.class)) {
                model.add(field.getName(), field.getShort(object));
            } else if(field.getType().equals(int.class)) {
                model.add(field.getName(), field.getInt(object));
            } else if(field.getType().equals(long.class)) {
                model.add(field.getName(), field.getLong(object));
            } else if(field.getType().equals(Class.forName("[B"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                byte[] bytes = (byte[]) field.get(object);
                for(int i=0; i<100; i++) {
                    jab.add(bytes[i]);
                }
                model.add(field.getName(), jab);
            }
        }

        return model.build();
    }
}
