package dao;

import model.Claim;

/**
 * Provides the interface for accessing claim data.
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 */
public interface ClaimDao {
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Adds a new claim to the system.
     * @param claim the member to add to the system
     * @return true if the claim was added; otherwise false
     */
    boolean addClaim(Claim claim);
    
    /**
     * Gets the claim with the specified id.
     * @param id the id of the claim to get
     * @return the claim with the specified id if the operation was a success; otherwise null
     */
    Claim getClaim(int id);
    
    /**
     * Gets all the claims in the system.
     * @return all claims in the system if the operation was a success; otherwise null
     */
    Claim[] getAllClaims();
    
    /**
     * Updates the specified claim in the system.
     * @param claim the claim to update
     * @return true if the update was successful, otherwise false
     */
    boolean updateClaim(Claim claim);
    
    /**
     * Gets all the claims with the specified memId
     * @param memId the id of the member to get the claims for
     * @return all claims in the system from specified member if the operation was a success; otherwise null
     */
    Claim[] getClaims(String memId);
    
    // </editor-fold>
    
}