package trainMe.jsonObjects;

import trainMe.api.apiModel.DisciplineApiType;

import java.util.ArrayList;

/**
 * Created by romab on 11/15/16.
 */
public class UserSearchResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String userType;
    private String description;
    private ArrayList<DisciplineApiType> disciplines;


    public UserSearchResponse() {
    }

    public UserSearchResponse(int id, String firstName, String lastName, String userType, ArrayList<DisciplineApiType> disciplines) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.disciplines = disciplines;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public ArrayList<DisciplineApiType> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<DisciplineApiType> disciplines) {
        this.disciplines = disciplines;
    }
}
