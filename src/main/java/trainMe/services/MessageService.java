package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.MessageDao;
import trainMe.model.Chat;
import trainMe.model.Message;

import java.util.List;

/**
 * Created by romanb on 12/28/16.
 */
@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private ChatDao chatDao;

    public List getChatMessages(int chatId) {

        return messageDao.getChatMessages(chatDao.read(chatId));

    }
}
