/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 */
@WebService(serviceName = "ProcessMember")
public class ProcessMember {

    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Approves the member with the specified id.
     * @param id the id of the member to approve
     * @return true if the member was updated successfully; otherwise false
     */
    @WebMethod(operationName = "approvemember")
    public boolean approvemember(@WebParam(name = "id") String id) {
        return update(id, "APPROVED");
    }
    
    /**
     * Rejects the member with the specified id.
     * @param id the id of the member to reject
     * @return true if the member was updated successfully; otherwise false
     */
    @WebMethod(operationName = "suspendmember")
    public boolean suspendmember(@WebParam(name = "id") String id) {
        return update(id, "SUSPENDED");
    }
    
    private boolean update(String id, String status) {
        Connection connection;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");
            try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE MEMBERS SET \"status\" = ? WHERE \"id\" = ?")) {
                prepStatement.setString(1, status);
                prepStatement.setString(2, id);
                return prepStatement.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }
    
    // </editor-fold>
    
}
