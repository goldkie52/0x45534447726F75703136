package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Web service for processing and updating members.
 * @author James Broadberry 14007903
 * @author Matthew Carpenter 14012396
 */
@WebService(serviceName = "ProcessMember")
public class ProcessMember {

    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Approves the member with the specified id.
     * @param id the id of the member to approve
     * @return true if the member was updated successfully; otherwise false
     */
    @WebMethod(operationName = "approveMember")
    public boolean approveMember(@WebParam(name = "id") String id) {
        return update(id, "APPROVED");
    }
    
    /**
     * Rejects the member with the specified id.
     * @param id the id of the member to reject
     * @return true if the member was updated successfully; otherwise false
     */
    @WebMethod(operationName = "suspendMember")
    public boolean suspendMember(@WebParam(name = "id") String id) {
        return update(id, "SUSPENDED");
    }
    
    /**
     * Updates the member with the specified id to the specified status
     * @param id the id of the member to update
     * @param status the status the member will change to
     * @return 
     */
    private boolean update(String id, String status) {
        Connection connection;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");
            boolean success = true;
            try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE MEMBERS SET \"status\" = ? WHERE \"id\" = ?")) {
                prepStatement.setString(1, status);
                prepStatement.setString(2, id);
                success = success && prepStatement.executeUpdate() > 0;
            }
            try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE USERS SET \"status\" = ? WHERE \"id\" = ?")) {
                prepStatement.setString(1, status);
                prepStatement.setString(2, id);
                success = success && prepStatement.executeUpdate() > 0;
            }
            return success;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }
    
    // </editor-fold>
    
}
