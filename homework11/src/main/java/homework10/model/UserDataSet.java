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

    public UserDataSet(String name, int age, Set<PhoneDataSet> phones) {
        this.name = name;
        this.age = age;
        this.phones = phones;
    }

    public UserDataSet(String name, int age, Set<PhoneDataSet> phones, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.phones = phones;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + phones +
                ", address=" + address +
                ", userId=" + userId +
                '}';
    }
}
