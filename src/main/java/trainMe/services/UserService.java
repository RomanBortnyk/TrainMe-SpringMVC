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

    public ArrayList<String> getUsersFullNamesContainsParam(String parameter) {

        if (parameter.length() > 1){

            ArrayList<String> result = new ArrayList<String>();

            List<User> usersList = (List<User>) userDao.readAllFullNamesWithParam(parameter);

            usersList.forEach(user -> result.add(user.getFirstName() +" " +user.getLastName()));

            return result;

        }

        return null;

    }

}
