package trainMe.config.test;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import trainMe.hibernate.HibernateUtil;

import javax.sql.DataSource;

/**
 * Created by romanb on 12/12/16.
 */
@Configuration
@ComponentScan(basePackages = {"trainMe.dao","trainMe.hibernate"})
@EnableTransactionManagement
public class DAOTestConfig {

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
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl("jdbc:hsqldb:mem:TestDB" );
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("trainMe.model");

        sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        sessionBuilder.setProperty("hibernate.show_sql", "true");
        sessionBuilder.setProperty("hibernate.hbm2ddl.auto" ,"create");
        sessionBuilder.setProperty("hibernate.cache.use_second_level_cache" ,"true");

        return sessionBuilder.buildSessionFactory();
    }
}
