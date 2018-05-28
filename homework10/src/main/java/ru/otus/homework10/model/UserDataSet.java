package ru.otus.homework10.model;

import javax.persistence.Entity;

@Entity
public class UserDataSet extends DataSet {

    private String name;
    private int age;

    public UserDataSet() {
    }

    public UserDataSet(UserDataSet load) {
        this.id = load.getId();
        this.age = load.getAge();
        this.name = load.getName();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserDataSet(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

}
