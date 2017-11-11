package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;
import org.junit.Test;
import static org.junit.Assert.*;
import test.BaseDbTestClass;

/**
 * Tests the PaymentDaoImpl class.
 * @author Kieran Harris 14010534
 * @author Matthew Carpenter 14012396
 */
public class PaymentDaoImplTest extends BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public PaymentDaoImplTest() {
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Tests">
    /**
     * Test of addUser method, of class UserDaoImpl, when the user does not
     * already exist in the database.
     */
    @Test
    public void testAddPayment_PaymentNotInDatabase() {
        Payment payment = createPayment(1, "e-simons", "FEE", 10, LocalDate.parse("2015-01-07"), LocalTime.parse("10:08:21"));
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.addPayemnt(payment);
        assertEquals(expResult, result);
        assertEquals(true, isPaymentPresentInTestDatabase(payment));
    }

    /**
     * Test of addUser method, of class UserDaoImpl, when the user already
     * exists in the database.
     */
    @Test
    public void testAddPayment_PaymentInDatabase() {
        Payment payment = createPayment(1, "e-simons", "FEE", 10, LocalDate.parse("2015-01-07"), LocalTime.parse("10:08:21"));
        assertTrue(addPaymentToTestDatabase(payment));
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.addPayemnt(payment);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class UserDaoImpl, when the user exists in the
     * database.
     */
    @Test
    public void testGetPayment_PaymentExists() {
        int id = 1;
        Payment expResult = createPayment(1, "e-simons", "FEE", 10, LocalDate.parse("2015-01-07"), LocalTime.parse("10:08:21"));
        assertTrue(addPaymentToTestDatabase(expResult));
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        Payment result = instance.getPayment(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class UserDaoImpl, when the user does not
     * exist in the database.
     */
    @Test
    public void testGetPayment_PaymentDoesNotExist() {
        int id = 1;
        Payment expResult = null;
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        Payment result = instance.getPayment(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserDaoImpl, when there are users in
     * the database.
     */
    @Test
    public void testGetAllPayments_PaymentsPresent() {
        int[] payments = new int[]{1, 2, 3, 4};
        String[] memIds = new String[]{"memId1", "memId2", "memId3", "memId4"};
        String[] typeOfPayments = new String[]{"FEE", "FEE", "FEE", "FEE"};
        float[] amounts = new float[]{10, 10 , 10, 10};
        LocalDate[] dates = new LocalDate[]{LocalDate.parse("2015-01-07"), LocalDate.parse("2015-01-07"), LocalDate.parse("2015-01-07"), LocalDate.parse("2015-01-07")};
        LocalTime[] times = new LocalTime[]{LocalTime.parse("10:08:21"), LocalTime.parse("10:08:21"), LocalTime.parse("10:08:21"), LocalTime.parse("10:08:21")};
        
        Payment[] expResult = new Payment[payments.length];
        for (int i = 0; i < payments.length; i++) {
            Payment payment = createPayment(payments[i], memIds[i], typeOfPayments[i], amounts[i], dates[i], times[i]);
            expResult[i] = payment;
            addPaymentToTestDatabase(payment);
        }
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        Payment[] result = instance.getAllPayments();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserDaoImpl, when there are no users
     * in the database.
     */
    @Test
    public void testGetAllPayments_NoPayments() {
        Payment[] expResult = new Payment[0];
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        Payment[] result = instance.getAllPayments();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of updateUser method, of class UserDaoImpl, when the user exists in
     * the database.
     */
    @Test
    public void testUpdatePayment_PaymentExists() {
        Payment payment = createPayment(1, "e-simons", "FEE", 10, LocalDate.parse("2015-01-07"), LocalTime.parse("10:08:21"));
        addPaymentToTestDatabase(payment);
        payment.setMemId("e-simonsUpdated");
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.updatePayment(payment);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateUser method, of class UserDaoImpl, when the user does not
     * exist in the database.
     */
    @Test
    public void testUpdatePayment_PaymentDoesNotExist() {
        Payment payment = createPayment(1, "e-simons", "FEE", 10, LocalDate.parse("2015-01-07"), LocalTime.parse("10:08:21"));
        payment.setMemId("e-simonsUpdated");
        PaymentDaoImpl instance = new PaymentDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.updatePayment(payment);
        assertEquals(expResult, result);
    }

    // </editor-fold>
    
    private Payment createPayment(int id, String memId, String typeOfPayment, float amount, LocalDate date, LocalTime time) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setMemId(memId);
        payment.setTypeOfPayment(typeOfPayment);
        payment.setAmount(amount);
        payment.setDate(date);
        payment.setTime(time);
        return payment;
    }

    private boolean isPaymentPresentInTestDatabase(Payment payment) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE \"id\" = ?")) {
            prepStatement.setInt(1, payment.getId());
            try (ResultSet queryResult = prepStatement.executeQuery()) {
                if (queryResult.next()) {
                    int id = queryResult.getInt("id");
                    String memId = queryResult.getString("mem_id");
                    String typeOfPayment = queryResult.getString("type_of_payment").trim();
                    float amount = queryResult.getFloat("amount");
                    LocalDate date = queryResult.getDate("date").toLocalDate();
                    LocalTime time = queryResult.getTime("time").toLocalTime();

                    return payment.getId() == id && payment.getMemId().equals(memId) && payment.getTypeOfPayment().equals(typeOfPayment) && payment.getAmount() == amount && payment.getDate().equals(date) && payment.getTime().equals(time);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean addPaymentToTestDatabase(Payment payment) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO PAYMENTS VALUES (?, ?, ?, ?, ?, ?)")) {
            prepStatement.setInt(1, payment.getId());
            prepStatement.setString(2, payment.getMemId());
            prepStatement.setString(3, payment.getTypeOfPayment());
            prepStatement.setFloat(4, payment.getAmount());
            prepStatement.setDate(5, Date.valueOf(payment.getDate()));
            prepStatement.setTime(6, Time.valueOf(payment.getTime()));
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // </editor-fold>
    
}
