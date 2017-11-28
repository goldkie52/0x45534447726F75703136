package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The Claim class is the value object that represents a claim in the database.
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 * @author Rachel Bailey 13006455
 */
public class Claim {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private int id;
    private String memId;
    private LocalDate date;
    private String rationale;
    private ClaimStatus status;
    private double amount;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Gets the value of the id property.
     * @return the id property
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Sets the value of the id property.
     * @param id the value to set the id property to
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets the value of the memId (member ID) property.
     * @return the memId property
     */
    public String getMemId() {
        return this.memId;
    }
    
    /**
     * Sets the value of the memId property.
     * @param memId the value to set the memId property to
     */
    public void setMemId(String memId) {
        this.memId = memId;
    }
    
    /**
     * Gets the value of the date of claim property.
     * @return the date property
     */
    public LocalDate getDate(){
        return this.date;
    }
    
    /**
     * Sets the value of the date property.
     * @param date the value to set the date property to
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    /**
     * Gets the value of the rationale of the claim property.
     * @return the rationale property
     */
    public String getRationale() {
        return this.rationale;
    }
    
    /**
     * Sets the value of the rationale property.
     * @param rationale the value to set the rationale property to
     */
    public void setRationale(String rationale) {
        this.rationale = rationale;
    }
    
    /**
     * Gets the value of the status of the claim property.
     * @return the status property
     */
    public ClaimStatus getStatus() {
        return this.status;
    }
    
    /**
     * Sets the value of the status property.
     * @param status the value to set the status property to
     */
    public void setStatus(ClaimStatus status) {
        this.status = status;
    }
    
    /**
     * Gets the value of the amount the claim is asking for property.
     * @return the amount property
     */
    public double getAmount() {
        return this.amount;
    }
    
    /**
     * Sets the value of the amount property.
     * @param amount the value to set the amount property to
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!Claim.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        final Claim claim = (Claim)o;
        if (this.id != claim.id) {
            return false;
        }
        if (!this.memId.equals(claim.memId)) {
            return false;
        }
        if (!this.date.equals(claim.date)) {
            return false;
        }
        if (!this.rationale.equals(claim.rationale)) {
            return false;
        }
        if (!this.status.equals(claim.status)) {
            return false;
        }
        if (this.amount != claim.amount) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.memId);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.rationale);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        return hash;
    }
    
    // </editor-fold>
    
    // </editor-fold>

}