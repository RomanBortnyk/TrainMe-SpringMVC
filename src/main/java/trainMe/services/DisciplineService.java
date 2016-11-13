package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.AfterTransaction;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.dao.implementation.DisciplineUserLinkDao;
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

    public void addDiscipline (User user, String discipline){


        discUsrLnkDao.create(new DisciplineUserLink(user,disciplineDao.read(discipline)));

    }

    public void removeDiscipline (User user, String discipline){

        discUsrLnkDao.delete(discUsrLnkDao.read(user,disciplineDao.read(discipline)));

    }

}
