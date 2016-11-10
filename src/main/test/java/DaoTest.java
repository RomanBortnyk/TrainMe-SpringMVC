import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import trainMe.dao.implementation.*;
import trainMe.model.Message;
import trainMe.model.User;

/**
 * Created by romab on 11/10/16.
 */
public class DaoTest {

    UserDao userDao;
    ChatDao chatDao;
    MessageDao messageDao;
    DisciplineDao disciplineDao;
    DisciplineUserLinkDao disciplineUserLinkDao;
    FeedbackDao feedbackDao;
    AvatarDao avatarDao;


    @Before
    public void initializeDAOs(){

        ApplicationContext ctx = new AnnotationConfigApplicationContext("trainMe.config");

        userDao = new UserDao();
        avatarDao = new AvatarDao();
        chatDao = new ChatDao();
        messageDao = new MessageDao();
        disciplineDao = new DisciplineDao();
        disciplineUserLinkDao = new DisciplineUserLinkDao();
        feedbackDao = new FeedbackDao();
    }

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
