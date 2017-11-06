package dao;

import model.User;

/**
 * Provides the interface for accessing user data.
 * @author Matthew Carpenter 14012396
 */
public interface UserDao {
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Adds a new user to the system, returning a reference to their details.
     * @param user the user to add to the system
     * @return true if the user was added; otherwise false
     */
    boolean addUser(User user);
    
    /**
     * Gets the user with the specified id.
     * @param id the id of the user to get
     * @return the user with the specified id if the operation was a success; otherwise null
     */
    User getUser(String id);
    
    /**
     * Gets all the users in the system.
     * @return all users in the system if the operation was a success; otherwise null
     */
    User[] getAllUsers();
    
    /**
     * Updates the specified user in the system.
     * @param user the user to update
     * @return true if the update was successful, otherwise false
     */
    boolean updateUser(User user);
    
    // </editor-fold>
    
}