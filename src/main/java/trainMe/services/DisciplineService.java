package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.AfterTransaction;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.dao.implementation.DisciplineUserLinkDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Discipline;
import trainMe.model.DisciplineUserLink;
import trainMe.model.User;

/**
 * Created by romab on 11/14/16.
 */
@Service
public class DisciplineService {

    @Autowired
    DisciplineUserLinkDao discUsrLnkDao;
    @Autowired
    DisciplineDao disciplineDao;
    @Autowired
    UserDao userDao;

    public void addDiscipline (String login, String discipline){


        discUsrLnkDao.create(new DisciplineUserLink(userDao.read(login),disciplineDao.read(discipline)));

    }

    public void removeDiscipline (String login, String discipline){

        discUsrLnkDao.delete(discUsrLnkDao.read(userDao.read(login),disciplineDao.read(discipline)));

    }

    public Discipline readById (int id){
        return disciplineDao.read(id);
    }

}
