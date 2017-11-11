package model;

import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the LoginResult class.
 * @author Matthew Carpenter 14012396
 */
public class LoginResultTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public LoginResultTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Test Lifecycle">
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of isValid method, of class LoginResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsValid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LoginResult instance = new LoginResult();
        boolean expResult = true;
        
        final Field userField = instance.getClass().getDeclaredField("valid");
        userField.setAccessible(true);
        userField.set(instance, true);
        
        boolean result = instance.isValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValid method, of class LoginResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetValid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        boolean valid = false;
        LoginResult instance = new LoginResult();
        instance.setValid(valid);
        
        final Field field = instance.getClass().getDeclaredField("valid");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(valid, (boolean)obj);
    }

    /**
     * Test of getUser method, of class LoginResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetUser() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LoginResult instance = new LoginResult();
        User expResult = new User();
        expResult.setId("id");
        expResult.setPassword("pass");
        expResult.setStatus(UserStatus.SUSPENDED);
        
        final Field userField = instance.getClass().getDeclaredField("user");
        userField.setAccessible(true);
        userField.set(instance, expResult);
        
        User result = instance.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUser method, of class LoginResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetUser() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        User user = new User();
        user.setId("id");
        user.setPassword("pass");
        user.setStatus(UserStatus.SUSPENDED);
        LoginResult instance = new LoginResult();
        instance.setUser(user);
        
        final Field field = instance.getClass().getDeclaredField("user");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(user, (User)obj);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
