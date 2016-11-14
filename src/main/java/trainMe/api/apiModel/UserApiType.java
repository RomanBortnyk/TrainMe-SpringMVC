package trainMe.api.apiModel;

/**
 * Created by romab on 11/13/16.
 */
public class UserApiType {

    private int id;
    private String firstName;
    private String lastName;
//    private String userType;


    public UserApiType(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
