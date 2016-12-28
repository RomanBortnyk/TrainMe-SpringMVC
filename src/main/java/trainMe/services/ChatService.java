package trainMe.services;

import org.jboss.logging.annotations.Cause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.ChatDao;
import trainMe.model.Chat;

import java.util.List;

/**
 * Created by romanb on 12/28/16.
 */
@Service
public class ChatService {

    @Autowired
    private ChatDao chatDao;

    public List getUsersChatList(int id) {
        List<Chat> result = chatDao.getUserChats(id);

        return result;
    }

    public List getUsersChatList(String login) {
        List<Chat> result = chatDao.getUserChats(login);

        return result;
    }
}
