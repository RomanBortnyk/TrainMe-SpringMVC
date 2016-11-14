package trainMe.api.apiModel;

/**
 * Created by romab on 11/14/16.
 */
public class ChatApiType {

    private int chatId;
    private  String firstName;
    private  String lastName;
    private  int userId;

    public ChatApiType(int chatId, String firstName, String lastName, int userId) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
