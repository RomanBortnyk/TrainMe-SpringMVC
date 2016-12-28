package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Discipline;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by romab on 11/11/16.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DisciplineDao disciplineDao;

    public User readById (int id){
        return userDao.read(id);
    }

    public User readByLogin (String login){

        return userDao.read(login);
    }
    
    public List readAll(){
        return  userDao.readAll();
    }

    public ArrayList<String> getUsersFullNamesStartsWithParam(String parameter) {

        List<Discipline> disciplineList = disciplineDao.readAll();
        ArrayList<String> result = new ArrayList<String>();

        List usersFullNamesList = userDao.readAllFullNames();

        //create list of users  full names
        for (Object listObject: usersFullNamesList){
            Object[] oarray = (Object[]) listObject;
            String[] names = Arrays.asList(oarray).toArray(new String[oarray.length]);

            if ( names[0].toLowerCase().startsWith(parameter.toLowerCase() )
                    || names[1].toLowerCase().startsWith(parameter.toLowerCase() )){
                result.add(names[1]+" "+ names[0]);
            }

        }

        return result;
    }

}
