package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import trainMe.jsonObjects.ReceivedMessageJson;

import java.security.Principal;

/**
 * Created by romanb on 12/8/16.
 */
@Service
public class MessengerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessageToUser(ReceivedMessageJson message, String username) {

        messagingTemplate.convertAndSendToUser(username,"/queue/messages-updates",message);

    }


}
