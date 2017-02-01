package trainMe.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Avatar;
import trainMe.model.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romab on 10/2/16.
 */
@Repository
public class UserDao extends AbstractDao {

    public User create(User user) {
        if (isExist(user)) {
            System.out.println("user with login: " + user.getLogin() + "or with email: " + user.getEmail() + " already exist");
            return null;
        } else {

            super.create(user);

            System.out.println("user with login: " + user.getLogin() + " has been created");
            return user;
        }
    }

    public User read (int id ){

        return (User) super.read(User.class, id);

    }

    public User update(User user) {
        return (User) super.update(user);
    }

    public void updateAvatar (User user, Avatar newAvatar){

        Avatar currentAvatar = user.getAvatar();
        AvatarDao avatarDao = new AvatarDao();

        if (currentAvatar == null){
            user.setAvatar(avatarDao.create(newAvatar));
            update(user);
        }else{

            currentAvatar = avatarDao.read(currentAvatar.getId());
            currentAvatar.setImage(newAvatar.getImage());

            avatarDao.update(currentAvatar);
        }

    }

    public void delete(User user) {
        if (isExist(user)) {
            super.delete(user);
            super.delete(user.getAvatar());
            System.out.println("user with login: "+ user.getLogin()+ " has been deleted");
        } else {
            System.out.println("you try delete user which does not exist");
        }
    }


    public List readAll() {
        return super.readAll(User.class);
    }


    public User read (String login){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);

        User newUser = (User) criteria.add(Restrictions.eq("login",login)).uniqueResult();

        session.close();

        if (newUser != null) return newUser; else return null;

    }

    public List read (String lastName, String firstName, String userType){

        List result ;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);

        Map<String, String> propertyNameValues = new HashMap<>();
        propertyNameValues.put("firstName", firstName);
        propertyNameValues.put("lastName", lastName);

        if ( !userType.equals("all") ){

            propertyNameValues.put("userType", userType);
            result = criteria.add(Restrictions.allEq(propertyNameValues)).list();
            session.close();

            return result;
        }

        result = criteria.add(Restrictions.allEq(propertyNameValues)).list();
        session.close();

        return result;
    }

    public boolean isExist (User user){

        if (user == null) return false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);

        Map<String, String> propertyNameValues = new HashMap<>();
        propertyNameValues.put("login", user.getLogin());
        propertyNameValues.put("email", user.getEmail());

        User newUser = (User)criteria.add(Restrictions.allEq(propertyNameValues)).uniqueResult();

        session.close();

        if (newUser == null) return false; else return true;

    }

    public List readAllFullNames (){

        List result = null;

        try {

            Session session = HibernateUtil.getSessionFactory().openSession();

            Query q = session.createQuery("select firstName, lastName from User");
            result = q.list();

            session.close();

        }catch (HibernateException e){
            e.printStackTrace();
        }

        return result;
    }

}
