package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainMe.api.RestAPI;
import trainMe.api.apiModel.DisciplineApiType;
import trainMe.api.apiModel.FeedbackApiType;
import trainMe.jsonObjects.UserSearchResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 11/13/16.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private RestAPI restAPI;

    @RequestMapping(value = "/feedbacks/{userId}",method= RequestMethod.GET)
    public ArrayList<FeedbackApiType> getFeedbacks(@PathVariable("userId") int userId) {
        return restAPI.getFeedbacksByUserId(userId);
    }

    @RequestMapping(value = "/disciplines/{userId}",method= RequestMethod.GET)
    public List getDisciplinesByUserId(@PathVariable("userId") int userId) {
        return restAPI.getDisciplinesByUserId(userId);
    }

    @RequestMapping(value = "/disciplinesToAdd/{userId}",method= RequestMethod.GET)
    public List getSortedDisciplinesNamesToAdd(@PathVariable("userId") int userId) {
        return restAPI.getSortedDisciplinesNamesToAdd(userId);
    }

    @RequestMapping(value = "/chats/byId/{userId}",method= RequestMethod.GET)
    public List getUsersChatsListByUserId(@PathVariable("userId") int userId) {
        return restAPI.getUsersChatsList(userId);
    }

    @RequestMapping(value = "/chats/byLogin/{userLogin}",method= RequestMethod.GET)
    public List getUsersChatsListByUserLogin(@PathVariable("userLogin") String userLogin) {
        return restAPI.getUsersChatsList(userLogin);
    }

    @RequestMapping(value = "/messages/{chatId}",method= RequestMethod.GET)
    public List getChatMessages(@PathVariable("chatId") int chatId) {
        return restAPI.getChatMessages(chatId);
    }



    @RequestMapping(value = "/autocomplete/disciplines/{param}",method= RequestMethod.GET)
    public ArrayList<String> getDisciplines(@PathVariable("param") String parameter) {

        return restAPI.getDisciplinesNamesStartsWithParam(parameter);
    }

    @RequestMapping(value = "/autocomplete/full_names/{param}",method= RequestMethod.GET)
    public ArrayList<String> getUsersFullNames(@PathVariable("param") String parameter) {

        return restAPI.getUsersFullNamesStartsWithParam(parameter);

    }


}
