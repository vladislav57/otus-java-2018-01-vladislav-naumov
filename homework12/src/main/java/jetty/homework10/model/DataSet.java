package jetty.homework10.model;

import javax.persistence.*;

@Entity
@Table( name = "userdata" )
public abstract class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    protected long userId;

    public DataSet() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
