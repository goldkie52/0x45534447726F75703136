package model;

/**
 * Holds the information relating to a registration request.
 * @author Matthew Carpenter 14012396
 */
public class SignupResult {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private boolean userValid;
    private boolean dobValid;
    private boolean connectionError;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Gets the validity of the registration request. 
     * @return true if the registration request was valid; otherwise false
     */
    public boolean isRequestValid() {
        return this.userValid && this.dobValid;
    }
    
    /**
     * Gets the value of the userValid property.
     * @return the userValid property
     */
    public boolean isUserValid() {
        return userValid;
    }

    /**
     * Sets the value of the userValid property.
     * @param userValid the value to set the userValid property to
     */
    public void setUserValid(boolean userValid) {
        this.userValid = userValid;
    }

    /**
     * Gets the value of the dobValid property.
     * @return the dobValid property
     */
    public boolean isDobValid() {
        return dobValid;
    }

    /**
     * Sets the value of the dobValid property.
     * @param dobValid the value to set the dobValid property to
     */
    public void setDobValid(boolean dobValid) {
        this.dobValid = dobValid;
    }
    
    /**
     * Gets the value of the connectionError property.
     * @return the connectionError property
     */
    public boolean isConnectionError() {
        return connectionError;
    }

    /**
     * Sets the value of the connectionError property.
     * @param connectionError the value to set the connectionError property to
     */
    public void setConnectionError(boolean connectionError) {
        this.connectionError = connectionError;
    }
    
    // </editor-fold>
    
}
