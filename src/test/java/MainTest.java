import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.model.Discipline;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;


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
        String describe  = resource.getString("describe");
        System.out.println(describe);

        //comment just for merge



    }
}
