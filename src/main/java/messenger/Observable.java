package messenger;


import trainMe.model.Message;

/**
 * Created by romab on 10/28/16.
 */
public interface Observable {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Message newMessage);

}
