package messenger;

/**
 * Created by romab on 11/1/16.
 */
public class SmallerMessage {

    private int authorId;
    private String authorFirstName;
    private String authorLastName;
    private String text;
    private int chatId;

    public SmallerMessage(int authorId, String text, String authorFirstName, String authorLastName) {
        this.authorId = authorId;
        this.text = text;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public SmallerMessage() {

    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
