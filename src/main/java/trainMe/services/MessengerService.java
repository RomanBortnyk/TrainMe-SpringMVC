package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.MessageDao;
import trainMe.dao.implementation.UserDao;
import trainMe.jsonObjects.ReceivedMessageJson;
import trainMe.model.Message;
import trainMe.model.User;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by romanb on 12/8/16.
 */
@Service
public class MessengerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    UserDao userDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    ChatDao chatDao;

    public void sendMessageToUser(ReceivedMessageJson incomingMessage, String authorUsername) {

        User author = userDao.read(authorUsername);

        Message outcomingMessage = new Message();

        outcomingMessage.setText(incomingMessage.getMessageText());

        outcomingMessage.setChat(chatDao.read(incomingMessage.getChatId()));

        outcomingMessage.setAuthor(author);

        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        outcomingMessage.setCreatedAt(timestamp);


        messageDao.create(outcomingMessage);

        if (! incomingMessage.getDestinationUserLogin().equals(authorUsername)){

            messagingTemplate.convertAndSendToUser(authorUsername,
                    "/queue/messages-updates",
                    outcomingMessage);
        }

        messagingTemplate.convertAndSendToUser(incomingMessage.getDestinationUserLogin(),
                                                "/queue/messages-updates",
                                                outcomingMessage);




    }


}
