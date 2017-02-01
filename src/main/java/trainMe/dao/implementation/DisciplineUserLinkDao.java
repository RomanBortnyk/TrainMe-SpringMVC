package trainMe.dao.implementation;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Discipline;
import trainMe.model.DisciplineUserLink;
import trainMe.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

/**
 * Created by romab on 9/23/16.
 */
@Repository
public class DisciplineUserLinkDao extends AbstractDao {


    public DisciplineUserLink create(DisciplineUserLink link) {

        return (DisciplineUserLink) super.create(link);

    }

    public DisciplineUserLink update(DisciplineUserLink link) {
        return (DisciplineUserLink) super.update(link);
    }

    public void delete(DisciplineUserLink link) {
        super.delete(link);
    }

    public DisciplineUserLink read(int id) {
        return (DisciplineUserLink) super.read(DisciplineUserLink.class, id);
    }

    public List readAll() {
        return super.readAll(DisciplineUserLink.class);
    }

    public List getUsersDisciplineLinks(int userId) {

        List result;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(DisciplineUserLink.class);

        result = criteria.add(Restrictions.eq("userId", userId)).list();

        session.close();

        return result;

    }

    public DisciplineUserLink read(User user, Discipline discipline) {

        DisciplineUserLink result;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(DisciplineUserLink.class);

        Map<String, Integer> propertyNameValues = new HashMap<>();
        propertyNameValues.put("userId", user.getId());
        propertyNameValues.put("disciplineId", discipline.getId());

        result = (DisciplineUserLink) criteria.add(Restrictions.allEq(propertyNameValues)).uniqueResult();

        session.close();

        return result;
    }

    public List find(String userType, String disciplineName, DisciplineDao disciplineDao) {

        List result = new ArrayList();
        List<DisciplineUserLink> tempList;

        Discipline discipline = disciplineDao.read(disciplineName);

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(DisciplineUserLink.class);

        tempList = (List<DisciplineUserLink>) criteria.add(Restrictions.eq("disciplineId", discipline.getId())).list();

        if (!userType.equals("all")) {

            //add to result only user with "userType" parameter type
            for (DisciplineUserLink discUsrLink : tempList) {
                User currentUser = discUsrLink.getUser();
                if (currentUser.getUserType().equals(userType))
                    result.add(currentUser);
            }

        }

        // add to result all type users
        for (DisciplineUserLink discUsrLink : tempList) {
            result.add(discUsrLink.getUser());
        }


        return result;
    }

}
