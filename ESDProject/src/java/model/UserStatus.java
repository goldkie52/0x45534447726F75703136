package model;

/**
 * Represents the status of a user.
 * @author Matthew Carpenter 14012396
 */
public enum UserStatus {

    /**
     * Indicates that the user is an administrator.
     */
    ADMIN,

    /**
     * Indicates that the user has not been approved yet.
     */
    APPLIED,

    /**
     * Indicates that the user has been approved.
     */
    APPROVED,
    
    /**
     * Indicates that the user has been suspended.
     */
    SUSPENDED
    
}