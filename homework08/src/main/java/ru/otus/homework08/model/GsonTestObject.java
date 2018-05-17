package ru.otus.homework08.model;

public class GsonTestObject {

    private int id;
    private String name;

    public GsonTestObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GsonTestObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
