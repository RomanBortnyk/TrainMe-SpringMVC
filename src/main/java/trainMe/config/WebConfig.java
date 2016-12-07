package trainMe.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"trainMe.controllers", "trainMe.api", "trainMe.messenger"})
@EnableAsync
@Import({SecurityConfig.class, RootConfig.class})

public class WebConfig extends WebMvcConfigurerAdapter {


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

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
