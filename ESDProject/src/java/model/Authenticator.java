package model;

import dao.UserDao;
import dao.UserDaoImpl;
import java.sql.Connection;

/**
 * Authenticates a login request.
 * @author Matthew Carpenter 14012396
 * @author James Broadberry 14007903
 */
public class Authenticator {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Initialises a new instance of the Authenticator class.
     * @param connection the connection to the database
     */
    public Authenticator(Connection connection) {
        this.connection = connection;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Authenticates the provided details.
     * @param username the username to authenticate
     * @param password the password to use for authentication
     * @return a LoginResult holding the result of the authentication
     */
    public LoginResult authenticate(String username, String password) {
        LoginResult result = new LoginResult();
        result.setValid(false);
        result.setUser(null);
        
        UserDao userDao = new UserDaoImpl(connection);
        User user = userDao.getUser(username);
        
        if (user != null) {
            if (password.equals(user.getPassword())) {
                result.setValid(true);
                result.setUser(user);
            }
        }
        
        return result;
    }
    
    // </editor-fold>
    
}
