package trainMe.messenger;


import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.MessageDao;
import trainMe.model.Chat;
import trainMe.model.Message;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 10/28/16.
 */
public class Database implements Observable {

    List<Observer> observers = new ArrayList<Observer>();
    List<User> waitingUsers = new ArrayList<User>();

    public Database(){

    }


    public void addMessage (Message message){

        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал");
        MessageDao messageDao = new MessageDao();
        ChatDao chatDao = new ChatDao();


        for (User user: waitingUsers){
            List<Chat> usersChats = chatDao.getUserChats(user.getId());
            for (Chat chat: usersChats){
                if ( chat.getId() == message.getChat().getId()){
                    synchronized (user) {
                        notifyObservers(message);
                        user.notify();
                        System.out.println(name + " поток Notifier отработал");

                    }
                }

            }
        }

        messageDao.create(message);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void registerObserver(Observer o) {
        observers.add(o);

    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Message newMessage) {
        for (Observer observer : observers)
            observer.update(newMessage);
    }


    public List<User> getWaitingUsers() {
        return waitingUsers;
    }

    public void setWaitingUsers(List<User> waitingUsers) {
        this.waitingUsers = waitingUsers;
    }
}
