
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import trainMe.config.DAOTestConfig;

import trainMe.dao.implementation.*;
import trainMe.model.Message;
import trainMe.model.User;

import static org.junit.Assert.*;

/**
 * Created by romab on 11/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DAOTestConfig.class)
public class DaoTest {

    @Autowired
    UserDao userDao;
    @Autowired
    ChatDao chatDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    DisciplineDao disciplineDao;
    @Autowired
    DisciplineUserLinkDao disciplineUserLinkDao;
    @Autowired
    FeedbackDao feedbackDao;
    @Autowired
    AvatarDao avatarDao;

    @Test
    public void userDaoTest (){
        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setLogin("Test");
        user.setUserType("test");
//        user.setDescription("description");
        user.setBirthdayDate("date");
        user.setEmail("email");
        user.setPassword("password");


        userDao.create(user);

        User userFromDB = userDao.read(1);

        assertEquals(user.getId(), userFromDB.getId());
        assertNull( userFromDB.getAvatar());
        assertNull( userFromDB.getDescription());

        assertEquals(user.getBirthdayDate(),userFromDB.getBirthdayDate());

        userFromDB = userDao.read("Test");
        assertEquals(user.getLogin(), userFromDB.getLogin());

        assertTrue(userDao.isExist(user));

        assertFalse(userDao.isPasswordCorrect(user,"password1"));
        userDao.delete(user);

        assertNull(userDao.read(1));




    }


    @Test
    public void messageDaoTest(){

    }

}
