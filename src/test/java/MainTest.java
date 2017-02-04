import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.model.Discipline;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.function.Consumer;


/**
 * Created by romab on 11/10/16.
 */
public class MainTest {


    public static void main(String[] args) {

        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());

        Date date = new Date(timestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        System.out.println(sdf.format(date));

        ResourceBundle resource = ResourceBundle.getBundle("project");

        String describe = resource.getString("describe");

        ArrayList<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        list.forEach(element -> {
            System.out.println(element);
            System.out.println(1+1);
        });

    }
}
