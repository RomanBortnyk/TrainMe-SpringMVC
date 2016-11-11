package trainMe.dao.implementation;


import messenger.SmallerMessage;
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

    public List<SmallerMessage> getChatMessages(int chatId){
        List<Message> result;
        List<SmallerMessage> resultWithSmallerMessages = new ArrayList<SmallerMessage>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Message where chat.id = :chatId");
        q.setInteger("chatId",chatId);

        result = q.list();

        session.getTransaction().commit();

        for (Message message: result){
            resultWithSmallerMessages.add(new SmallerMessage(message.getAuthor().getId(),message.getText(),
                    message.getAuthor().getFirstName(),message.getAuthor().getLastName()));
        }

        return resultWithSmallerMessages;

    }
}
