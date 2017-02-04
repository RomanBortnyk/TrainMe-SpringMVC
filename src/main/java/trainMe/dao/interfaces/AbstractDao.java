package trainMe.dao.interfaces;

import trainMe.model.Entity;
import org.hibernate.Query;
import org.hibernate.Session;
import trainMe.hibernate.HibernateUtil;
import java.io.Serializable;
import java.util.List;

/**
 * Created by romab on 10/2/16.
 */
public abstract class AbstractDao implements GenericDao,Serializable{

    public Entity create (Entity entity){

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
                if(session.isOpen()){
//                    System.out.println("Closing session");
                    session.close();
                }
        }

        return entity;
    }

    public Entity read(Class clazz, int id) {

        Entity entity = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entity = (Entity) session.get(clazz, id);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
//                System.out.println("Closing session");
                session.close();
            }
        }

        return entity;

    }

    public Entity update(Entity entity) {

        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
//                System.out.println("Closing session");
                session.close();
            }
        }

        return entity;

    }

    public void delete(Entity entity) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
//                System.out.println("Closing session");
                session.close();
            }
        }

    }

    public List readAll(Class clazz) {
        List items = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery("from " + clazz.getName());
            items = query.list();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
//                System.out.println("Closing session");
                session.close();
            }
        }

        return items;
    }
}
