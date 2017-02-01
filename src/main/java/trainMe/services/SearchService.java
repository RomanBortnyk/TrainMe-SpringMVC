package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.dao.implementation.DisciplineUserLinkDao;
import trainMe.dao.implementation.UserDao;
import trainMe.jsonObjects.SearchRequestObject;
import trainMe.jsonObjects.SearchResponseObject;
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
    @Autowired
    DisciplineDao disciplineDao;

    public ArrayList<SearchResponseObject> search(SearchRequestObject requestObject) {


        if (requestObject.getSearchOption().equals("byFullName")) {
            String fullName[] = requestObject.getSearchString().split(" ");

            List<User> users = userDao.read(fullName[0], fullName[1], requestObject.getUserTypeOption());

            return generateResultList(users);

        } else if (requestObject.getSearchOption().equals("byDiscipline")) {


            List<User> users = discUsrLnkDao.find(requestObject.getUserTypeOption(), disciplineDao.read(requestObject.getSearchString()) );

            return generateResultList(users);
        }else return null;


    }

    public ArrayList<SearchResponseObject> generateResultList (List<User> usersList){

        ArrayList<SearchResponseObject> result = new ArrayList<SearchResponseObject>();

        for (User user: usersList){

            List<DisciplineUserLink> links = discUsrLnkDao.getUsersDisciplineLinks(user);

            SearchResponseObject searchResponseObject = new SearchResponseObject();
            ArrayList<Discipline> disciplines = new ArrayList<Discipline>();

            for (DisciplineUserLink link: links){
                disciplines.add(link.getDiscipline());
            }
            searchResponseObject.setDisciplines(disciplines);
            searchResponseObject.setId(user.getId());
            searchResponseObject.setFirstName(user.getFirstName());
            searchResponseObject.setLastName(user.getLastName());
            searchResponseObject.setUserType(user.getUserType());
            searchResponseObject.setDescription(user.getDescription());

            result.add(searchResponseObject);

        }

        return result;
    }

}
