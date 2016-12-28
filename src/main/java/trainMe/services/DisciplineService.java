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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

    public List getDisciplinesByUserId(int id) {

        ArrayList<Discipline> result = new ArrayList<Discipline>();
        List disciplinesLinks = discUsrLnkDao.getUsersDisciplineLinks(id);

        for (Object discLink : disciplinesLinks) {
            Discipline discipline = ((DisciplineUserLink) discLink).getDiscipline();
            result.add(discipline);
        }

        return result;

    }

    public List getSortedDisciplinesNamesToAdd(int id) {
        List currentDisciplines = getDisciplinesByUserId(id);
        List allDisciplinesList = disciplineDao.readAll();

        ArrayList<String> result = new ArrayList<String>();

        //create list of disciplines names
        for (Object discipline : allDisciplinesList) {
            int i = 0;
            for (Object currentDiscipline : currentDisciplines) {
                if (((Discipline) discipline).getName().equals(((Discipline) currentDiscipline).getName())) i++;

            }
            if (i == 0) result.add(((Discipline) discipline).getName());
        }
        Collections.sort(result);
        return result;
    }

    public ArrayList<String> getDisciplinesNamesStartsWithParam(String parameter) {

        List<Discipline> disciplineList = disciplineDao.readAll();
        ArrayList<String> result = new ArrayList<String>();

        //create result list
        Iterator it = disciplineList.iterator();
        while (it.hasNext()) {
            Discipline current = (Discipline) it.next();
            if (parameter != null) {
                if (current.getName().startsWith(parameter.toLowerCase())) {
                    result.add(current.getName());
                }
            }
        }

        return result;
    }

}
