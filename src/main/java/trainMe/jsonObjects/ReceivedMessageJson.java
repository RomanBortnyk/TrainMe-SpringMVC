package trainMe.jsonObjects;

/**
 * Created by romab on 11/15/16.
 */
public class ReceivedMessageJson {

    private int chatId;
    private String messageText;
    private String destinationUserLogin;


    public ReceivedMessageJson(int chatId, String messageText, String destinationUserLogin) {
        this.chatId = chatId;
        this.messageText = messageText;
        this.destinationUserLogin = destinationUserLogin;
    }

    public ReceivedMessageJson() {

    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getDestinationUserLogin() {
        return destinationUserLogin;
    }

    public void setDestinationUserLogin(String destinationUserLogin) {
        this.destinationUserLogin = destinationUserLogin;
    }
}
