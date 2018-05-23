package ru.otus.homework08.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectWithArrayList<T> {

    ArrayList arrayList;

    public ObjectWithArrayList(ArrayList<T> object) {
       this.arrayList = object;
    }

    public ObjectWithArrayList() {
    }

    public ArrayList getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }
}
