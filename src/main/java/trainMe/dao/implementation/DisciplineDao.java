package trainMe.dao.implementation;//package dao.implementation;


import org.hibernate.Query;
import org.hibernate.Session;
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

        Query q = session.createQuery("from Discipline where name = :name");
        q.setString("name", name);

        Discipline newDiscipline = (Discipline)q.uniqueResult();

        session.close();

        return newDiscipline;
    }

}
