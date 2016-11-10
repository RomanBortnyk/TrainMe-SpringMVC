package messenger.controllers;

import messenger.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by romab on 10/28/16.
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        servletContextEvent.getServletContext().setAttribute("database", new Database());

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
