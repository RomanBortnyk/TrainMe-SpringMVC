package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trainMe.jsonObjects.NewFeedbackRequest;
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

    @RequestMapping(value="add", method = RequestMethod.POST)
    public @ResponseBody NewFeedbackRequest addFeedback(HttpServletRequest request ,
                                            @RequestBody NewFeedbackRequest feedbackRequest) {

        feedbackService.add(feedbackRequest.getDestinationUserId(),
                (User)request.getSession().getAttribute("currentSessionUser"),
                feedbackRequest.getNewFeedbackText());

        return new NewFeedbackRequest();
    }


}
