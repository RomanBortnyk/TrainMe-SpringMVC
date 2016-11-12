package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.UserDao;
import trainMe.model.User;

/**
 * Created by romab on 11/12/16.
 */
@Service
public class CheckUsersRegistrationService {

    @Autowired
    private UserDao userDao;

    private User registeredUser;
    private String errorMessage;

    public boolean check(String login, String password){
        if (userDao.isExist(login)){
            User user = userDao.read(login);
            if (userDao.isPasswordCorrect(user,password)){
                registeredUser = user;
                return true;
            }else {
                errorMessage = "password incorrect";
                return false;
            }
        }else {
            errorMessage = "user with login \"" + login + "\" does not exist";
            return false;
        }

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }
}
