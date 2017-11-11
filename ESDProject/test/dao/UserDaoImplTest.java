package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.UserStatus;
import org.junit.Test;
import static org.junit.Assert.*;
import test.BaseDbTestClass;

/**
 * Tests the UserDaoImpl class.
 * @author Matthew Carpenter 14012396
 */
public class UserDaoImplTest extends BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public UserDaoImplTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of addUser method, of class UserDaoImpl, when the user does not already exist in the database.
     */
    @Test
    public void testAddUser_UserNotInDatabase() {
        User user = createUser("test_user", "password");
        UserDaoImpl instance = new UserDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.addUser(user);
        assertEquals(expResult, result);
        assertEquals(true, isUserPresentInTestDatabase(user));
    }
    
    /**
     * Test of addUser method, of class UserDaoImpl, when the user already exists in the database.
     */
    @Test
    public void testAddUser_UserInDatabase() {
        User user = createUser("test_user", "password");
        assertTrue(addUserToTestDatabase(user));
        UserDaoImpl instance = new UserDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.addUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class UserDaoImpl, when the user exists in the database.
     */
    @Test
    public void testGetUser_UserExists() {
        String id  = "test_user";
        User expResult = createUser(id, "password");
        assertTrue(addUserToTestDatabase(expResult));
        UserDaoImpl instance = new UserDaoImpl(connection);
        User result = instance.getUser(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUser method, of class UserDaoImpl, when the user does not exist in the database.
     */
    @Test
    public void testGetUser_UserDoesNotExist() {
        String id  = "test_user";
        User expResult = null;
        UserDaoImpl instance = new UserDaoImpl(connection);
        User result = instance.getUser(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserDaoImpl, when there are users in the database.
     */
    @Test
    public void testGetAllUsers_UsersPresent() {
        String[] users = new String[] { "test_user1", "test_user2", "test_user3", "test_user4" };
        String[] passwords = new String[] { "password1", "password2", "password3", "password4" };
        User[] expResult = new User[users.length];
        for (int i = 0; i < users.length; i++) {
            User user = createUser(users[i], passwords[i]);
            expResult[i] = user;
            addUserToTestDatabase(user);
        }
        UserDaoImpl instance = new UserDaoImpl(connection);
        User[] result = instance.getAllUsers();
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of getAllUsers method, of class UserDaoImpl, when there are no users in the database.
     */
    @Test
    public void testGetAllUsers_NoUsers() {
        User[] expResult = new User[0];
        UserDaoImpl instance = new UserDaoImpl(connection);
        User[] result = instance.getAllUsers();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of updateUser method, of class UserDaoImpl, when the user exists in the database.
     */
    @Test
    public void testUpdateUser_UserExists() {
        String id = "test_user";
        User user = createUser(id, "password");
        addUserToTestDatabase(user);
        user.setStatus(UserStatus.SUSPENDED);
        UserDaoImpl instance = new UserDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.updateUser(user);
        assertEquals(expResult, result);
        isUserPresentInTestDatabase(user);
    }
    
    /**
     * Test of updateUser method, of class UserDaoImpl, when the user does not exist in the database.
     */
    @Test
    public void testUpdateUser_UserDoesNotExist() {
        String id = "test_user";
        User user = createUser(id, "password");
        user.setStatus(UserStatus.SUSPENDED);
        UserDaoImpl instance = new UserDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.updateUser(user);
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    private User createUser(String id, String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setStatus(UserStatus.APPROVED);
        return user;
    }
    
    private boolean isUserPresentInTestDatabase(User user) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM USERS WHERE \"id\" = ?")) {
            prepStatement.setString(1, user.getId());
            try (ResultSet queryResult = prepStatement.executeQuery()) {
                if (queryResult.next()) {
                    String passwordResult = queryResult.getString("password");
                    String statusResultString = queryResult.getString("status").trim();
                    UserStatus statusResult = UserStatus.valueOf(statusResultString);
                    return user.getPassword().equals(passwordResult) && user.getStatus().equals(statusResult);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
