package model;

import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the SignupResult class.
 * @author Matthew Carpenter 14012396
 */
public class SignupResultTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public SignupResultTest() {
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
     * Test of isRequestValid method, of class SignupResult, when both userValid and dobValid fields are true.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsRequestValid_FieldsAreTrue() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = true;
        
        final Field userField = instance.getClass().getDeclaredField("userValid");
        userField.setAccessible(true);
        userField.set(instance, true);
        final Field dobField = instance.getClass().getDeclaredField("dobValid");
        dobField.setAccessible(true);
        dobField.set(instance, true);
        
        boolean result = instance.isRequestValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isRequestValid method, of class SignupResult, when both userValid and dobValid fields are false.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsRequestValid_FieldsAreFalse() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = false;
        
        final Field userField = instance.getClass().getDeclaredField("userValid");
        userField.setAccessible(true);
        userField.set(instance, false);
        final Field dobField = instance.getClass().getDeclaredField("dobValid");
        dobField.setAccessible(true);
        dobField.set(instance, false);
        
        boolean result = instance.isRequestValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isRequestValid method, of class SignupResult, when userValid is false and dobValid is true.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsRequestValid_UserIsInvalid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = false;
        
        final Field userField = instance.getClass().getDeclaredField("userValid");
        userField.setAccessible(true);
        userField.set(instance, true);
        final Field dobField = instance.getClass().getDeclaredField("dobValid");
        dobField.setAccessible(true);
        dobField.set(instance, false);
        
        boolean result = instance.isRequestValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isRequestValid method, of class SignupResult, when userValid is true and dobValid is false.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsRequestValid_DobIsInvalid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = false;
        
        final Field userField = instance.getClass().getDeclaredField("userValid");
        userField.setAccessible(true);
        userField.set(instance, false);
        final Field dobField = instance.getClass().getDeclaredField("dobValid");
        dobField.setAccessible(true);
        dobField.set(instance, true);
        
        boolean result = instance.isRequestValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of isUserValid method, of class SignupResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsUserValid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = true;
        
        final Field field = instance.getClass().getDeclaredField("userValid");
        field.setAccessible(true);
        field.set(instance, true);
        
        boolean result = instance.isUserValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserValid method, of class SignupResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetUserValid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        boolean userValid = true;
        SignupResult instance = new SignupResult();
        instance.setUserValid(userValid);
        
        final Field field = instance.getClass().getDeclaredField("userValid");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(userValid, (boolean)obj);
    }

    /**
     * Test of isDobValid method, of class SignupResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsDobValid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = true;
        
        final Field field = instance.getClass().getDeclaredField("dobValid");
        field.setAccessible(true);
        field.set(instance, true);
        
        boolean result = instance.isDobValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDobValid method, of class SignupResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetDobValid() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        boolean dobValid = true;
        SignupResult instance = new SignupResult();
        instance.setDobValid(dobValid);
        
        final Field field = instance.getClass().getDeclaredField("dobValid");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(dobValid, (boolean)obj);
    }

    /**
     * Test of isConnectionError method, of class SignupResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsConnectionError() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SignupResult instance = new SignupResult();
        boolean expResult = true;
        
        final Field field = instance.getClass().getDeclaredField("connectionError");
        field.setAccessible(true);
        field.set(instance, true);
        
        boolean result = instance.isConnectionError();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConnectionError method, of class SignupResult.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetConnectionError() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        boolean connectionError = true;
        SignupResult instance = new SignupResult();
        instance.setConnectionError(connectionError);
        
        final Field field = instance.getClass().getDeclaredField("connectionError");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(connectionError, (boolean)obj);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
