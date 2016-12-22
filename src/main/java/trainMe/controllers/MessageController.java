package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
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

    public void handle (@Payload ReceivedMessageJson incoming, Principal principal){

        messengerService.sendMessageToUser(incoming, principal.getName());

    }


}
