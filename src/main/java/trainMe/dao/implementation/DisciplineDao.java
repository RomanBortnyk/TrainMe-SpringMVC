package trainMe.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.Discipline;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by romab on 9/23/16.
 */
@Repository
public class DisciplineDao extends AbstractDao {


    public Discipline create(Discipline obj) {
        return (Discipline) super.create(obj);
    }

    public Discipline update(Discipline obj) {
        return (Discipline) super.update(obj);
    }

    public void delete(Discipline obj) {
        super.delete(obj);
    }

    public Discipline read(int id) {
        return (Discipline) super.read(Discipline.class, id);
    }

    public List readAll() {
        return super.readAll(Discipline.class);
    }

    public Discipline createFromFile(String name, File image){

            byte[] bFile = new byte[(int) image.length()];

            try {
                FileInputStream fileInputStream = new FileInputStream(image);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Discipline discipline = new Discipline();
            discipline.setName(name);
            discipline.setIcon(bFile);

            return (Discipline) super.create(discipline);


    }

    public Discipline read (String name){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Discipline.class);

        Discipline newDiscipline = (Discipline)criteria.add(Restrictions.eq("name", name)).uniqueResult();

        session.close();

        return newDiscipline;
    }

    /**
     * Read all disciplines if their names contains param value
     * @param param - discipline name can contains this string
     */
    public List readAllWithParamMatch (String param){

        List result;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Discipline.class);

        Criterion firstName = Restrictions.like("firstName", param, MatchMode.ANYWHERE);

        result = criteria.add(Restrictions.like("name", param, MatchMode.ANYWHERE)).list();

        return result;


    }

}
