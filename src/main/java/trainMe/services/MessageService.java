package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.MessageDao;
import trainMe.model.Message;

import java.util.List;

/**
 * Created by romanb on 12/28/16.
 */
@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public List getChatMessages(int id) {

        return messageDao.getChatMessages(id);

    }
}
