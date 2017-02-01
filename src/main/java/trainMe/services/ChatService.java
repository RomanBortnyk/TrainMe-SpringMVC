package trainMe.services;

import org.jboss.logging.annotations.Cause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Chat;

import java.util.List;

/**
 * Created by romanb on 12/28/16.
 */
@Service
public class ChatService {

    @Autowired
    private ChatDao chatDao;
    @Autowired
    private UserDao userDao;

    public List getUsersChatList(int userId) {
        List<Chat> result = chatDao.getUserChats(userDao.read(userId));

        return result;
    }

    public List getUsersChatList(String login) {
        List<Chat> result = chatDao.getUserChats(userDao.read(login));

        return result;
    }
}
