import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import trainMe.config.app.WebConfig;
import trainMe.config.test.WebTestConfiguration;
import trainMe.model.User;
import trainMe.services.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfiguration.class})
@WebAppConfiguration
public class ControllersTests {

    private  MockMvc mockMvc;

    @Autowired
    private UserService userServiceMock;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        Mockito.reset(userServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }



    @Test
    public void profileControllerTest() throws Exception  {

        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setLogin("Test");
        user.setUserType("test");
        user.setDescription("description");
        user.setBirthdayDate("date");
        user.setEmail("email");
        user.setPassword("password");

        when(userServiceMock.readById(1)).thenReturn(user);


        mockMvc.perform(get("/profile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(forwardedUrl("/WEB-INF/views/profile.jsp"))
                .andExpect(model().attribute("user", user));

        verify(userServiceMock, times(1)).readById(1);
        verifyNoMoreInteractions(userServiceMock);

    }

    @Test
    public void mainControllerTest() throws Exception{

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"));


        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(forwardedUrl("/WEB-INF/views/registration.jsp"));

        mockMvc.perform(get("/conversations"))
                .andExpect(status().isOk())
                .andExpect(view().name("conversations"))
                .andExpect(forwardedUrl("/WEB-INF/views/conversations.jsp"));

        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchPage"))
                .andExpect(forwardedUrl("/WEB-INF/views/searchPage.jsp"));


    }


}
