package trainMe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import trainMe.services.RestAPIService;
import trainMe.jsonObjects.NewFeedbackRequest;
import trainMe.jsonObjects.SearchRequestObject;
import trainMe.jsonObjects.SearchResponseObject;
import trainMe.jsonObjects.TestObject;
import trainMe.model.Feedback;
import trainMe.model.User;
import trainMe.services.FeedbackService;
import trainMe.services.SearchService;
import trainMe.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 11/13/16.
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    RestAPIService restAPIService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;


    @RequestMapping(value = "/test",method= RequestMethod.GET)
    public TestObject getTest() {

        TestObject test = new TestObject(3,"name","alias",213214);

        return test;
    }

    //feedback api's

    @RequestMapping(value="/feedback", method = RequestMethod.POST)
    public @ResponseBody Feedback addFeedback(@RequestBody NewFeedbackRequest newFeedbackRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = authentication == null ? null : userService.readByLogin( authentication.getName() );

        return feedbackService.add(newFeedbackRequest.getDestinationUserId(),
                currentUser,
                newFeedbackRequest.getNewFeedbackText());

    }

    @RequestMapping(value = "/feedback/{feedbackId}",method= RequestMethod.GET)
    public Feedback getFeedback(@PathVariable("feedbackId") int feedbackId) {
        return restAPIService.getFeedbackById(feedbackId);
    }

    @RequestMapping(value = "/feedbacks/{userId}",method= RequestMethod.GET)
    public ArrayList<Feedback> getFeedbacks(@PathVariable("userId") int userId) {
        return restAPIService.getFeedbacksByUserId(userId);
    }


    //discipline api's

    @RequestMapping(value = "/disciplines/{userId}",method= RequestMethod.GET)
    public List getDisciplinesByUserId(@PathVariable("userId") int userId) {
        return restAPIService.getDisciplinesByUserId(userId);
    }

    @RequestMapping(value = "/disciplinesToAdd/{userId}",method= RequestMethod.GET)
    public List getSortedDisciplinesNamesToAdd(@PathVariable("userId") int userId) {
        return restAPIService.getSortedDisciplinesNamesToAdd(userId);
    }


    //chat api's

    @RequestMapping(value = "/chats/byId/{userId}",method= RequestMethod.GET)
    public List getUsersChatsListByUserId(@PathVariable("userId") int userId) {
        return restAPIService.getUsersChatList(userId);
    }

    @RequestMapping(value = "/chats/byLogin/{userLogin}",method= RequestMethod.GET)
    public List getUsersChatsListByUserLogin(@PathVariable("userLogin") String userLogin) {
        return restAPIService.getUsersChatList(userLogin);
    }

    // message api's


    @RequestMapping(value = "/messages/{chatId}",method= RequestMethod.GET)
    public List getChatMessages(@PathVariable("chatId") int chatId) {

        return restAPIService.getChatMessages(chatId);
    }


    // autocomplete api's

    @RequestMapping(value = "/autocomplete/disciplines/{param}",method= RequestMethod.GET)
    public ArrayList<String> getDisciplines(@PathVariable("param") String parameter) {

        return restAPIService.getDisciplinesNamesStartsWithParam(parameter);
    }

    @RequestMapping(value = "/autocomplete/full_names/{param}",method= RequestMethod.GET)
    public ArrayList<String> getUsersFullNames(@PathVariable("param") String parameter) {

        return restAPIService.getUsersFullNamesStartsWithParam(parameter);

    }

    // search api's

    @RequestMapping (value = "/search", method = RequestMethod.POST)
    public @ResponseBody List<SearchResponseObject> search (@RequestBody SearchRequestObject requestObject){

        return searchService.search(requestObject);
    }




}
