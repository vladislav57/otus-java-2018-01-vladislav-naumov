package ru.otus.homework10.model;

import javax.persistence.*;

@Entity
@Table(name = "PHONE")
public class PhoneDataSet {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDataSet userDataSet;

    public PhoneDataSet(String phone, UserDataSet userDataSet) {
        this.phone = phone;
        this.userDataSet = userDataSet;
    }

    public PhoneDataSet(String phone) {
        this.phone = phone;
    }

    public PhoneDataSet() {
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }

    public UserDataSet getUserDataSet() {
        return userDataSet;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserDataSet(UserDataSet userDataSet) {
        this.userDataSet = userDataSet;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                ", phone='" + phone + '\'' +
                '}';
    }
}
