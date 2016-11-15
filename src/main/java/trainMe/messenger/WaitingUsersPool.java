package trainMe.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.MessageDao;
import trainMe.model.Chat;
import trainMe.model.Message;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by romab on 11/14/16.
 */
@Service
public class WaitingUsersPool implements Observable {

    @Autowired
    ChatDao chatDao;
    @Autowired
    MessageDao messageDao;

    List<Observer> observers = new ArrayList<Observer>();
    List<User> waitingUsers = new ArrayList<User>();


    public synchronized void addMessage(Message message) {
//
//        String name = Thread.currentThread().getName();
//        System.out.println(name + " стартовал");

        Iterator<User> userIterator = waitingUsers.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            Iterator<Chat> chatIterator = chatDao.getUserChats(user.getId()).iterator();
            while (chatIterator.hasNext()){
                Chat chat = chatIterator.next();
                if (chat.getId() == message.getChat().getId()) {
                    synchronized (user) {
                        notifyObservers(message);
                        user.notify();
//                        System.out.println(name + " поток Notifier отработал");
                    }
                }
            }
        }

//        for (User user : waitingUsers) {
//            List<Chat> usersChats = chatDao.getUserChats(user.getId());
//            for (Chat chat : usersChats) {
//                if (chat.getId() == message.getChat().getId()) {
//                    synchronized (user) {
//                        notifyObservers(message);
//                        user.notify();
//                        System.out.println(name + " поток Notifier отработал");
//
//                    }
//                }
//            }
//        }

        messageDao.create(message);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public synchronized void registerObserver(Observer o) {
        observers.add(o);

    }

    public synchronized void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Message newMessage) {
        for (Observer observer : observers)
            observer.update(newMessage);
    }

    public synchronized void addWaitingUser(User user) {

        this.waitingUsers.add(user);

    }

    public synchronized void removeWaitingUser(User user) {

        this.waitingUsers.remove(user);

    }

    public void setWaitingUsers(List<User> waitingUsers) {
        this.waitingUsers = waitingUsers;
    }

}
