package ru.otus.homework08.model;

import java.util.HashSet;

public class ObjectWithHashSet {

    private HashSet hashSet;

    public ObjectWithHashSet(HashSet hashSet) {
        this.hashSet = hashSet;
    }

    public HashSet getHashSet() {
        return hashSet;
    }

    public void setHashSet(HashSet hashSet) {
        this.hashSet = hashSet;
    }
}
