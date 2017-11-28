package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Member;
import model.MemberStatus;

/**
 * Provides the implementation for accessing member data from the database.
 *
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 * @see dao.MemberDao
 */
public class MemberDaoImpl implements MemberDao {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Initializes a new instance of the MemberDaoImpl class.
     * @param connection the connection to the database
     */
    public MemberDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Adds a new member to the system.
     * @param member the member to add to the system
     * @return true if the member was added; otherwise false
     */
    @Override
    public boolean addMember(Member member) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO MEMBERS VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            // Set values on statement
            prepStatement.setString(1, member.getId());
            prepStatement.setString(2, member.getName());
            prepStatement.setString(3, member.getAddress());
            prepStatement.setDate(4, Date.valueOf(member.getDob().toString()));
            prepStatement.setDate(5, Date.valueOf(member.getDor().toString()));
            prepStatement.setString(6, member.getStatus().toString());
            prepStatement.setDouble(7, member.getBalance());
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }

    /**
     * Gets the member with the specified id.
     * @param id the id of the member to get
     * @return the member with the specified id if the operation was a success; otherwise null
     */
    @Override
    public Member getMember(String id) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS WHERE \"id\" = ?")) {
            // Set value on statement
            prepStatement.setString(1, id);
            // Execute the statement and pull out the result set
            try (ResultSet result = prepStatement.executeQuery()) {
                // If there is a result present
                if (result.next()) {
                    // Pull values from row
                    String name = result.getString("name");
                    String address = result.getString("address");
                    LocalDate dob = result.getDate("dob").toLocalDate();
                    LocalDate dor = result.getDate("dor").toLocalDate();
                    String statusString = result.getString("status");
                    double balanceString = result.getDouble("balance");
                    // Create Member object with values and return
                    return createMember(id, name, address, dob, dor, statusString, balanceString);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Gets all the members in the system.
     * @return an array of all members in the system if successful; otherwise null
     */
    @Override
    public Member[] getAllMembers() {
        // Create the prepared statement and execute it, pulling out the result set
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS");
                ResultSet result = prepStatement.executeQuery()) {
            ArrayList<Member> members = new ArrayList<>();
            // While there are more rows in the result set
            while (result.next()) {
                // Pull values from row
                String id = result.getString("id");
                String name = result.getString("name");
                String address = result.getString("address");
                LocalDate dob = result.getDate("dob").toLocalDate();
                LocalDate dor = result.getDate("dor").toLocalDate();
                String statusString = result.getString("status");
                double balanceString = result.getDouble("balance");
                // Create Member object with values and add to list
                members.add(createMember(id, name, address, dob, dor, statusString, balanceString));
            }
            // Convert list to array and return
            return members.toArray(new Member[0]);
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Gets all the verified members in the system.
     * @param status the status of the member
     * @return an array of the members in the system with the specified status if successful; otherwise null
     */
    @Override
    public Member[] getMembers(MemberStatus status) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS WHERE \"status\" = ?")) {
            // Set value on statement
            prepStatement.setString(1, status.toString());
            ArrayList<Member> members = new ArrayList<>();
            // Execute the statement and pull out the result set
            try (ResultSet result = prepStatement.executeQuery()) {
                // While there are more rows in the result set
                while (result.next()) {
                    // Pull values from row
                    String id = result.getString("id");
                    String name = result.getString("name");
                    String address = result.getString("address");
                    LocalDate dob = result.getDate("dob").toLocalDate();
                    LocalDate dor = result.getDate("dor").toLocalDate();
                    double balanceString = result.getDouble("balance");
                    // Create Member object with values and add to list
                    members.add(createMember(id, name, address, dob, dor, status.toString(), balanceString));
                }
            }
            // Convert list to array and return
            return members.toArray(new Member[0]);
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Updates the specified member in the system.
     * @param member the member to update
     * @return true if the update was successful, otherwise false
     */
    @Override
    public boolean updateMember(Member member) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE MEMBERS SET \"name\" = ?, \"address\" = ?, \"dob\" = ?, \"dor\" = ?, \"status\" = ?, \"balance\" = ? WHERE \"id\" = ?")) {
            // Set values on statement
            prepStatement.setString(1, member.getName());
            prepStatement.setString(2, member.getAddress());
            prepStatement.setDate(3, Date.valueOf(member.getDob()));
            prepStatement.setDate(4, Date.valueOf(member.getDor()));
            prepStatement.setString(5, member.getStatus().toString());
            prepStatement.setDouble(6, member.getBalance());
            prepStatement.setString(7, member.getId());
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }

    private Member createMember(String id, String name, String address, LocalDate dob, LocalDate dor, String status, double balance) {
        // Create new Member object
        Member member = new Member();
        // Set values on object
        member.setId(id);
        member.setName(name);
        member.setAddress(address);
        member.setDob(dob);
        member.setDor(dor);
        member.setStatus(MemberStatus.valueOf(status.toUpperCase().trim()));
        member.setBalance(balance);
        // Return object
        return member;
    }

    // </editor-fold>
    
}
