package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The Member class is the value object that represents a member in the database.
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 */
public class Member implements Serializable {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private String id;
    private String name;
    private String address;
    private MemberStatus status;
    private double balance;
    private LocalDate dob;
    private LocalDate dor;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Gets the value of the id property.
     * @return the id property
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Sets the value of the id property.
     * @param id the value to set the id property to
     */
    public void setId(String id) {
        this.id = id;
    }

     /**
     * Gets the value of the name property.
     * @return the name property
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets the value of the name property.
     * @param name the value to set the name property to
     */
    public void setName(String name) {
        this.name = name;
    }
    
     /**
     * Gets the value of the address property.
     * @return the address property
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * Sets the value of the address property.
     * @param address the value to set the name property to
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the value of the date of birth (dob) property.
     * @return the dob property
     */      
    public LocalDate getDob() {
        return this.dob;
    }
    
    /**
     * Sets the value of the dob property.
     * @param dob the value to set
     */  
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
     /**
     * Gets the value of the date of registration (dor) property.
     * @return the dor property
     */  
    public LocalDate getDor() {
        return this.dor;
    }
    
    /**
     * Sets the value of the dor property.
     * @param dor the value to set
     */
    public void setDor(LocalDate dor) {
        this.dor = dor;
    }
    
        /**
     * Gets the value of the status property.
     * @return the status property
     */    
    public MemberStatus getStatus() {
        return this.status;
    }
    
    /**
     * Sets the value of the status property.
     * @param status the value to set
     */
    public void setStatus(MemberStatus status) {
        this.status = status;
    }
    
    /**
     * Gets the value of the balance property.
     * @return the balance property
     */  
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * Sets the value of the balance property.
     * @param balance the value to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
       
    // <editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!Member.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        final Member member = (Member)o;
        if (!this.id.equals(member.id)) {
            return false;
        }
        if (!this.name.equals(member.name)) {
            return false;
        }
        if (!this.address.equals(member.address)) {
            return false;
        }
        if (!this.status.equals(member.status)) {
            return false;
        }
        if (this.balance != member.balance) {
            return false;
        }
        if (!this.dob.equals(member.dob)) {
            return false;
        }
        if (!this.dor.equals(member.dor)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.address);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.balance);
        hash = 71 * hash + Objects.hashCode(this.dob);
        hash = 71 * hash + Objects.hashCode(this.dor);
        return hash;
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}