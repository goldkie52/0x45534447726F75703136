package dao;

import model.Payment;

/**
 * Provides the interface for accessing payment data.
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 * @author Rachel Bailey 13006455
 */
public interface PaymentDao {

// <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Adds a new payment to the system.
     * @param payment the payment to add to the system
     * @return true if the payment was added; otherwise false
     */
    boolean addPayment(Payment payment);
    
    /**
     * Gets the payment with the specified id.
     * @param payment the id of the payment to get
     * @return the member with the specified id if the operation was a success; otherwise null
     */
    public Payment getPayment(int id);
    
    /**
     * Gets all the payments in the system.
     * @return all payments in the system if the operation was a success; otherwise null
     */
    public Payment[] getAllPayments();
    
    /**
     * Gets all the Payments for a specified memId
     * @return all payments  for a specified memId if the operation was a success; otherwise null
     */
    public Payment[] getPaymentsForMember(String memId);
    
    /**
     * Updates the specified payment in the system.
     * @param payment the payment to update
     * @return true if the update was successful, otherwise false
     */
    public boolean updatePayment(Payment payment);
    
    // </editor-fold>

}