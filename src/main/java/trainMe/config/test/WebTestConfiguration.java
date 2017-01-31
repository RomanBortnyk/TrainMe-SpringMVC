package trainMe.config.test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import trainMe.controllers.ApiController;
import trainMe.controllers.MainController;
import trainMe.controllers.ProfileController;
import trainMe.dao.implementation.*;
import trainMe.model.Chat;
import trainMe.model.Message;
import trainMe.services.*;

/**
 * Created by romanb on 12/27/16.
 */

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = {})

public class WebTestConfiguration extends WebMvcConfigurerAdapter {


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // dao's mocks

    @Bean
    public UserDao userDao (){
        return Mockito.mock(UserDao.class);
    }

    @Bean
    public MessageDao messageDao (){
        return Mockito.mock(MessageDao.class);
    }

    @Bean
    public ChatDao chatDao(){
        return Mockito.mock(ChatDao.class);
    }

    @Bean
    public FeedbackDao feedbackDao (){ return Mockito.mock(FeedbackDao.class) ;}

    @Bean
    public DisciplineDao disciplineDao(){ return Mockito.mock(DisciplineDao.class);}

    @Bean
    public DisciplineUserLinkDao disciplineUserLinkDao(){ return Mockito.mock(DisciplineUserLinkDao.class);}



    //---------------------------------------

    @Bean
    public SimpMessagingTemplate messagingTemplate(){
        return Mockito.mock(SimpMessagingTemplate.class);
    }

    // services mocks

    @Bean
    public FeedbackService feedbackService(){
        return Mockito.mock(FeedbackService.class);
    }

    @Bean
    public SearchService searchService (){return Mockito.mock(SearchService.class); }

    @Bean
    public MessengerService messengerService (){
        return Mockito.mock(MessengerService.class);
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public DisciplineService disciplineService (){ return Mockito.mock(DisciplineService.class);}

    @Bean
    public ChatService chatService() {
        return Mockito.mock(ChatService.class);
    }

    @Bean
    public MessageService messageService() {
        return Mockito.mock(MessageService.class);
    }

    //----------------------------------------


    // controller's mocks
    @Bean
    public ProfileController profileController(){
        return new ProfileController();
    }

    @Bean
    public MainController mainController(){
        return new MainController();
    }

    @Bean
    public ApiController apiController (){
        return new ApiController();
    }


}
