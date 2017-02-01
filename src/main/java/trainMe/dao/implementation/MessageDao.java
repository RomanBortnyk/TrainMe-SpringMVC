package trainMe.dao.implementation;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Chat;
import trainMe.model.Message;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 10/2/16.
 */
@Repository
public class MessageDao extends AbstractDao {

    public Message create(Message message) {
        return (Message)super.create(message);
    }

    public Message update(Message message) {
        return (Message) super.update(message);
    }

    public void delete(Message message) {
        super.delete(message);
    }

    public Message read(int id) {
        return (Message) super.read(Message.class, id);
    }

    public List readAll() {
        return super.readAll(Message.class);
    }

    public List getChatMessages(Chat chat){

        List result ;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Message.class);

        result = criteria.add(Restrictions.eq("chat", chat)).list();

        session.close();

        return result;

    }
}
