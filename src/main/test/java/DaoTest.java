import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import trainMe.config.WebConfig;
import trainMe.dao.implementation.*;
import trainMe.model.Message;
import trainMe.model.User;

/**
 * Created by romab on 11/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
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
    public void userDaoTest(){
        User user  = userDao.read(10);

        assertEquals("vlad",userDao.read("vlad").getLogin());

    }

    @Test
    public void messageDaoTest(){
        Message message  = messageDao.read(256);

        assertEquals("first",message.getText());
        assertEquals(10,message.getChat().getId());
        assertEquals(10,message.getAuthor().getId());

    }

}
