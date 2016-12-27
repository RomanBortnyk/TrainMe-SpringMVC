package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.MessageDao;
import trainMe.dao.implementation.UserDao;
import trainMe.jsonObjects.ReceivedMessageJson;
import trainMe.model.Chat;
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


        outcomingMessage.setCreatedAt(generateCurrentTimestamp());


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

    public String checkDoesChatExistAndSendToUser(ReceivedMessageJson incoming, String authorUsername){

        User author = userDao.read(authorUsername);

        //chatId here is destination user id
        User destinationUser = userDao.read(incoming.getChatId());

        Message outcomingMessage = new Message();

        outcomingMessage.setAuthor(author);
        outcomingMessage.setText(incoming.getMessageText());
        outcomingMessage.setCreatedAt(generateCurrentTimestamp());


        if ( chatDao.isExistByUsersId(author.getId(),destinationUser.getId()) ){
            outcomingMessage.setChat(chatDao.readByUsersIds(author.getId() , destinationUser.getId()));

        }else {

            Chat chat = new Chat(author,destinationUser);
            outcomingMessage.setChat(chatDao.create(chat));

        }

        messageDao.create(outcomingMessage);

        if (! destinationUser.getLogin().equals(authorUsername)){

            messagingTemplate.convertAndSendToUser(authorUsername,
                    "/queue/messages-updates",
                    outcomingMessage);
        }


        messagingTemplate.convertAndSendToUser(destinationUser.getLogin(),
                                                "/queue/messages-updates",
                                                outcomingMessage);

        return "success";

    }

    public Timestamp generateCurrentTimestamp(){
        Date today = new Date();
        return new Timestamp(today.getTime());
    }

}
