package model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

 /**
 * Tests the Member class.
 * @author Rachel Bailey 13006455
 */
public class MemberTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public MemberTest() {
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
     * Test of getId method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        String expResult = "test_id";
        
        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getId();
        assertEquals(expResult, result);
    }

     /**
     * Test of setId method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetId() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        String id = "member_id";
        Member instance = new Member();
        instance.setId(id);
        
        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(id, (String)obj);
    }

     /**
     * Test of getName method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetName() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        String expResult = "name";
        
        final Field field = instance.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getName();
        assertEquals(expResult, result);
    }

     /**
     * Test of setName method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetName() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String name = "name";
        Member instance = new Member();
        instance.setName(name);
        
        final Field field = instance.getClass().getDeclaredField("name");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(name, (String)obj);
    }
    
     /**
     * Test of getAddress method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetAddress() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        String expResult = "address";
        
        final Field field = instance.getClass().getDeclaredField("address");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

     /**
     * Test of setAddress method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetAddress() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String address = "address";
        Member instance = new Member();
        instance.setAddress(address);
        
        final Field field = instance.getClass().getDeclaredField("address");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(address, (String)obj);
    }

     /**
     * Test of getDob method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetDob() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        LocalDate expResult = LocalDate.now();
        
        final Field field = instance.getClass().getDeclaredField("dob");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        LocalDate result = instance.getDob();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDob method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetDob() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LocalDate dob = LocalDate.now();
        Member instance = new Member();
        instance.setDob(dob);
        
        final Field field = instance.getClass().getDeclaredField("dob");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(dob, (LocalDate)obj);
    }
    
     /**
     * Test of getDor method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetDor() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        LocalDate expResult = LocalDate.now();
        
        final Field field = instance.getClass().getDeclaredField("dor");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        LocalDate result = instance.getDor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDor method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetDor() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LocalDate dor = LocalDate.now();
        Member instance = new Member();
        instance.setDor(dor);
        
        final Field field = instance.getClass().getDeclaredField("dor");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(dor, (LocalDate)obj);
    }
    
    
    /**
     * Test of getStatus method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetStatus() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        MemberStatus expResult = MemberStatus.APPROVED;
        
        final Field field = instance.getClass().getDeclaredField("status");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        MemberStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetStatus() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        MemberStatus status = MemberStatus.APPROVED;
        Member instance = new Member();
        instance.setStatus(status);
        
        final Field field = instance.getClass().getDeclaredField("status");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertEquals(status, (MemberStatus)obj);
    }
    
     /**
     * Test of getBalance method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetBalance() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Member instance = new Member();
        double expResult = 10.10;
        
        final Field field = instance.getClass().getDeclaredField("balance");
        field.setAccessible(true);
        field.set(instance, expResult);
        
        double result = instance.getBalance();
        assertTrue(expResult == result);
    }

     /**
     * Test of setBalance method, of class Member.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetBalance() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        double balance = 10.10;
        Member instance = new Member();
        instance.setBalance(balance);
        
        final Field field = instance.getClass().getDeclaredField("balance");
        field.setAccessible(true);
        Object obj = field.get(instance);
        
        assertTrue(balance == (double)obj);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
