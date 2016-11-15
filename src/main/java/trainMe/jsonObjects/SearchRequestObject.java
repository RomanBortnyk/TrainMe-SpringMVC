package trainMe.jsonObjects;

/**
 * Created by romab on 11/15/16.
 */
public class SearchRequestObject {

    private String searchOption;
    private String userTypeOption;
    private String searchString;

    public SearchRequestObject(String searchOption, String userTypeOption, String searchString) {
        this.searchOption = searchOption;
        this.userTypeOption = userTypeOption;
        this.searchString = searchString;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getUserTypeOption() {
        return userTypeOption;
    }

    public void setUserTypeOption(String userTypeOption) {
        this.userTypeOption = userTypeOption;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
