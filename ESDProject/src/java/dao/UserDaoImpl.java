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
    
    @Override
    public boolean addUser(User user) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)")) {
            prepStatement.setString(1, user.getId());
            prepStatement.setString(2, user.getPassword());
            prepStatement.setString(3, user.getStatus().toString());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public User getUser(String id) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM USERS WHERE \"id\" = ?")) {
            prepStatement.setString(1, id);
            try (ResultSet result = prepStatement.executeQuery()) {
                if (result.next()) {
                    String password = result.getString("password");
                    String statusString = result.getString("status");
                    return createUser(id, password, statusString);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public User[] getAllUsers() {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM USERS");
                ResultSet result = prepStatement.executeQuery()) {
            ArrayList<User> users = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String password = result.getString("password");
                String statusString = result.getString("status");
                users.add(createUser(id, password, statusString));
            }
            return users.toArray(new User[0]);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean updateUser(User user) {
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE USERS SET \"password\" = ?, \"status\" = ? WHERE \"id\" = ?")) {
            prepStatement.setString(1, user.getPassword());
            prepStatement.setString(2, user.getStatus().toString());
            prepStatement.setString(3, user.getId());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private User createUser(String id, String password, String status) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setStatus(UserStatus.valueOf(status.toUpperCase().trim()));
        return user;
    }
    
    // </editor-fold>
    
}
