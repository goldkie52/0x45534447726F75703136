package model;

import dao.UserDaoImplTest;
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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Registrar class.
 * @author Matthew Carpenter 14012396
 */
public class RegistrarTest {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private static Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public RegistrarTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Test Lifecycle">
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc_Test");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
        try (PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM USERS")) {
            prepStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM MEMBERS")) {
            prepStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of register method, of class Registrar, with valid inputs.
     */
    @Test
    public void testRegister_ValidInputs() {
        String firstName = "Test";
        String lastName = "User";
        String dob = "01-01-2017";
        String address = "Address";
        Registrar instance = new Registrar(connection);
        SignupResult result = instance.register(firstName, lastName, dob, address);
        assertEquals(true, result.isUserValid());
        assertEquals(true, result.isDobValid());
        assertEquals(true, result.isRequestValid());
        assertEquals(false, result.isConnectionError());
    }
    
    /**
     * Test of register method, of class Registrar, with invalid dob.
     */
    @Test
    public void testRegister_InvalidDob() {
        String firstName = "Test";
        String lastName = "User";
        String dob = "40-40-2017";
        String address = "Address";
        Registrar instance = new Registrar(connection);
        SignupResult result = instance.register(firstName, lastName, dob, address);
        assertEquals(false, result.isDobValid());
    }
    
    /**
     * Test of register method, of class Registrar, with invalid user.
     */
    @Test
    public void testRegister_InvalidUser() {
        User user = createUser("T-User", "password");
        assertTrue(addUserToTestDatabase(user));
        String firstName = "Test";
        String lastName = "User";
        String dob = "01-01-2017";
        String address = "Address";
        Registrar instance = new Registrar(connection);
        SignupResult result = instance.register(firstName, lastName, dob, address);
        assertEquals(false, result.isUserValid());
    }
    
    // </editor-fold>
    
    private User createUser(String id, String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setStatus(UserStatus.APPROVED);
        return user;
    }
    
    private boolean addUserToTestDatabase(User user) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)")) {
            prepStatement.setString(1, user.getId());
            prepStatement.setString(2, user.getPassword());
            prepStatement.setString(3, user.getStatus().toString().toUpperCase());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // </editor-fold>
    
}
