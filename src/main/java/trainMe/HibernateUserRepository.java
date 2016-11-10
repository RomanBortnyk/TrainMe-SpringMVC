package trainMe;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainMe.model.Feedback;
import trainMe.model.User;


import java.io.Serializable;

/**
 * Created by romab on 11/10/16.
 */
@Repository
@Transactional
public class HibernateUserRepository {


    @Autowired
    SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User read(int id) {
        return (User) currentSession().get(User.class, id);
    }

}
