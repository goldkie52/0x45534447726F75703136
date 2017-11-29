package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Web service for processing and updating claims.
 * @author Matthew Carpenter 14012396
 */
@WebService(serviceName = "ProcessClaim")
public class ProcessClaim {
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Approves the claim with the specified id.
     * @param id the id of the claim to approve
     * @return true if the claim was updated successfully; otherwise false
     */
    @WebMethod(operationName = "approve")
    public boolean approve(@WebParam(name = "id") int id) {
        return update(id, "APPROVED");
    }
    
    /**
     * Rejects the claim with the specified id.
     * @param id the id of the claim to reject
     * @return true if the claim was updated successfully; otherwise false
     */
    @WebMethod(operationName = "reject")
    public boolean reject(@WebParam(name = "id") int id) {
        return update(id, "REJECTED");
    }
    
    /**
     * Updates the claim with the specified id to the specified status
     * @param id the id of the claim to update
     * @param status the status the claim will change to
     * @return 
     */
    private boolean update(int id, String status) {
        Connection connection;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");
            try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE CLAIMS SET \"status\" = ? WHERE \"id\" = ?")) {
                prepStatement.setString(1, status);
                prepStatement.setInt(2, id);
                return prepStatement.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }
    
    // </editor-fold>
    
}
