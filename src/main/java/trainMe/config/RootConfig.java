package trainMe.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import trainMe.hibernate.HibernateUtil;

import javax.sql.DataSource;

/**
 * Created by romanb on 12/7/16.
 */
@Configuration
@ComponentScan(basePackages = {"trainMe.dao","trainMe.hibernate","trainMe.services"})
@EnableTransactionManagement
//@Import({WebConfig.class})

public class RootConfig {

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory(dataSource()));
        return htm;
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
