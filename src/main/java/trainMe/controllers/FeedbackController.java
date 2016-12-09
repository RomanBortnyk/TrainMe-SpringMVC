package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trainMe.dao.implementation.UserDao;
import trainMe.jsonObjects.NewFeedbackRequest;
import trainMe.model.Feedback;
import trainMe.model.User;
import trainMe.services.FeedbackService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by romab on 11/14/16.
 */
@Controller
@RequestMapping("/feedback/")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;
    @Autowired
    UserDao userDao;

    @RequestMapping(value="add", method = RequestMethod.POST)
    public @ResponseBody Feedback addFeedback(@RequestBody NewFeedbackRequest newFeedbackRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = authentication == null ? null : userDao.read( authentication.getName() );

        return feedbackService.add(newFeedbackRequest.getDestinationUserId(),
                currentUser,
                newFeedbackRequest.getNewFeedbackText());
    }


}
