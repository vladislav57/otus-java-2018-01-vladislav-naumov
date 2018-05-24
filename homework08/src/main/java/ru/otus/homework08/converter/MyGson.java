package ru.otus.homework08.converter;

import javax.json.*;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class MyGson {
    public static JsonObjectBuilder createBuilderRecursivly(Object object) throws ClassNotFoundException, IllegalAccessException {
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
                System.out.println(Class.forName("java.util.ArrayList"));
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(Object i : (ArrayList<Object>) field.get(object)) {
                    jab.add(createBuilderRecursivly(i));
                }
                model.add("arrayList", jab);
            }
        }

        return model;
    }

    private static JsonObjectBuilder buiderFromPrimitiveType(Object object) {
        System.out.println(object.getClass());
        JsonObjectBuilder model = Json.createObjectBuilder();
        /*if(object.getClass().equals(String.class)) {
            model.add((String) object);
        } else if(object.getClass().equals(boolean.class)) {
            model.add(field.getName(), field.getBoolean(object));
        } else if(object.getClass().equals(char.class)) {
            model.add(field.getName(), "" + field.getChar(object)); //JsonObjectBuilder не принимает на вход char, только String
        } else if(object.getClass().equals(float.class)) {
            model.add(field.getName(), Double.valueOf("" + field.getFloat(object))); // JsonObjectBuilder не принимает на вход float, только double
        } else if(object.getClass().equals(double.class)) {
            model.add(field.getName(), field.getDouble(object));
        } else if(object.getClass().equals(byte.class)) {
            model.add(field.getName(), field.getByte(object));
        } else if(object.getClass().equals(short.class)) {
            model.add(field.getName(), field.getShort(object));
        } else if(object.getClass().equals(int.class)) {
            model.add(field.getName(), field.getInt(object));
        } else if(object.getClass().equals(long.class)) {
            model.add(field.getName(), field.getLong(object));
        }*/
        return null;
    }

    private static boolean isVectorOfPrimitiveType(Object object) throws ClassNotFoundException {
        Class aClass = object.getClass();
        return (aClass.equals(Class.forName("[Z")) || aClass.equals(Class.forName("[C")) || aClass.equals(Class.forName("[F")) || aClass.equals(Class.forName("[D")) || aClass.equals(Class.forName("[B")) || aClass.equals(Class.forName("[S")) || aClass.equals(Class.forName("[I")) || aClass.equals(Class.forName("[J")));
    }

    private static boolean isString(Object object) {
        return object.getClass().equals(String.class);
    }

    private static boolean isOfPrimitiveTypeWrapper(Object object) {
        Class aClass = object.getClass();
        return (aClass.equals(Boolean.class) || aClass.equals(Character.class) || aClass.equals(Float.class) || aClass.equals(Double.class) || aClass.equals(Byte.class) || aClass.equals(Short.class) || aClass.equals(Integer.class) || aClass.equals(Long.class));
    }

    private static boolean isOfPrymitiveType(Object object) {
        Class aClass = object.getClass();
        return (aClass.equals(boolean.class) || aClass.equals(char.class) || aClass.equals(float.class) || aClass.equals(double.class) || aClass.equals(byte.class) || aClass.equals(short.class) || aClass.equals(int.class) || aClass.equals(long.class));
    }

    public static JsonObject objectToJsonObject(Object object) throws IllegalAccessException, ClassNotFoundException {

        JsonObjectBuilder jsonObjectBuilder = createBuilderRecursivly(object);
        return jsonObjectBuilder.build();


    }
}
