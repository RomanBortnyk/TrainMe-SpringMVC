package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import trainMe.api.apiModel.MessageApiType;
import trainMe.jsonObjects.ReceivedMessageJson;
import trainMe.services.MessengerService;

import java.security.Principal;

/**
 * Created by romanb on 12/8/16.
 */
@Controller
public class MessageController {

    @Autowired
    MessengerService messengerService;


    @MessageMapping("/send")
//    @SendToUser("/queue/messages-updates")
    public void handle (@Payload ReceivedMessageJson msg, Principal principal){


        messengerService.sendMessageToUser(msg, principal.getName());

        int i =0;



//        messagingTemplate.convertAndSendToUser("vlad","/queue/messages-updates",
//                new MessageApiType(1,"sdfdsf","sdfdsf","sdfdsf"));


    }


}
