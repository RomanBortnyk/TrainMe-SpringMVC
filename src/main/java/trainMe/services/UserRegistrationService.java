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
public class UserRegistrationService {

    @Autowired
    UserDao userDao;

    private String errorMessage;

    public boolean register(User newUser){

       if (userDao.isExist(newUser)){
           errorMessage = "User with login \""+newUser.getLogin()+"\" or email \"" + newUser.getEmail()+ "\" already exists";
           return false;
       }else {
           userDao.create(newUser);
           return true;
       }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
