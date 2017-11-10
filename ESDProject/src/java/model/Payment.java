package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * The Payment class is the value object that represents a claim in the database.
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 */
public class Payment implements Serializable {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private int id;
    private String memId;
    private String typeOfPayment;
    private float amount;
    private LocalDate date;
    private LocalTime time;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String mem_id) {
        this.memId = mem_id;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String type_of_payment) {
        this.typeOfPayment = type_of_payment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
    
    // </editor-fold>

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.memId);
        hash = 67 * hash + Objects.hashCode(this.typeOfPayment);
        hash = 67 * hash + Float.floatToIntBits(this.amount);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.time);
        return hash;
    }

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
        if (Float.floatToIntBits(this.amount) != Float.floatToIntBits(other.amount)) {
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
    
}