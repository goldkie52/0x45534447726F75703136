package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The User class is the value object that represents a claim in the database.
 * @author Matthew Carpenter 14012396
 */
public class User implements Serializable {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private String id;
    private String password;
    private UserStatus status;
    
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
     * Gets the value of the password property.
     * @return the password property
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Sets the value of the password property.
     * @param password the value to set the password property to
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Gets the value of the status property.
     * @return the status property
     */
    public UserStatus getStatus() {
        return this.status;
    }
    
    /**
     * Sets the value of the status property.
     * @param status the value to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!User.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        final User user = (User)o;
        if (this.id != user.id) {
            return false;
        }
        if (this.password != user.password) {
            return false;
        }
        if (this.status != user.status) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.password);
        hash = 71 * hash + Objects.hashCode(this.status);
        return hash;
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}