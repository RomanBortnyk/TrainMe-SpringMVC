import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import trainMe.HibernateUserRepository;
import trainMe.model.Feedback;
import trainMe.model.User;

import javax.jws.soap.SOAPBinding;

public class HomeControllerTest {


  @Test
  public void HibernateUserRepositoryTest(){

    ApplicationContext ctx =
            new AnnotationConfigApplicationContext("trainMe.config");

    HibernateUserRepository hfr = (HibernateUserRepository) ctx.getBean("hibUsrRepo");

    User user = hfr.read(10);
    assertEquals ("Vlad", user.getFirstName());

  }

}
