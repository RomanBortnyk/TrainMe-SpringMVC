package trainMe.dao.implementation;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Message;
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

    public List <Message> getChatMessages(int chatId){

//        List<Message> result = new ArrayList<Message>();
        List<Message> result;

        Session session = HibernateUtil.getSessionFactory().openSession();


        Query q = session.createQuery("from Message where chat.id = :chatId");
        q.setInteger("chatId",chatId);

        result = q.list();

        session.close();


        return result;

    }
}
