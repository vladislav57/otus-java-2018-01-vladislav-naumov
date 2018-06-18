package homework10.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userDataSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PhoneDataSet> phones = new HashSet<PhoneDataSet>();

    @OneToOne(mappedBy = "userDataSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AddressDataSet address;

    public UserDataSet() {
    }

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserDataSet(UserDataSet uds) {
        this.name = uds.getName();
        this.age = uds.getAge();
        this.address = uds.getAddress();
        this.phones = uds.getPhones();
    }

    public UserDataSet(String name, int age, String login, String password, Set<PhoneDataSet> phones, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
        this.phones = phones;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Set<PhoneDataSet> getPhones() {
        return phones;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhones(Set<PhoneDataSet> phones) {
        this.phones = phones;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phones=" + phones +
                ", address=" + address +
                ", userId=" + userId +
                '}';
    }
}
