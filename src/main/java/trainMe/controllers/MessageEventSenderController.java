package trainMe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import trainMe.api.apiModel.MessageApiType;
import trainMe.messenger.NewMessageListener;
import trainMe.messenger.WaitingUsersPool;
import trainMe.model.Message;
import trainMe.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by romab on 11/14/16.
 */
@Controller


public class MessageEventSenderController {

    @Autowired
    WaitingUsersPool usersPool;

    @RequestMapping(value = "/listen", method = RequestMethod.POST)
    @ResponseBody
    public DeferredResult<MessageApiType> listen(HttpServletRequest request)  throws InterruptedException{
        final DeferredResult<MessageApiType> result = new DeferredResult<MessageApiType>((long)0);

        User currentUser = (User) request.getSession().getAttribute("currentSessionUser");
        usersPool.addWaitingUser(currentUser);

        NewMessageListener messageListener = new NewMessageListener();

        usersPool.registerObserver(messageListener);

        messageListener.justWait(currentUser);

        result.setResult(messageListener.getCurrentMessage());

        usersPool.removeWaitingUser(currentUser);
        usersPool.removeObserver(messageListener);

        return result;
    }


}
