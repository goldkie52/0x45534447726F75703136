package model;

import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the User class.
 * @author Matthew Carpenter 14012396
 */
public class UserTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public UserTest() {
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
     * Test of getId method, of class User.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        User instance = new User();
        String expResult = "test_id";
        
        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class User.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetId() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        String id = "user_id";
        User instance = new User();
        instance.setId(id);
        
        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(id, (String)obj);
    }

    /**
     * Test of getPassword method, of class User.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetPassword() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        User instance = new User();
        String expResult = "password";
        
        final Field field = instance.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetPassword() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String password = "password";
        User instance = new User();
        instance.setPassword(password);
        
        final Field field = instance.getClass().getDeclaredField("password");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(password, (String)obj);
    }

    /**
     * Test of getStatus method, of class User.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetStatus() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        User instance = new User();
        UserStatus expResult = UserStatus.APPROVED;
        
        final Field field = instance.getClass().getDeclaredField("status");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        UserStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class User.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetStatus() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        UserStatus status = UserStatus.APPROVED;
        User instance = new User();
        instance.setStatus(status);
        
        final Field field = instance.getClass().getDeclaredField("status");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(status, (UserStatus)obj);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
