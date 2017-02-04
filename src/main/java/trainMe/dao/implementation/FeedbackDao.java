package trainMe.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Feedback;
import trainMe.model.User;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romab on 9/21/16.
 */
@Repository
public class FeedbackDao extends AbstractDao {

    public Feedback create(Feedback feedback) {
        if (feedback != null ) {
            super.create(feedback);
            System.out.println("feedback has been created");
            return feedback;
        } else {
            System.out.println("feedback already exist");
            return null;
        }

    }

    public Feedback update(Feedback feedback) {
        if (feedback != null && isExist(feedback)) {
            super.update(feedback);
            System.out.println("feedback has been updated");
            return feedback;
        } else {
            System.out.println("feedback does not  exist");
            return null;
        }
    }

    public void delete(Feedback feedback) {
        if (isExist(feedback)) {
            super.delete(feedback);
            System.out.println("feedback has been deleted");
        } else {
            System.out.println("you try delete feedback which does not exist");
        }

    }

    public Feedback read(int id) {
        return (Feedback) super.read(Feedback.class, id);
    }

    public List readAll() {
        return super.readAll(Feedback.class);
    }

    public boolean isExist(Feedback feedback) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Feedback.class);

        Map<String, Integer> propertyNameValues = new HashMap<>();
        propertyNameValues.put("authorId", feedback.getAuthor().getId());
        propertyNameValues.put("userId", feedback.getUser().getId());

        Feedback newFeedback = (Feedback) criteria.add(Restrictions.allEq(propertyNameValues)).uniqueResult();

        session.close();

        if (newFeedback == null) return false; else return true;
    }

    public List getUsersFeedbacks (User user){

        List result;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Feedback.class);

        result = criteria.add(Restrictions.eq("user", user)).list();

        session.close();

        return result;
    }
}
