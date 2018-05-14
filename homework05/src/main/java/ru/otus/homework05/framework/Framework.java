package ru.otus.homework05.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Framework {
    private static Method Before;
    private static Method After;

    public static void runTestInClass(String s){
        init();
        Class targetClass = null;
        try {
            targetClass = Class.forName(s);
            Method[] methods = targetClass.getMethods();
            Before = checkAnnotation(methods, "ru.otus.homework05.annotations.Before");
            After = checkAnnotation(methods, "ru.otus.homework05.annotations.After");
            Set<Method> tests = getAllTests(methods);

            for(Method m : tests) {
                runTest(targetClass, m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> T runTest(Class<T> type, Method method) {
        T t = null;
        try {
            t = type.getConstructor().newInstance();
            if(Before != null)
                Before.invoke(t);
            method.invoke(t);
            if(After != null)
                After.invoke(t);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }

    private static Set<Method> getAllTests(Method[] methods) throws ClassNotFoundException {
        Set<Method> tests = new HashSet<Method>();
        for(Method m : methods) {
            Method test = checkAnnotation(m, "ru.otus.homework05.annotations.Test");
            if(test != null)
                tests.add(m);
        }
        return tests;
    }

    private static Method checkAnnotation(Method[] methods, String annotationName) throws ClassNotFoundException {
        for(Method m : methods) {
            Method check = checkAnnotation(m, annotationName);
            if(check != null)
                return check;
        }
        return null;
    }

    private static Method checkAnnotation(Method method, String annotationName) throws ClassNotFoundException {
        Annotation[] annotations = method.getAnnotations();
        for(Annotation a : annotations) {
            if(a.annotationType().equals(Annotation.class.forName(annotationName))) {
                return method;
            }
        }
        return null;
    }

    private static void init() {
        After = null;
        Before = null;
    }
}
