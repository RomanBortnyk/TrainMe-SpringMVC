import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.model.Discipline;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;


/**
 * Created by romab on 11/10/16.
 */
public class MainTest {


    public static void main(String[] args) {

        Discipline discipline1 = new Discipline("name1", null);
        discipline1.setId(1);

        Discipline discipline2 = new Discipline("name1", null);
        discipline2.setId(1);

        boolean equals = discipline1.equals(discipline2);

        System.out.println(equals);

        ArrayList<Discipline> list1 = new ArrayList<>();
        ArrayList<Discipline> list2 = new ArrayList<>();

        list1.add(discipline1);
        list2.add(discipline2);

        boolean contains = list1.contains(discipline2);

        System.out.println(contains);

    }
}
