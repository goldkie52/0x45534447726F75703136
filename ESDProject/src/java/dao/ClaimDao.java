package dao;

import java.time.LocalDate;
import model.Claim;

/**
 * Provides the interface for accessing claim data.
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 * @author Rachel Bailey 13006455
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
     * @return an array of the claims in the system if successful; otherwise null
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
     * @return an array of the claims in the system for the specified member id if successful; otherwise null
     */
    Claim[] getClaims(String memId);
    
    /**
     * Gets all the claims that were added after the specified date
     * @param date the date to retrieve claims after
     * @return an array of the claims in the system after the date if successful; otherwise null
     */
    Claim[] getClaimsFromDate(LocalDate date);
    
    /**
     * Gets the next open id for a claim
     * @return the next valid id
     */
    int getNextId();
    
    // </editor-fold>
    
}