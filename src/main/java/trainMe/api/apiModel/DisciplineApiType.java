package trainMe.api.apiModel;

/**
 * Created by romab on 11/13/16.
 */
public class DisciplineApiType {

    private int id;
    private String name;

    public DisciplineApiType(int id, String name) {
        this.id = id;
        this.name = name;
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
}
