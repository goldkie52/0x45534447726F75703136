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
     *
     * @param connection the connection to the database
     */
    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public boolean addPayment(Payment payment) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO PAYMENTS VALUES (?,?,?,?,?,?)")) {
            prepStatement.setInt(1, payment.getId());
            prepStatement.setString(2, payment.getMemId());
            prepStatement.setString(3, payment.getTypeOfPayment());
            prepStatement.setDouble(4, payment.getAmount());
            prepStatement.setDate(5, Date.valueOf(payment.getDate()));
            prepStatement.setTime(6, Time.valueOf(payment.getTime()));
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public Payment getPayment(int id) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE \"id\" = ?")) {
            prepStatement.setInt(1, id);
            try (ResultSet resultSet = prepStatement.executeQuery()) {
                if (resultSet.next()) {
                    String memId = resultSet.getString("mem_id");
                    String typeOfPayment = resultSet.getString("type_of_payment").trim();
                    double amount = resultSet.getDouble("amount");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime time = resultSet.getTime("time").toLocalTime();
                    return createPayment(id, memId, typeOfPayment, amount, date, time);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Payment[] getAllPayments() {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS");
                ResultSet resultSet = prepStatement.executeQuery()) {
            ArrayList<Payment> payments = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String memId = resultSet.getString("mem_id");
                String typeOfPayment = resultSet.getString("type_of_payment").trim();
                double amount = resultSet.getDouble("amount");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime time = resultSet.getTime("time").toLocalTime();
                payments.add(createPayment(id, memId, typeOfPayment, amount, date, time));
            }
            return payments.toArray(new Payment[0]);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Payment[] getPaymentsForMember(String memId) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE \"mem_id\" = ?")) {
            prepStatement.setString(1, memId);
            try (ResultSet resultSet = prepStatement.executeQuery()) {
                ArrayList<Payment> payments = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String typeOfPayment = resultSet.getString("type_of_payment").trim();
                    double amount = resultSet.getDouble("amount");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime time = resultSet.getTime("time").toLocalTime();
                    payments.add(createPayment(id, memId, typeOfPayment, amount, date, time));
                }
                return payments.toArray(new Payment[0]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updatePayment(Payment payment) {
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE PAYMENTS SET \"mem_id\" = ?, \"type_of_payment\" = ?, \"amount\" = ?, \"date\" = ?, \"time\" = ? WHERE \"id\" = ?")) {
            prepStatement.setString(1, payment.getMemId());
            prepStatement.setString(2, payment.getTypeOfPayment());
            prepStatement.setDouble(3, payment.getAmount());
            prepStatement.setDate(4, Date.valueOf(payment.getDate()));
            prepStatement.setTime(5, Time.valueOf(payment.getTime()));
            prepStatement.setInt(6, payment.getId());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public int getNextId() {
        Payment[] allPayments = getAllPayments();
        int maxId = -1;
        for (Payment payment : allPayments) {
            if (payment.getId() > maxId) {
                maxId = payment.getId();
            }
        }
        return maxId + 1;
    }

    private Payment createPayment(int id, String memId, String typeOfPayment, double amount, LocalDate date, LocalTime time) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setMemId(memId);
        payment.setTypeOfPayment(typeOfPayment.toUpperCase().trim());
        payment.setAmount(amount);
        payment.setDate(date);
        payment.setTime(time);
        return payment;
    }
    
    // </editor-fold>

}
