package model;

/**
 * Holds the information relating to a login request.
 * @author Matthew Carpenter 14012396
 */
public class LoginResult {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private boolean valid;
    private User user;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Gets the value of the valid property.
     * @return the valid property
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the value of the valid property.
     * @param valid the value to set the valid property to
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * Gets the value of the user property.
     * @return the user property
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * @param user the value to set the user property to
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    // </editor-fold>
    
}
