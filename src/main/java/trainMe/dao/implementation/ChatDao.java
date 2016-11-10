package trainMe.dao.implementation;


import dao.interfaces.AbstractDao;
import model.Chat;
import model.Item;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.List;

/**
 * Created by romab on 10/2/16.
 */
public class ChatDao extends AbstractDao {

    public Chat create(Chat chat) {

        return (Chat) super.create(chat);
    }


    public Chat read(int id) {
        return (Chat) super.read(Chat.class, id);
    }

    public List<Chat> getUserChats(int userId) {

        List<Chat> result;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Chat where user1.id =:userId " +
                "or user2.id =:userId");

        q.setInteger("userId", userId);

        result = q.list();

        session.getTransaction().commit();


        for (Chat chat : result) {
            if (chat.getUser1().getId() != userId) {
                User tempUser = chat.getUser1();
                chat.setUser1(chat.getUser2());
                chat.setUser2(tempUser);
            }
        }

        return result;

    }

    public Chat readByUsersIds(int user1Id, int user2Id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Chat where " +
                "(user1.id = :user1Id and user2.id = :user2Id) or " +
                "(user1.id = :user2Id and user2.id = :user1Id)");

        q.setInteger("user1Id", user1Id);
        q.setInteger("user2Id", user2Id);
        Chat resultChat = (Chat) q.uniqueResult();

        session.getTransaction().commit();

        return resultChat;
    }

    public boolean isExistByUsersId(int user1Id, int user2Id) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Chat where " +
                "(user1.id = :user1Id and user2.id = :user2Id) or " +
                "(user1.id = :user2Id and user2.id = :user1Id)");
        q.setInteger("user1Id", user1Id);
        q.setInteger("user2Id", user2Id);
        Chat resultChat = (Chat) q.uniqueResult();

        session.getTransaction().commit();
        if (resultChat == null) return false;
        else return true;

    }


    public Item update(Item item) {
        return super.update(item);
    }


    public void delete(Item item) {
        super.delete(item);
    }


    public List readAll(Class clazz) {
        return super.readAll(clazz);
    }
}
