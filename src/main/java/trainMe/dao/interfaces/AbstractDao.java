package trainMe.dao.interfaces;

import trainMe.model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import trainMe.hibernate.HibernateUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by romab on 10/2/16.
 */
public abstract class AbstractDao implements GenericDao,Serializable{

    public Item create (Item item){

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
                if(session.isOpen()){
                    System.out.println("Closing session");
                    session.close();
                }
        }

        return item;
    }

    public Item read(Class clazz, int id) {

        Item item = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            item = (Item) session.get(clazz, id);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
                System.out.println("Closing session");
                session.close();
            }
        }

        return item;

    }

    public Item update(Item item) {

        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
                System.out.println("Closing session");
                session.close();
            }
        }

        return item;

    }

    public void delete(Item item) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
                System.out.println("Closing session");
                session.close();
            }
        }

    }

    public List readAll(Class clazz) {
        List items = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName());
            items = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session.isOpen()){
                System.out.println("Closing session");
                session.close();
            }
        }

        return items;
    }
}
