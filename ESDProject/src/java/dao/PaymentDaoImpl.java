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
 * @see dao.PaymentDao
 */
public class PaymentDaoImpl implements PaymentDao {

    Connection connection;

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addPayemnt(Payment payment) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO PAYMENTS VALUES (?,?,?,?,?,?)")) {
            prepStatement.setInt(1, payment.getId());
            prepStatement.setString(2, payment.getMemId());
            prepStatement.setString(3, payment.getTypeOfPayment());
            prepStatement.setFloat(4, payment.getAmount());
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
                    return CreatePayment(resultSet.getInt("id"), resultSet.getString("mem_id"),
                            resultSet.getString("type_of_payment").trim(), resultSet.getFloat("amount"),
                            resultSet.getDate("date").toLocalDate(), resultSet.getTime("time").toLocalTime());
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
            ArrayList paymentList = new ArrayList();
            while (resultSet.next()) {                
                paymentList.add(CreatePayment(resultSet.getInt("id"), resultSet.getString("mem_id"),
                            resultSet.getString("type_of_payment").trim(), resultSet.getFloat("amount"),
                            resultSet.getDate("date").toLocalDate(), resultSet.getTime("time").toLocalTime()));
            }
            
            return (Payment[]) paymentList.toArray();
        } catch (SQLException ex) {

        }
        
        return null;
    }

    @Override
    public boolean updatePayemnt(Payment payment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Payment CreatePayment(int id, String memId, String typeOfPayment, float amount, LocalDate date, LocalTime time) {
        Payment payment = new Payment();

        payment.setId(id);
        payment.setMemId(memId);
        payment.setTypeOfPayment(typeOfPayment.trim());
        payment.setAmount(amount);
        payment.setDate(date);
        payment.setTime(time);

        return payment;
    }

}
