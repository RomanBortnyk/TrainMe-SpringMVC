package trainMe.jsonObjects;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by romanb on 12/7/16.
 */
public class TestObject {


    private int id ;

    @JsonIgnore
    private String name;


    private String alias;
    private int serial ;

    public TestObject(int id, String name, String alias, int serial) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.serial = serial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}
