package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.UserDao;
import trainMe.model.User;

import java.util.ArrayList;

/**
 * Created by romab on 11/11/16.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    private String errorMessage;

    public User readById (int id){
        return userDao.read(id);
    }

    public User readByLogin (String login){

        return userDao.read(login);
    }
}
