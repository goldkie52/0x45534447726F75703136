package model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Payment class.
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 */
public class PaymentTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public PaymentTest() {
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
     * Test of getId method, of class Payment.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Payment instance = new Payment();
        int expResult = 1;
        
        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Payment.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    @Test
    public void testSetId() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int id = 1;
        Payment instance = new Payment();
        instance.setId(id);
        
        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(id, (int)obj);
    }
    
    /**
     * Test of getMemId method, of class Payment.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetMemId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Payment instance = new Payment();
        String expResult = "e-simons";
        
        final Field field = instance.getClass().getDeclaredField("memId");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getMemId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setMemId method, of class Payment
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetMemId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        String memId = "e-simons";
        Payment instance = new Payment();
        instance.setMemId(memId);
        
        final Field field = instance.getClass().getDeclaredField("memId");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(memId, (String) obj);
    }
    
    /**
     * Test of getTypeOfPayment method, of class Payment.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetTypeOfPayment() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Payment instance = new Payment();
        String expResult = "FEE";
        
        final Field field = instance.getClass().getDeclaredField("typeOfPayment");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getTypeOfPayment();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setTypeOfPayment method, of class Payment
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetTypeOfPayment() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        String memId = "FEE";
        Payment instance = new Payment();
        instance.setMemId(memId);
        
        final Field field = instance.getClass().getDeclaredField("memId");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(memId, (String) obj);
    }

    /**
     * Test of getAmount method, of class Payment.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetAmount() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Payment instance = new Payment();
        float expResult = 10.0f;
        
        final Field field = instance.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        float result = instance.getAmount();
        assertEquals(expResult, result, 0.0001);
    }
    
    /**
     * Test of setAmount method, of class Payment
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetAmount() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        float amount = 10.0f;
        Payment instance = new Payment();
        instance.setAmount(amount);
        
        final Field field = instance.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(amount, (float) obj, 0.0001);
    }
    
    /**
     * Test of getDate method, of class Payment.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetDate() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Payment instance = new Payment();
        LocalDate expResult = LocalDate.parse("2015-01-07");
        
        final Field field = instance.getClass().getDeclaredField("date");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setDate method, of class Payment
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetDate() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        LocalDate date = LocalDate.parse("2015-01-07");
        Payment instance = new Payment();
        instance.setDate(date);
        
        final Field field = instance.getClass().getDeclaredField("date");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(date, (LocalDate) obj);
    }
    
    /**
     * Test of getTime method, of class Payment.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetTime() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Payment instance = new Payment();
        LocalTime expResult = LocalTime.parse("10:08:21");
        
        final Field field = instance.getClass().getDeclaredField("time");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        LocalTime result = instance.getTime();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setTime method, of class Payment
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetTime() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        LocalTime time = LocalTime.parse("10:08:21");
        Payment instance = new Payment();
        instance.setTime(time);
        
        final Field field = instance.getClass().getDeclaredField("time");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(time, (LocalTime) obj);
    }
        
    // </editor-fold>
    
    // </editor-fold>
    
}
