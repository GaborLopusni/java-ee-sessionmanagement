package org.glopusni.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@javax.servlet.annotation.WebListener
public class WebListener implements ServletContextListener, HttpSessionListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);

        sce.getServletContext().setSessionTimeout(2); // 2 minutes
        System.out.println("contextInitialized() called");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("An HttpSession was created.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("An HttpSession was destroyed.");
    }
}
