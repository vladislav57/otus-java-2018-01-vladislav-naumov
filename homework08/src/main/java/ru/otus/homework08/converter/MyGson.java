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
            System.out.println(field.getName());
            System.out.println(field.getType());
            if(field.getType().equals(String.class)) {
                model.add(field.getName(), (String) field.get(object));
            } else if(field.getType().equals(boolean.class)) {
                model.add(field.getName(), field.getBoolean(object));
            } else if(field.getType().equals(char.class)) {
                model.add(field.getName(), "" + field.getChar(object)); //JsonObjectBuilder не принимает на вход char, только String
            } else if(field.getType().equals(float.class)) {
                model.add(field.getName(), Double.valueOf("" + field.getFloat(object))); // JsonObjectBuilder не принимает на вход float, только double
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
                for(byte b:(byte[]) field.get(object)) {
                    jab.add(b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[Z"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(boolean b:(boolean[]) field.get(object)) {
                    jab.add(b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[C"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(char b:(char[]) field.get(object)) {
                    jab.add("" + b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[F"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(float b:(float[]) field.get(object)) {
                    jab.add(Double.valueOf("" + b));
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[D"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(double b:(double[]) field.get(object)) {
                    jab.add(b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[S"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(short b:(short[]) field.get(object)) {
                    jab.add(b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[I"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(int b:(int[]) field.get(object)) {
                    jab.add(b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("[J"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(long b:(long[]) field.get(object)) {
                    jab.add(b);
                }
                model.add(field.getName(), jab);
            } else if(field.getType().equals(Class.forName("java.util.ArrayList"))) {
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(Integer i : (ArrayList<Integer>) field.get(object)) {
                    jab.add(i);
                }
                model.add("arrayList", jab);
            }
        }

        return model.build();
    }
}
