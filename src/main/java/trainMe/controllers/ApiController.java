package trainMe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import trainMe.model.Chat;
import trainMe.model.Message;
import trainMe.services.*;
import trainMe.jsonObjects.NewFeedbackRequest;
import trainMe.jsonObjects.SearchRequestObject;
import trainMe.jsonObjects.SearchResponseObject;
import trainMe.jsonObjects.TestObject;
import trainMe.model.Feedback;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 11/13/16.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;
    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ChatService chatService;
    @Autowired
    MessageService messageService;


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
        return feedbackService.getFeedbackById(feedbackId);
    }

    @RequestMapping(value = "/feedbacks/{userId}",method= RequestMethod.GET)
    public ArrayList<Feedback> getFeedbacks(@PathVariable("userId") int userId) {
        return feedbackService.getFeedbacksByUserId(userId);
    }


    //discipline api's

    @RequestMapping(value = "/disciplines/{userId}",method= RequestMethod.GET)
    public List getDisciplinesByUserId(@PathVariable("userId") int userId) {
        return disciplineService.getDisciplinesByUserId(userId);
    }

    @RequestMapping(value = "/disciplinesToAdd/{userId}",method= RequestMethod.GET)
    public List getSortedDisciplinesNamesToAdd(@PathVariable("userId") int userId) {
        return disciplineService.getSortedDisciplinesNamesToAdd(userId);
    }


    //chat api's

    @RequestMapping(value = "/chats/byId/{userId}",method= RequestMethod.GET)
    public List getUsersChatsListByUserId(@PathVariable("userId") int userId) {
        return chatService.getUsersChatList(userId);
    }

    @RequestMapping(value = "/chats/byLogin/{userLogin}",method= RequestMethod.GET)
    public List getUsersChatsListByUserLogin(@PathVariable("userLogin") String userLogin) {
        return chatService.getUsersChatList(userLogin);
    }

    // message api's


    @RequestMapping(value = "/messages/{chatId}",method= RequestMethod.GET)
    public List getChatMessages(@PathVariable("chatId") int chatId) {

        return messageService.getChatMessages(chatId);
    }


    // autocomplete api's

    @RequestMapping(value = "/autocomplete/disciplines/{param}",method= RequestMethod.GET)
    public ArrayList<String> getDisciplines(@PathVariable("param") String parameter) {

        return disciplineService.getDisciplinesNamesStartsWithParam(parameter);
    }

    @RequestMapping(value = "/autocomplete/full_names/{param}",method= RequestMethod.GET)
    public ArrayList<String> getUsersFullNames(@PathVariable("param") String parameter) {

        return userService.getUsersFullNamesStartsWithParam(parameter);

    }

    // search api's

    @RequestMapping (value = "/search", method = RequestMethod.POST)
    public @ResponseBody List<SearchResponseObject> search (@RequestBody SearchRequestObject requestObject){

        return searchService.search(requestObject);
    }



}
