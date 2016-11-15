package trainMe.jsonObjects;

/**
 * Created by romab on 11/15/16.
 */
public class ReceivedMessageJson {

    private int chatId;
    private String messageText;
    private int destinationUserId;


    public ReceivedMessageJson(int chatId, String massageText) {
        this.chatId = chatId;
        this.messageText = massageText;
    }

    public ReceivedMessageJson() {
    }

    public int getDestinationUserId() {
        return destinationUserId;
    }

    public void setDestinationUserId(int destinationUserId) {
        this.destinationUserId = destinationUserId;
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
}
