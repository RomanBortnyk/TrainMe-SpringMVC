package trainMe.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Chat;
import trainMe.model.Item;
import trainMe.model.User;

import java.util.List;

/**
 * Created by romab on 10/2/16.
 */
@Repository
public class ChatDao extends AbstractDao {

    public Chat create(Chat chat) {

        return (Chat) super.create(chat);
    }

    public Chat read(int id) {
        return (Chat) super.read(Chat.class, id);
    }

    public List<Chat> getUserChats(User user) {

        List<Chat> result;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Chat.class);

        Criterion user1 = Restrictions.eq("user1", user);
        Criterion user2 = Restrictions.eq("user2", user);

        result = (List<Chat>) criteria.add(Restrictions.or(user1, user2)).list();

        session.close();

        // every user with current "user" parameter will be in a second place in chat object
        for (Chat chat : result) {
            if ( !chat.getUser2().getLogin().equals(user.getLogin()) ) {
                User tempUser = chat.getUser1();
                chat.setUser1(chat.getUser2());
                chat.setUser2(tempUser);
            }
        }

        return result;

    }

    public Chat readByUsersIds(int user1Id, int user2Id){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from Chat where " +
                "(user1.id = :user1Id and user2.id = :user2Id) or " +
                "(user1.id = :user2Id and user2.id = :user1Id)");

        q.setInteger("user1Id", user1Id);
        q.setInteger("user2Id", user2Id);
        Chat resultChat = (Chat) q.uniqueResult();

        session.close();

        return resultChat;
    }

    public boolean isExistByUsersId(int user1Id, int user2Id) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from Chat where " +
                "(user1.id = :user1Id and user2.id = :user2Id) or " +
                "(user1.id = :user2Id and user2.id = :user1Id)");

        q.setInteger("user1Id", user1Id);
        q.setInteger("user2Id", user2Id);
        Chat resultChat = (Chat) q.uniqueResult();

        session.close();

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
