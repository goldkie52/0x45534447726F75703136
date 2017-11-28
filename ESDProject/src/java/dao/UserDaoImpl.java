package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.UserStatus;

/**
 * Provides the implementation for accessing user data from the database.
 * @author Matthew Carpenter 14012396
 * @see dao.UserDao
 */
public class UserDaoImpl implements UserDao {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * Initialises a new instance of the UserDaoImpl class.
     * @param connection the connection to the database
     */
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Adds a new user to the system.
     * @param user the user to add to the system
     * @return true if the user was added; otherwise false
     */
    @Override
    public boolean addUser(User user) {
        // Create prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)")) {
            // Set values on statement
            prepStatement.setString(1, user.getId());
            prepStatement.setString(2, user.getPassword());
            prepStatement.setString(3, user.getStatus().toString());
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }
    
    /**
     * Gets the user with the specified id.
     * @param id the id of the user to get
     * @return the user with the specified id if the operation was a success; otherwise null
     */
    @Override
    public User getUser(String id) {
        // Create prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM USERS WHERE \"id\" = ?")) {
            // Set value on statement
            prepStatement.setString(1, id);
            // Execute the statement and pull out the result set
            try (ResultSet result = prepStatement.executeQuery()) {
                // If there is a result present
                if (result.next()) {
                    // Pull values from row
                    String password = result.getString("password");
                    String statusString = result.getString("status");
                    // Create User object with values and return
                    return createUser(id, password, statusString);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }
    
    /**
     * Gets all the users in the system.
     * @return all users in the system if the operation was a success; otherwise null
     */
    @Override
    public User[] getAllUsers() {
        // Create the prepared statement and execute it, pulling out the result set
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM USERS");
                ResultSet result = prepStatement.executeQuery()) {
            ArrayList<User> users = new ArrayList<>();
            // While there are more rows in the result set
            while (result.next()) {
                // Pull values from row
                String id = result.getString("id");
                String password = result.getString("password");
                String statusString = result.getString("status");
                // Create User object with values and add to list
                users.add(createUser(id, password, statusString));
            }
            // Convert list to array and return
            return users.toArray(new User[0]);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }
    
    /**
     * Updates the specified user in the system.
     * @param user the user to update
     * @return true if the update was successful, otherwise false
     */
    @Override
    public boolean updateUser(User user) {
        // Create prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE USERS SET \"password\" = ?, \"status\" = ? WHERE \"id\" = ?")) {
            // Set values on statement
            prepStatement.setString(1, user.getPassword());
            prepStatement.setString(2, user.getStatus().toString());
            prepStatement.setString(3, user.getId());
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }
    
    private User createUser(String id, String password, String status) {
        // Create new User object
        User user = new User();
        // Set values on object
        user.setId(id);
        user.setPassword(password);
        user.setStatus(UserStatus.valueOf(status.toUpperCase().trim()));
        // Return object
        return user;
    }
    
    // </editor-fold>
    
}
