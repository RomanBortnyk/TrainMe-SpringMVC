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
//
//    @Autowired
//    UserDao userDao;
    @Autowired
    AvatarDao avatarDao;

    public void update (User user, MultipartFile file) throws IOException{

        Avatar avatar = user.getAvatar();
        avatar.setImage(file.getBytes());
        avatarDao.update(avatar);

    }
}
