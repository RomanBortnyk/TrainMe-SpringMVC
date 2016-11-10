package trainMe.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import trainMe.HibernateUserRepository;
import trainMe.hibernate.HibernateUtil;
import trainMe.model.User;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("trainMe")
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Bean
  public HibernateTransactionManager hibernateTransactionManager(){
    HibernateTransactionManager htm = new HibernateTransactionManager();
    htm.setSessionFactory(sessionFactory(dataSource()));
    return htm;
  }


  @Bean
  public HibernateUserRepository hibUsrRepo (){
    return new HibernateUserRepository();
  }

  @Bean
  public HibernateUtil hibernateUtil (){
    HibernateUtil hibernateUtil = new HibernateUtil();
    hibernateUtil.setSessionFactory(sessionFactory(dataSource()));
    return hibernateUtil;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://localhost:3306/TrainMe");
    ds.setUsername("root");
    ds.setPassword("root");
    return ds;
  }

  @Bean
  public SessionFactory sessionFactory(DataSource dataSource) {

    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    sessionBuilder.scanPackages("trainMe.model");

    sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//    sessionBuilder.setProperty("hibernate.show_sql", "true");

    return sessionBuilder.buildSessionFactory();
  }


}
