package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * The base class to use for all database-required test classes.
 * @author Matthew Carpenter 14012396
 */
public abstract class BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    protected static Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Test Lifecycle">
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDbTestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc_Test");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDbTestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDbTestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
        try (PreparedStatement claimsPrepStatement = connection.prepareStatement("DELETE FROM CLAIMS");
                PreparedStatement membersPrepStatement = connection.prepareStatement("DELETE FROM MEMBERS");
                PreparedStatement paymentsPrepStatement = connection.prepareStatement("DELETE FROM PAYMENTS");
                PreparedStatement usersPrepStatement = connection.prepareStatement("DELETE FROM USERS")) {
            claimsPrepStatement.execute();
            membersPrepStatement.execute();
            paymentsPrepStatement.execute();
            usersPrepStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDbTestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
