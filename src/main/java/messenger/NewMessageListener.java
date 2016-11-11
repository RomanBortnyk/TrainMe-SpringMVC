package messenger;


import trainMe.model.Message;
import trainMe.model.User;

/**
 * Created by romab on 10/28/16.
 */
public class NewMessageListener implements Observer {

    private SmallerMessage currentMessage;


    public void update(Message message) {
        currentMessage = new SmallerMessage();
        currentMessage.setAuthorId(message.getAuthor().getId());
        currentMessage.setAuthorFirstName(message.getAuthor().getFirstName());
        currentMessage.setAuthorLastName(message.getAuthor().getLastName());
        currentMessage.setText(message.getText());
        currentMessage.setChatId(message.getChat().getId());
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

    public String getMessageText() {
        return currentMessage.getText();
    }

    public SmallerMessage getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(SmallerMessage currentMessage) {
        this.currentMessage = currentMessage;
    }


}
