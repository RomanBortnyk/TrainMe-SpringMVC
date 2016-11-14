package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trainMe.dao.implementation.ChatDao;
import trainMe.jsonObjects.NewFeedbackRequest;
import trainMe.jsonObjects.ReceivedMessageJson;
import trainMe.messenger.WaitingUsersPool;
import trainMe.model.Message;
import trainMe.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by romab on 11/14/16.
 */
@Controller
@RequestMapping("/sendEvent/")
public class MessageEventReceiverController {

    @Autowired
    ChatDao chatDao;
    @Autowired
    WaitingUsersPool usersPool;

    @RequestMapping(value = "message", method = RequestMethod.POST)
    public @ResponseBody
    ReceivedMessageJson receiveMessage(HttpServletRequest request ,
                                    @RequestBody ReceivedMessageJson receivedMessage) {

        Message messageToAdd = new Message();

        messageToAdd.setAuthor((User)request.getSession().getAttribute("currentSessionUser"));
        messageToAdd.setText(receivedMessage.getMessageText());
        messageToAdd.setChat(chatDao.read(receivedMessage.getChatId()));

        Date today = new java.util.Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        messageToAdd.setCreatedAt(timestamp);

        usersPool.addMessage(messageToAdd);

        return new ReceivedMessageJson();
    }

//
//
//    @RequestMapping(value = "doesChatExist", method = RequestMethod.POST)


}
