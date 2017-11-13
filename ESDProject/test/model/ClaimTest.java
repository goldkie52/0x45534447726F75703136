/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author c27-harris
 */
public class ClaimTest {

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ClaimTest() {
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
     * Test of getId method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Claim instance = new Claim();
        int expResult = 0;

        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(instance, expResult);

        int result = instance.getId();
        assertTrue(expResult == result);
    }

    /**
     * Test of setId method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        int id = 0;
        Claim instance = new Claim();
        instance.setId(id);

        final Field field = instance.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Object obj = field.get(instance);

        assertTrue(id == (int) obj);
    }

    /**
     * Test of getMemid method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetMemId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Claim instance = new Claim();
        String expResult = "test_memId";

        final Field field = instance.getClass().getDeclaredField("mem_id");
        field.setAccessible(true);
        field.set(instance, expResult);

        String result = instance.getMemId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMemid method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetMemId() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String memId = "claim_memId";
        Claim instance = new Claim();
        instance.setMemId(memId);

        final Field field = instance.getClass().getDeclaredField("mem_id");
        field.setAccessible(true);
        Object obj = field.get(instance);

        assertEquals(memId, (String) obj);
    }

    /**
     * Test of getDate method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetDate() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Claim instance = new Claim();
        LocalDate expResult = LocalDate.now();

        final Field field = instance.getClass().getDeclaredField("date");
        field.setAccessible(true);
        field.set(instance, expResult);

        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetDate() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LocalDate date = LocalDate.now();
        Claim instance = new Claim();
        instance.setDate(date);

        final Field field = instance.getClass().getDeclaredField("date");
        field.setAccessible(true);
        Object obj = field.get(instance);

        assertEquals(date, (LocalDate) obj);
    }

    /**
     * Test of getRationale method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetRationale() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Claim instance = new Claim();
        String expResult = "test_rationale";

        final Field field = instance.getClass().getDeclaredField("rationale");
        field.setAccessible(true);
        field.set(instance, expResult);

        String result = instance.getRationale();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRationale method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetRationale() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String rationale = "claim_rationale";
        Claim instance = new Claim();
        instance.setRationale(rationale);

        final Field field = instance.getClass().getDeclaredField("rationale");
        field.setAccessible(true);
        Object obj = field.get(instance);

        assertEquals(rationale, (String) obj);
    }

    /**
     * Test of getStatus method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetStatus() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Claim instance = new Claim();
        ClaimStatus expResult = ClaimStatus.APPROVED;

        final Field field = instance.getClass().getDeclaredField("status");
        field.setAccessible(true);
        field.set(instance, expResult);

        ClaimStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetStatus() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        ClaimStatus status = ClaimStatus.APPROVED;
        Claim instance = new Claim();
        instance.setStatus(status);

        final Field field = instance.getClass().getDeclaredField("status");
        field.setAccessible(true);
        Object obj = field.get(instance);

        assertEquals(status, (ClaimStatus) obj);
    }

    /**
     * Test of getAmount method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetAmount() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Claim instance = new Claim();
        double expResult = 10.10;

        final Field field = instance.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        field.set(instance, expResult);

        double result = instance.getAmount();
        assertTrue(expResult == result);
    }

    /**
     * Test of setAmount method, of class Claim.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetAmount() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        double amount = 10.10;
        Claim instance = new Claim();
        instance.setAmount(amount);

        final Field field = instance.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        Object obj = field.get(instance);

        assertTrue(amount == (double) obj);
    }

    // </editor-fold>
    // </editor-fold>
}
