package com.company.web;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.company.model.pool.ConnectionPool;
import com.company.model.pool.PoolException;



public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Logger logger = Logger.getLogger(ApplicationStartListener.class);
        try {
            ConnectionPool.getInstance().destroy();
          } catch (PoolException e) {
           logger.error(e);
          }

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String homeDir = context.getRealPath("/");
        File properties = new File(homeDir,"WEB-INF/lib/log4j.properties");
        File message = new File(homeDir,"WEB-INF/message.properties");
        PropertyConfigurator.configure(properties.toString());
        PropertyConfigurator.configure(message.toString());
        Logger logger = Logger.getLogger(ApplicationStartListener.class);
        String jdbcUrl = context.getInitParameter("jdbc-url");
        String jdbcDriver = context.getInitParameter("jdbc-driver");
        String jdbcUser = context.getInitParameter("jdbc-user");
        String jdbcPassword = context.getInitParameter("jdbc-password");
        int poolMinSize = Integer.parseInt(context.getInitParameter("pool-min-size"));
        int poolMaxSize = Integer.parseInt(context.getInitParameter("pool-max-size"));
        int poolConnectionValidationTimeout = Integer.parseInt(context.getInitParameter("pool-connection-validation-timeout"));
        try {
             ConnectionPool.getInstance().init(jdbcUrl, jdbcDriver, jdbcUser, jdbcPassword, poolMinSize,
                 poolMaxSize,poolConnectionValidationTimeout);
             logger.info("init ok");
            } catch (PoolException e) {
            logger.fatal(e);
        }
    }
}