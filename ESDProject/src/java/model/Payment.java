package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
    
}