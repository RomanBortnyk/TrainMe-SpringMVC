package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import trainMe.dao.implementation.AvatarDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Avatar;
import trainMe.model.User;

import java.io.IOException;

/**
 * Created by romab on 11/14/16.
 */
@Service
public class AvatarService {

    @Autowired
    UserDao userDao;
    @Autowired
    AvatarDao avatarDao;

    public void update (String login, MultipartFile file) throws IOException{


        User user = userDao.read(login);

        if(user.getAvatar() == null){
            Avatar newAvatar = avatarDao.create(new Avatar(file.getBytes()));
            user.setAvatar(newAvatar);
            userDao.update(user);
        }else {
            Avatar avatarToUpdate = user.getAvatar();
            avatarToUpdate.setImage(file.getBytes());
            avatarDao.update(avatarToUpdate);
        }

    }
}
