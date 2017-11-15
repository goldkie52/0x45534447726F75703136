package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Responds to events from the servlet context of the application.
 * @author Matthew Carpenter 14012396
 */
@WebListener
public class ContextListener implements ServletContextListener {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Receives notification that the web application initialization process is starting.
     * All ServletContextListeners are notified of context initialization before any filters or servlets in the web application are initialized.
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String databaseName = sc.getInitParameter("databaseName");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + databaseName);
        } catch (ClassNotFoundException | SQLException ex) {
            sc.setAttribute("error", ex);
        }
        sc.setAttribute("databaseConnection", this.connection);
    }

    /**
     * Receives notification that the ServletContext is about to be shut down.
     * All servlets and filters will have been destroyed before any ServletContextListeners are notified of context destruction.
     * @param sce the ServletContextEvent containing the ServletContext that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            this.connection.close();
        } catch (SQLException ex) { }
    }
    
    // </editor-fold>
    
}
