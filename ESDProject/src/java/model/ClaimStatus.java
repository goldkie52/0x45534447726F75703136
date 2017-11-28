package model;

/**
 * Represents the status of a claim.
 *
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 * @author Rachel Bailey 13006455
 */
public enum ClaimStatus {
    
    /**
     * Indicates that the member's claim has been approved.
     */
    APPROVED,
    
    /**
     * Indicates that the claim is pending, yet to be approved.
     */
    PENDING,
    
    /**
     * Indicates that the claim has been rejected.
     */
    REJECTED,
    
}
