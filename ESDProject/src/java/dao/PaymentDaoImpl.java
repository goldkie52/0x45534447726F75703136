package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import model.Payment;
import java.util.logging.Logger;

/**
 * Provides the implementation for accessing payment data from the database.
 *
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 * @author Rachel Bailey 13006455
 * @see dao.PaymentDao
 */
public class PaymentDaoImpl implements PaymentDao {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Initializes a new instance of the PaymentDaoImpl class.
     * @param connection the connection to the database
     */
    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Adds a new payment to the system.
     * @param payment the payment to add to the system
     * @return true if the payment was added; otherwise false
     */
    @Override
    public boolean addPayment(Payment payment) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO PAYMENTS VALUES (?,?,?,?,?,?)")) {
            // Set values on statement
            prepStatement.setInt(1, payment.getId());
            prepStatement.setString(2, payment.getMemId());
            prepStatement.setString(3, payment.getTypeOfPayment());
            prepStatement.setDouble(4, payment.getAmount());
            prepStatement.setDate(5, Date.valueOf(payment.getDate()));
            prepStatement.setTime(6, Time.valueOf(payment.getTime()));
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }

    /**
     * Gets the payment with the specified id.
     * @param id the id of the payment to get
     * @return the payment with the specified id if the operation was a success; otherwise null
     */
    @Override
    public Payment getPayment(int id) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE \"id\" = ?")) {
            // Set value on statement
            prepStatement.setInt(1, id);
            // Execute the statement and pull out the result set
            try (ResultSet resultSet = prepStatement.executeQuery()) {
                // If there is a result present
                if (resultSet.next()) {
                    // Pull values from row
                    String memId = resultSet.getString("mem_id");
                    String typeOfPayment = resultSet.getString("type_of_payment").trim();
                    double amount = resultSet.getDouble("amount");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime time = resultSet.getTime("time").toLocalTime();
                    // Create Payment object with values and return
                    return createPayment(id, memId, typeOfPayment, amount, date, time);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Gets all the payments in the system.
     * @return an array of all payments in the system if successful; otherwise null
     */
    @Override
    public Payment[] getAllPayments() {
        // Create the prepared statement and execute it, pulling out the result set
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS");
                ResultSet resultSet = prepStatement.executeQuery()) {
            ArrayList<Payment> payments = new ArrayList<>();
            // While there are more rows in the result set
            while (resultSet.next()) {
                // Pull values from row
                int id = resultSet.getInt("id");
                String memId = resultSet.getString("mem_id");
                String typeOfPayment = resultSet.getString("type_of_payment").trim();
                double amount = resultSet.getDouble("amount");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime time = resultSet.getTime("time").toLocalTime();
                // Create Payment object with values and add to list
                payments.add(createPayment(id, memId, typeOfPayment, amount, date, time));
            }
            // Convert list to array and return
            return payments.toArray(new Payment[0]);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }
    
    /**
     * Gets all the Payments for a specified memId
     * @param memId the member id to get payments for
     * @return an array of all payments in the system for a specified member id if successful; otherwise null
     */
    @Override
    public Payment[] getPaymentsForMember(String memId) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE \"mem_id\" = ?")) {
            // Set value on statement
            prepStatement.setString(1, memId);
            // Execute the statement and pull out the result set
            try (ResultSet resultSet = prepStatement.executeQuery()) {
                ArrayList<Payment> payments = new ArrayList<>();
                // While there are more rows in the result set
                while (resultSet.next()) {
                    // Pull values from row
                    int id = resultSet.getInt("id");
                    String typeOfPayment = resultSet.getString("type_of_payment").trim();
                    double amount = resultSet.getDouble("amount");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime time = resultSet.getTime("time").toLocalTime();
                    // Create Payment object with values and add to list
                    payments.add(createPayment(id, memId, typeOfPayment, amount, date, time));
                }
                // Convert list to array and return
                return payments.toArray(new Payment[0]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Updates the specified payment in the system.
     * @param payment the payment to update
     * @return true if the update was successful, otherwise false
     */
    @Override
    public boolean updatePayment(Payment payment) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE PAYMENTS SET \"mem_id\" = ?, \"type_of_payment\" = ?, \"amount\" = ?, \"date\" = ?, \"time\" = ? WHERE \"id\" = ?")) {
            // Set values on statement
            prepStatement.setString(1, payment.getMemId());
            prepStatement.setString(2, payment.getTypeOfPayment());
            prepStatement.setDouble(3, payment.getAmount());
            prepStatement.setDate(4, Date.valueOf(payment.getDate()));
            prepStatement.setTime(5, Time.valueOf(payment.getTime()));
            prepStatement.setInt(6, payment.getId());
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return false;
    }
    
    /**
     * Gets the next open id for a payment
     * @return the next valid id
     */
    @Override
    public int getNextId() {
        // Get all payments in the system
        Payment[] allPayments = getAllPayments();
        int maxId = -1;
        // Loop over each payment to calculate the highest id
        for (Payment payment : allPayments) {
            if (payment.getId() > maxId) {
                maxId = payment.getId();
            }
        }
        // Return the next valid id; the current maximum + 1
        return maxId + 1;
    }

    private Payment createPayment(int id, String memId, String typeOfPayment, double amount, LocalDate date, LocalTime time) {
        // Create new Payment object
        Payment payment = new Payment();
        // Set values on object
        payment.setId(id);
        payment.setMemId(memId);
        payment.setTypeOfPayment(typeOfPayment.toUpperCase().trim());
        payment.setAmount(amount);
        payment.setDate(date);
        payment.setTime(time);
        // Return object
        return payment;
    }
    
    // </editor-fold>

}
