package ru.otus.homework08.converter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.*;

public class MyConverter {

    /**
     * Google gson преобразует в json не только объекты, но и примитивные типы
     * например, int, Integer
     * При этом json не заключается в фигурные скобки, как в случае с объектом
     */
    public static String objectToJsonString(Object object) throws ClassNotFoundException, IllegalAccessException {
        System.out.println(object.getClass());
        if(isString(object))
            return object.toString();
        else if(isOfPrymitiveType(object))
            return object.toString();
        else if(isOfPrimitiveTypeWrapper(object))
            return object.toString();
        else if(isVectorOfPrimitiveType(object))
            return vectorOfPrimitiveType(object).build().toString();
        else if(isVectorOfPrimitiveTypeWrapper(object))
            return vectorOfPrimitiveTypeWrapper(object).build().toString();
        else if(isCollection(object)) {
            return collectionFromCollectionOfObjects(object).build().toString();
        } else { // в этом случае разбираем сложный объект
            JsonObjectBuilder model = Json.createObjectBuilder();
            generateObjectRecursively(object, model);
            return model.build().toString();
        }
    }

    //Объод объекта проще всего сделать с помощью рекурсии
    private static void generateObjectRecursively(Object object, JsonObjectBuilder model) throws IllegalAccessException, ClassNotFoundException {
        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
            System.out.println("Field name " + field.getName() + " field type " + field.getType());
            if(field.getType().equals(String.class)) {
                model.add(field.getName(), (String) field.get(object));
            } else if(field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
                model.add(field.getName(), field.getBoolean(object));
            } else if(field.getType().equals(char.class) || field.getType().equals(Character.class)) {
                model.add(field.getName(), "" + field.getChar(object)); //JsonObjectBuilder не принимает на вход char, только String
            } else if(field.getType().equals(float.class) || field.getType().equals(Float.class)) {
                model.add(field.getName(), Double.valueOf("" + field.getFloat(object))); // JsonObjectBuilder не принимает на вход float, только double
            } else if(field.getType().equals(double.class) || field.getType().equals(Double.class)) {
                model.add(field.getName(), field.getDouble(object));
            } else if(field.getType().equals(byte.class) || field.getType().equals(Byte.class)) {
                model.add(field.getName(), field.getByte(object));
            } else if(field.getType().equals(short.class) || field.getType().equals(Short.class)) {
                model.add(field.getName(), field.getShort(object));
            } else if(field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                model.add(field.getName(), field.getInt(object));
            } else if(field.getType().equals(long.class) || field.getType().equals(Long.class)) {
                model.add(field.getName(), field.getLong(object));
            } else if(isVectorOfPrimitiveType(field.get(object))) {
                model.add(field.getName(), vectorOfPrimitiveType(field.get(object)));
            } else if(isVectorOfPrimitiveTypeWrapper(field.get(object))) {
                model.add(field.getName(), vectorOfPrimitiveTypeWrapper(field.get(object)));
            } else if(isCollection(field.get(object))) {
                model.add(getCollectionName(field), collectionFromCollectionOfObjects(field.get(object)));
            } else {
                //только в этом случае нужно в дереве объекта создать поддерево
                JsonObjectBuilder localModel = Json.createObjectBuilder();
                generateObjectRecursively(field.get(object), localModel);
                model.add(field.getName(), localModel);
            }
        }
    }

    private static String getCollectionName(Field field) {
        String fullName = field.getGenericType().getTypeName();
        fullName = fullName.replace("java.util.", "");
        String firstLetter = fullName.substring(0, 1);
        String othersPart = fullName.substring(1);
        return firstLetter.toLowerCase() + othersPart;
    }

    private static JsonArrayBuilder collectionFromCollectionOfObjects(Object object) throws ClassNotFoundException, IllegalAccessException {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(Object obj : (Collection<Object>) object) {
            if(obj.getClass().equals(String.class)) {
                jab.add((String)obj);
            } else if(obj.getClass().equals(boolean.class) || obj.getClass().equals(Boolean.class)) {
                jab.add((Boolean) obj);
            } else if(obj.getClass().equals(char.class) || obj.getClass().equals(Character.class)) {
                jab.add("" + obj); //JsonObjectBuilder не принимает на вход char, только String
            } else if(obj.getClass().equals(float.class) || obj.getClass().equals(Float.class)) {
                jab.add(Double.valueOf("" + obj)); // JsonObjectBuilder не принимает на вход float, только double
            } else if(obj.getClass().equals(double.class) || obj.getClass().equals(Double.class)) {
                jab.add((Double) obj);
            } else if(obj.getClass().equals(byte.class) || obj.getClass().equals(Byte.class)) {
                jab.add((Byte) obj);
            } else if(obj.getClass().equals(short.class) || obj.getClass().equals(Short.class)) {
                jab.add((Short) obj);
            } else if(obj.getClass().equals(int.class) || obj.getClass().equals(Integer.class)) {
                jab.add((Integer) obj);
            } else if(obj.getClass().equals(long.class) || obj.getClass().equals(Long.class)) {
                jab.add((Long) obj);
            } else if(isVectorOfPrimitiveType(obj)) {
                jab.add(vectorOfPrimitiveType(obj));
            } else if(isVectorOfPrimitiveTypeWrapper(obj)) {
                jab.add(vectorOfPrimitiveTypeWrapper(obj));
            } else if(isCollection(obj)) {
                jab.add(collectionFromCollectionOfObjects(obj));
            } else {
                JsonObjectBuilder localModel = Json.createObjectBuilder();
                generateObjectRecursively(obj, localModel);
                jab.add(localModel);
            }
        }
        return jab;
    }

    private static JsonArrayBuilder vectorOfPrimitiveTypeWrapper(Object object) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Class oClass = object.getClass();
        if(oClass.equals((new Boolean[0]).getClass())) {
            for(Boolean b:(Boolean[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals((new Character[0]).getClass())) {
            for(Character b:(Character[]) object) {
                jab.add("" + b);
            }
        } else if(oClass.equals((new Float[0]).getClass())) {
            for(Float b:(Float[]) object) {
                jab.add(Double.valueOf("" + b));
            }
        } else if(oClass.equals((new Double[0]).getClass())) {
            for(Double b:(Double[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals((new Byte[0]).getClass())) {
            for(Byte b:(Byte[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals((new Short[0]).getClass())) {
            for(Short b:(Short[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals((new Integer[0]).getClass())) {
            for(Integer b:(Integer[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals((new Long[0]).getClass())) {
            for(Long b:(Long[]) object) {
                jab.add(b);
            }
        }
        return jab;
    }

    private static JsonArrayBuilder vectorOfPrimitiveType(Object object) throws ClassNotFoundException {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Class oClass = object.getClass();
        if(oClass.equals(Class.forName("[Z"))) {
            for(boolean b:(boolean[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals(Class.forName("[C"))) {
            for(char b:(char[]) object) {
                jab.add("" + b);
            }
        } else if(oClass.equals(Class.forName("[F"))) {
            for(float b:(float[]) object) {
                jab.add(Double.valueOf("" + b));
            }
        } else if(oClass.equals(Class.forName("[D"))) {
            for(double b:(double[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals(Class.forName("[B"))) {
            for(byte b:(byte[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals(Class.forName("[S"))) {
            for(short b:(short[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals(Class.forName("[I"))) {
            for(int b:(int[]) object) {
                jab.add(b);
            }
        } else if(oClass.equals(Class.forName("[J"))) {
            for(long b:(long[]) object) {
                jab.add(b);
            }
        }
        return jab;
    }

    private static boolean isCollection(Object object) {
        Class aClass = object.getClass();
        return (aClass.equals(ArrayList.class) || aClass.equals(HashSet.class) || aClass.equals(LinkedList.class) || aClass.equals(TreeSet.class) || aClass.equals(LinkedHashSet.class) || aClass.equals(Stack.class) || aClass.equals(Vector.class));
    }

    private static boolean isOfPrimitiveTypeWrapper(Object object) {
        Class aClass = object.getClass();
        return (aClass.equals(Boolean.class) || aClass.equals(Character.class) || aClass.equals(Float.class) || aClass.equals(Double.class) || aClass.equals(Byte.class) || aClass.equals(Short.class) || aClass.equals(Integer.class) || aClass.equals(Long.class));
    }

    private static boolean isOfPrymitiveType(Object object) {
        Class aClass = object.getClass();
        return (aClass.equals(boolean.class) || aClass.equals(char.class) || aClass.equals(float.class) || aClass.equals(double.class) || aClass.equals(byte.class) || aClass.equals(short.class) || aClass.equals(int.class) || aClass.equals(long.class));
    }

    private static boolean isVectorOfPrimitiveType(Object object) throws ClassNotFoundException {
        Class aClass = object.getClass();
        return (aClass.equals(Class.forName("[Z")) || aClass.equals(Class.forName("[C")) || aClass.equals(Class.forName("[F")) || aClass.equals(Class.forName("[D")) || aClass.equals(Class.forName("[B")) || aClass.equals(Class.forName("[S")) || aClass.equals(Class.forName("[I")) || aClass.equals(Class.forName("[J")));
    }

    private static boolean isVectorOfPrimitiveTypeWrapper(Object object) throws ClassNotFoundException {
        Class aClass = object.getClass();
        return aClass.equals(Class.forName("[Z")) || aClass.equals(Class.forName("[C")) || aClass.equals(Class.forName("[F")) || aClass.equals(Class.forName("[D")) || aClass.equals(Class.forName("[B")) || aClass.equals(Class.forName("[S")) || aClass.equals((new Integer[5].getClass())) || aClass.equals(Class.forName("[J"));
    }

    private static boolean isString(Object object) {
        return object.getClass().equals(String.class);
    }

}
