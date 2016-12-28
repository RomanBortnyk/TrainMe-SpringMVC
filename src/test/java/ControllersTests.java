import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import trainMe.config.test.WebTestConfiguration;
import trainMe.model.Feedback;
import trainMe.model.User;
import trainMe.services.FeedbackService;
import trainMe.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfiguration.class})
@WebAppConfiguration
public class ControllersTests {

    private  MockMvc mockMvc;

    @Autowired
    private UserService userServiceMock;
    @Autowired
    private FeedbackService feedbackServiceMock;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        Mockito.reset(userServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void feedbackAPIgetByIdTest () throws Exception{

        Feedback feedback = new Feedback();
        feedback.setText("feedback");
        feedback.setId(1);
        User user = new User(
                "firstName",
                "lastName",
                "10/10/2000",
                "login",
                "pass",
                "email",
                "coach"
        );
        user.setId(1);
        feedback.setAuthor(user);


        when(feedbackServiceMock.getFeedbackById(1)).thenReturn(feedback);


        mockMvc.perform(get("/api/feedback/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.text", is("feedback")))
                .andExpect(jsonPath("$.authorId", is(1)))
                .andExpect(jsonPath("$.authorFirstName", is("firstName")));

        verify(feedbackServiceMock, times(1)).getFeedbackById(1);
        verifyNoMoreInteractions(feedbackServiceMock);
    }

    @Test
    public void feedbackAPIgetAllByUserIdTest () throws Exception{

        Feedback feedback = new Feedback();
        feedback.setText("feedback");
        feedback.setId(1);
        User user = new User(
                "firstName",
                "lastName",
                "10/10/2000",
                "login",
                "pass",
                "email",
                "coach"
        );
        user.setId(1);
        feedback.setAuthor(user);

        ArrayList<Feedback>  list = new ArrayList<>();
        list.add(feedback);
        list.add(feedback);

        when(feedbackServiceMock.getFeedbacksByUserId(1)).thenReturn(list);


        mockMvc.perform(get("/api/feedbacks/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].text", is("feedback")))
                .andExpect(jsonPath("$[0].authorId", is(1)))
                .andExpect(jsonPath("$[0].authorFirstName", is("firstName")))

                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].text", is("feedback")))
                .andExpect(jsonPath("$[1].authorId", is(1)))
                .andExpect(jsonPath("$[1].authorFirstName", is("firstName")));

        verify(feedbackServiceMock, times(1)).getFeedbacksByUserId(1);
        verifyNoMoreInteractions(feedbackServiceMock);
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
