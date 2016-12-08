package trainMe.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import trainMe.aspects.Audience;
import trainMe.hibernate.HibernateUtil;

import javax.sql.DataSource;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"trainMe.controllers", "trainMe.api", "trainMe.messenger"})
@EnableAsync
@Import({SecurityConfig.class, RootConfig.class, WebSocketStompConfig.class})

public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void configureMessageConverters(
          List<HttpMessageConverter<?>> converters) {

    converters.add(new MappingJackson2HttpMessageConverter());

    super.configureMessageConverters(converters);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

//  @Bean
//  public SimpMessagingTemplate simpMessagingTemplate (){
//      return new SimpMessagingTemplate();
//  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Bean
  public CommonsMultipartResolver multipartResolver (){
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//    multipartResolver.setMaxUploadSize(100000);
    return multipartResolver;
  }




}
