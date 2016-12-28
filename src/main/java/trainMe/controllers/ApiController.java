package trainMe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private MessageService messageService;



    @RequestMapping(value = "/expotential/{status}", method= RequestMethod.GET)
    public User simulateExpotentialBackoff(@PathVariable String status, HttpServletResponse response) {

        User user = new User(
                "firstName",
                "lastName",
                "10/10/2000",
                "login",
                "pass",
                "email",
                "coach"
        );
        user.setId(1);

        if ( status.equals("ok")) return user;
        else if (status.equals("error")){

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }else return null;


    }

    //user api's
    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable ("id") int id) {

         return userService.readById(id);
    }
    
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List getUserById() {

        return userService.readAll();
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
