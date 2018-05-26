package ru.otus.homework08.model;

public class ObjectWithObjectWithString {

    private ObjectWithString objectWithString;

    public ObjectWithObjectWithString() {
    }

    public ObjectWithObjectWithString(ObjectWithString objectWithString) {
        this.objectWithString = objectWithString;
    }

    public ObjectWithString getObjectWithString() {
        return objectWithString;
    }

    public void setObjectWithString(ObjectWithString objectWithString) {
        this.objectWithString = objectWithString;
    }
}
