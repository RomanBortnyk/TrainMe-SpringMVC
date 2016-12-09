import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import trainMe.config.WebConfig;
import trainMe.controllers.HomeController;
import trainMe.jsonObjects.TestObject;
import trainMe.model.User;
import static org.junit.Assert.*;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class ControllersTest {



    @Test
    public void test()
            throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestObject dtoObject = new TestObject(1,"fsdf","sdfsd",1232423);

        String dtoAsString = mapper.writeValueAsString(dtoObject);
        System.out.println(dtoAsString);

        assertTrue("sdfsdf",true);
    }




}
