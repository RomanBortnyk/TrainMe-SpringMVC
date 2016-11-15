package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trainMe.api.apiModel.DisciplineApiType;
import trainMe.jsonObjects.SearchRequestObject;
import trainMe.jsonObjects.UserSearchResponse;
import trainMe.services.SearchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 11/15/16.
 */
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping (value = "/searchRequest", method = RequestMethod.POST)
    public @ResponseBody List<UserSearchResponse> search (@RequestBody SearchRequestObject requestObject){

        return searchService.search(requestObject);
    }

//    @RequestMapping(value = "/users",method= RequestMethod.GET)
//    public List getUsers() {
//
//        ArrayList<UserSearchResponse> result = new ArrayList<UserSearchResponse>();
//
//        ArrayList<DisciplineApiType> disc = new ArrayList<DisciplineApiType>();
//        disc.add(new DisciplineApiType(1,"test"));
//        disc.add(new DisciplineApiType(2,"test"));
//
//        UserSearchResponse userSearchResponse = new UserSearchResponse(1,"TEST");
//        userSearchResponse.setDisciplines(disc);
//
//        UserSearchResponse userSearchResponse1 = new UserSearchResponse(2,"TEST2");
//        userSearchResponse1.setDisciplines(disc);
//
//        result.add(userSearchResponse);
//        result.add(userSearchResponse1);
//
//
//        return result;
//    }





}
