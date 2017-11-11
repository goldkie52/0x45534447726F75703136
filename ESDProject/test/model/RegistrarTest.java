package model;

import dao.UserDaoImplTest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import test.BaseDbTestClass;

/**
 * Tests the Registrar class.
 * @author Matthew Carpenter 14012396
 */
public class RegistrarTest extends BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public RegistrarTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
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
        User user = createUser("t-user", "password");
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
