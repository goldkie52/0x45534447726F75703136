package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * The Payment class is the value object that represents a claim in the database.
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 * @author Rachel Bailey 13006455
 */
public class Payment implements Serializable {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private int id;
    private String memId;
    private String typeOfPayment;
    private double amount;
    private LocalDate date;
    private LocalTime time;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
     /**
     * Gets the value of the id property.
     * @return the id property
     */
    public int getId() {
        return id;
    }
    
     /**
     * Sets the value of the id property.
     * @param id the value to set the id property to
     */
    public void setId(int id) {
        this.id = id;
    }
    
     /**
     * Gets the value of the member id (memId) property.
     * @return the memId property
     */
    public String getMemId() {
        return memId;
    }

     /**
     * Sets the value of the memId property.
     * @param mem_id the value to set the memId property to
     */
    public void setMemId(String mem_id) {
        this.memId = mem_id;
    }

     /**
     * Gets the value of the typeOfPayment property.
     * @return the status property
     */
    public String getTypeOfPayment() {
        return typeOfPayment;
    }

     /**
     * Sets the value of the typeOfPayment property.
     * @param type_of_payment the value to set the typeOfPayment property to
     */
    public void setTypeOfPayment(String type_of_payment) {
        this.typeOfPayment = type_of_payment;
    }

     /**
     * Gets the value of the amount property.
     * @return the amount property
     */
    public double getAmount() {
        return amount;
    }

     /**
     * Sets the value of the amount property.
     * @param amount the value to set the amount property to
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

     /**
     * Gets the value of the date property.
     * @return the date property
     */    
    public LocalDate getDate() {
        return date;
    }

     /**
     * Sets the value of the date property.
     * @param date the value to set
     */  
    public void setDate(LocalDate date) {
        this.date = date;
    }

     /**
     * Gets the value of the time of payment property.
     * @return the time property
     */ 
    public LocalTime getTime() {
        return time;
    }

     /**
     * Sets the value of the time property.
     * @param time the value to set
     */  
    public void setTime(LocalTime time) {
        this.time = time;
    }

    
    // <editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payment other = (Payment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (!Objects.equals(this.memId, other.memId)) {
            return false;
        }
        if (!Objects.equals(this.typeOfPayment, other.typeOfPayment)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }
   
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.memId);
        hash = 67 * hash + Objects.hashCode(this.typeOfPayment);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.time);
        return hash;
    }
    
    // </editor-fold>
    
    // </editor-fold>

}