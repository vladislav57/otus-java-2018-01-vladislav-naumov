package ru.otus.homework08.converter;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyGson {
    public static JsonObject objectToJsonObject(Object object) throws FileNotFoundException {
        JsonReader reader = Json.createReader(new FileReader("C:\\Users\\vladn\\IdeaProjects\\otus-java-2018-01-vladislav-naumov\\homework08\\src\\main\\resources\\objectWithString.txt"));
        JsonStructure jsonst = reader.read();

        navigateTree(jsonst, "base");

        return (JsonObject) jsonst;

    }

    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }

        return stWriter.toString();
    }

    private static void navigateTree(JsonValue tree, String key) {
        if (key != null)
            System.out.print("Key " + key + ": ");
        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name : object.keySet())
                    navigateTree(object.get(name), name);
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array)
                    navigateTree(val, null);
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("STRING " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("NUMBER " + num.toString());
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
        }
    }
}
