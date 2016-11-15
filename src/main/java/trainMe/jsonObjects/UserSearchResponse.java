package trainMe.jsonObjects;

import trainMe.api.apiModel.DisciplineApiType;

import java.util.ArrayList;

/**
 * Created by romab on 11/15/16.
 */
public class UserSearchResponse {

    private int id;
    private String name;
    private ArrayList<DisciplineApiType> disciplines;

    public UserSearchResponse(int id, String name) {
        this.id = id;
        this.name = name;
        this.disciplines = new ArrayList<DisciplineApiType>();
    }

    public UserSearchResponse() {
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

    public ArrayList<DisciplineApiType> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<DisciplineApiType> disciplines) {
        this.disciplines = disciplines;
    }
}
