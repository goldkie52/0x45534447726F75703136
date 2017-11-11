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
 * Tests the Authenticator class.
 * @author Matthew Carpenter 14012396
 */
public class AuthenticatorTest extends BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public AuthenticatorTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of authenticate method, of class Authenticator, with valid inputs.
     */
    @Test
    public void testAuthenticate_ValidInputs() {
        String username = "test_user";
        String password = "password";
        User user = createUser(username, password);
        assertTrue(addUserToTestDatabase(user));
        Authenticator instance = new Authenticator(connection);
        
        LoginResult result = instance.authenticate(username, password);
        
        assertEquals(true, result.isValid());
        assertEquals(user, result.getUser());
    }
    
    /**
     * Test of authenticate method, of class Authenticator, with valid user but invalid password.
     */
    @Test
    public void testAuthenticate_ValidUserInvalidPassword() {
        String username = "test_user";
        String password = "password";
        User user = createUser(username, password);
        assertTrue(addUserToTestDatabase(user));
        Authenticator instance = new Authenticator(connection);
        
        LoginResult result = instance.authenticate(username, "incorrect");
        
        assertEquals(false, result.isValid());
        assertEquals(null, result.getUser());
    }
    
    /**
     * Test of authenticate method, of class Authenticator, with invalid inputs.
     */
    @Test
    public void testAuthenticate_InvalidInputs() {
        String username = "test_user";
        String password = "password";
        User user = createUser(username, password);
        assertTrue(addUserToTestDatabase(user));
        Authenticator instance = new Authenticator(connection);
        
        LoginResult result = instance.authenticate("wrong", "incorrect");
        
        assertEquals(false, result.isValid());
        assertEquals(null, result.getUser());
    }
    
    // </editor-fold>
    
    private User createUser(String id, String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setStatus(UserStatus.APPLIED);
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
