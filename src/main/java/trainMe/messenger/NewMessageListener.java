package trainMe.messenger;


import trainMe.api.apiModel.MessageApiType;
import trainMe.model.Message;
import trainMe.model.User;

/**
 * Created by romab on 10/28/16.
 */
public class NewMessageListener implements Observer {

    private MessageApiType currentMessage;


    public void update(Message message) {
        currentMessage = new MessageApiType();
        currentMessage.setAuthorId(message.getId());
        currentMessage.setAuthorFirstName(message.getAuthor().getFirstName());
        currentMessage.setAuthorLastName(message.getAuthor().getLastName());
        currentMessage.setChatId(message.getChat().getId());
        currentMessage.setText(message.getText());

    }


    public void justWait(User currentUser) {
        try {

            synchronized (currentUser) {

                currentUser.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageApiType getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(MessageApiType currentMessage) {
        this.currentMessage = currentMessage;
    }
}
