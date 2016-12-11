package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.DisciplineUserLinkDao;
import trainMe.dao.implementation.UserDao;
import trainMe.jsonObjects.SearchRequestObject;
import trainMe.jsonObjects.UserSearchResponse;
import trainMe.model.Discipline;
import trainMe.model.DisciplineUserLink;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 11/15/16.
 */
@Service
public class SearchService {

    @Autowired
    UserDao userDao;
    @Autowired
    DisciplineUserLinkDao discUsrLnkDao;

    public ArrayList<UserSearchResponse> search(SearchRequestObject requestObject) {


        if (requestObject.getSearchOption().equals("byFullName")) {
            String fullName[] = requestObject.getSearchString().split(" ");

            List<User> users = userDao.read(fullName[0], fullName[1], requestObject.getUserTypeOption());

            return generateResultList(users);

        } else if (requestObject.getSearchOption().equals("byDiscipline")) {


            List<User> users = discUsrLnkDao.find(requestObject.getUserTypeOption(), requestObject.getSearchString());

            return generateResultList(users);
        }else return null;


    }

    public ArrayList<UserSearchResponse> generateResultList (List<User> usersList){

        ArrayList<UserSearchResponse> result = new ArrayList<UserSearchResponse>();

        for (User user: usersList){

            List<DisciplineUserLink> links = discUsrLnkDao.getUsersDisciplineLinks(user.getId());

            UserSearchResponse userSearchResponse = new UserSearchResponse();
            ArrayList<Discipline> disciplines = new ArrayList<Discipline>();

            for (DisciplineUserLink link: links){
                disciplines.add(link.getDiscipline());
            }
            userSearchResponse.setDisciplines(disciplines);
            userSearchResponse.setId(user.getId());
            userSearchResponse.setFirstName(user.getFirstName());
            userSearchResponse.setLastName(user.getLastName());
            userSearchResponse.setUserType(user.getUserType());
            userSearchResponse.setDescription(user.getDescription());

            result.add(userSearchResponse);

        }

        return result;
    }

}
