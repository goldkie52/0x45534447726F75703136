package dao;

import model.Payment;

/**
 * Provides the interface for accessing payment data.
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 */
public interface PaymentDao {
    
    public boolean addPayemnt(Payment payment);
    
    public Payment getPayment(int id);
    
    public Payment[] getAllPayments();
    
    public boolean updatePayment(Payment payment);
}