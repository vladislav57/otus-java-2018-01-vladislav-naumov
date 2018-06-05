package jetty.homework10.model;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class AddressDataSet {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "street")
    private String street;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserDataSet userDataSet;

    public AddressDataSet() {
    }

    public AddressDataSet(String street, UserDataSet userDataSet) {
        this.street = street;
        this.userDataSet = userDataSet;
    }

    public AddressDataSet(String street) {
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public UserDataSet getUserDataSet() {
        return userDataSet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setUserDataSet(UserDataSet userDataSet) {
        this.userDataSet = userDataSet;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                ", street='" + street + '\'' +
                '}';
    }
}
