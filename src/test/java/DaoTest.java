
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import trainMe.aspects.OrchestraConcert;
import trainMe.aspects.Performance;
import trainMe.config.RootConfig;
import trainMe.config.WebConfig;
import trainMe.dao.implementation.*;
import trainMe.model.Message;
import trainMe.model.User;

/**
 * Created by romab on 11/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
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
    public void fullfillDisciplinesTable (){


        System.out.println("context is loaded");
//        File chess = new File("/home/romanb/Desktop/png/black.png");
//        File motorcycleRacing = new File("/home/romanb/Desktop/png/black-1.png");
//        File swimming = new File("/home/romanb/Desktop/png/person-swimming.png");
//        File skiing = new File("/home/romanb/Desktop/png/silhouette.png");
//        File barefooting = new File("/home/romanb/Desktop/png/silhouette-1.png");
//        File gymnastics = new File("/home/romanb/Desktop/png/silhouette-2.png");
//        File archery = new File("/home/romanb/Desktop/png/silhouette-3.png");
//        File climbing = new File("/home/romanb/Desktop/png/silhouette-4.png");
//        File wrestling = new File("/home/romanb/Desktop/png/silhouettes.png");
//        File mma = new File("/home/romanb/Desktop/png/silhouettes-1.png");
//        File soccer = new File("/home/romanb/Desktop/png/soccer-player-running-with-the-ball.png");
//        File skating = new File("/home/romanb/Desktop/png/speed-skiing.png");
//        File skateboarding = new File("/home/romanb/Desktop/png/sport.png");
//        File skydiving = new File("/home/romanb/Desktop/png/sport-1.png");
//        File synchronousSwimming = new File("/home/romanb/Desktop/png/sport-2.png");
//        File kitesurfing = new File("/home/romanb/Desktop/png/sport-3.png");
//        File volleyball = new File("/home/romanb/Desktop/png/volleyball-silhouette.png");
//        File waterPolo = new File("/home/romanb/Desktop/png/waterpolo-player-with-the-balls-in-the-water.png");
//        File weightlifting = new File("/home/romanb/Desktop/png/weightlifting-silhouette.png");
//
//
//        disciplineDao.createFromFile("chess",chess);
//        disciplineDao.createFromFile("motorcycle racing",motorcycleRacing);
//        disciplineDao.createFromFile("swimming",swimming);
//        disciplineDao.createFromFile("kickboxing",skiing);
//        disciplineDao.createFromFile("barefooting",barefooting);
//        disciplineDao.createFromFile("gymnastics",gymnastics);
//        disciplineDao.createFromFile("archery",archery);
//        disciplineDao.createFromFile("climbing",climbing);
//        disciplineDao.createFromFile("wrestling",wrestling);
//        disciplineDao.createFromFile("mma",mma);
//        disciplineDao.createFromFile("soccer",soccer);
//        disciplineDao.createFromFile("skating",skating);
//        disciplineDao.createFromFile("skateboarding",skateboarding);
//        disciplineDao.createFromFile("skydiving",skydiving);
//        disciplineDao.createFromFile("synchronous swimming",synchronousSwimming);
//        disciplineDao.createFromFile("kitesurfing",kitesurfing);
//        disciplineDao.createFromFile("volleyball",volleyball);
//        disciplineDao.createFromFile("water polo",waterPolo);
//        disciplineDao.createFromFile("weightlifting",weightlifting);


    }

//    @Test
//    public void ertt(){
//
//        assertEquals("drin drin", concert.perform());
//
//    }


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
