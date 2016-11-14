package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.UserDao;
import trainMe.model.User;

/**
 * Created by romab on 11/14/16.
 */
@Service
public class DescriptionService {


    @Autowired
    UserDao userDao;

    public void update(User user, String description){

//        User userToUpdate = user;
//        userToUpdate.setDescription(description);
        user.setDescription(description);
        userDao.update(user);
    }

}
