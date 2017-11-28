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
import model.Claim;
import model.ClaimStatus;

/**
 * Provides the implementation for accessing claim data from the database.
 *
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 * @author Rachel Bailey 13006455
 * @see dao.ClaimDao
 */
public class ClaimDaoImpl implements ClaimDao {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Initializes a new instance of the ClaimDaoImpl class.
     * @param connection the connection to the database
     */
    public ClaimDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Adds a new claim to the system.
     * @param claim the member to add to the system
     * @return true if the claim was added; otherwise false
     */
    @Override
    public boolean addClaim(Claim claim) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO CLAIMS VALUES (?, ?, ?, ?, ?, ?)")) {
            // Set values on statement
            prepStatement.setInt(1, claim.getId());
            prepStatement.setString(2, claim.getMemId());
            prepStatement.setDate(3, Date.valueOf(claim.getDate()));
            prepStatement.setString(4, claim.getRationale());
            prepStatement.setString(5, claim.getStatus().toString());
            prepStatement.setDouble(6, claim.getAmount());
            // Execute the statement and return a boolean representing whether any rows were added or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }

    /**
     * Gets the claim with the specified id.
     * @param id the id of the claim to get
     * @return the claim with the specified id if the operation was a success; otherwise null
     */
    @Override
    public Claim getClaim(int id) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS WHERE \"id\" = ?")) {
            // Set value on statement
            prepStatement.setString(1, Integer.toString(id));
            // Execute the statement and pull out the result set
            try (ResultSet result = prepStatement.executeQuery()) {
                // If there is a result present
                if (result.next()) {
                    // Pull values from row
                    String memId = result.getString("mem_id");
                    LocalDate date = result.getDate("date").toLocalDate();
                    String rationale = result.getString("rationale");
                    String status = result.getString("status");
                    double amount = result.getDouble("amount");
                    // Create Claim object with values and return
                    return createClaim(id, memId, date, rationale, status, amount);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Gets all the claims in the system.
     * @return an array of the claims in the system if successful; otherwise null
     */
    @Override
    public Claim[] getAllClaims() {
        // Create the prepared statement and execute it, pulling out the result set
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS");
                ResultSet result = prepStatement.executeQuery()) {
            ArrayList<Claim> claims = new ArrayList<>();
            // While there are more rows in the result set
            while (result.next()) {
                // Pull values from row
                int id = result.getInt("id");
                String memId = result.getString("mem_id");
                LocalDate date = result.getDate("date").toLocalDate();
                String rationale = result.getString("rationale");
                String status = result.getString("status");
                double amount = result.getDouble("amount");
                // Create Claim object with values and add to list
                claims.add(createClaim(id, memId, date, rationale, status, amount));
            }
            // Convert list to array and return
            return claims.toArray(new Claim[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }

    /**
     * Updates the specified claim in the system.
     * @param claim the claim to update
     * @return true if the update was successful, otherwise false
     */
    @Override
    public boolean updateClaim(Claim claim) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE CLAIMS SET \"mem_id\" = ?, \"date\" = ?, \"rationale\" = ?, \"status\" = ?, \"amount\" = ? WHERE \"id\" = ?")) {
            // Set values on statement
            prepStatement.setString(1, claim.getMemId());
            prepStatement.setDate(2, Date.valueOf(claim.getDate().toString()));
            prepStatement.setString(3, claim.getRationale());
            prepStatement.setString(4, claim.getStatus().toString());
            prepStatement.setDouble(5, claim.getAmount());
            prepStatement.setInt(6, claim.getId());
            // Execute the statement and return a boolean representing whether any rows were changed or not
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false if there is an issue with the database interaction
        return false;
    }

    /**
     * Gets all the claims with the specified memId
     * @param memId the id of the member to get the claims for
     * @return an array of the claims in the system for the specified member id if successful; otherwise null
     */
    @Override
    public Claim[] getClaims(String memId) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS WHERE \"mem_id\" = ?")) {
            // Set value on statement
            prepStatement.setString(1, memId);
            // Execute the statement and pull out the result set
            try (ResultSet result = prepStatement.executeQuery()) {
                ArrayList<Claim> claims = new ArrayList<>();
                // While there are more rows in the result set
                while (result.next()) {
                    // Pull values from row
                    int id = result.getInt("id");
                    LocalDate date = result.getDate("date").toLocalDate();
                    String rationale = result.getString("rationale");
                    String status = result.getString("status");
                    double amount = result.getDouble("amount");
                    // Create Claim object with values and add to list
                    claims.add(createClaim(id, memId, date, rationale, status, amount));
                }
                // Convert list to array and return
                return claims.toArray(new Claim[0]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }
    
    /**
     * Gets all the claims that were added after the specified date
     * @param date the date to retrieve claims after
     * @return an array of the claims in the system after the date if successful; otherwise null
     */
    @Override
    public Claim[] getClaimsFromDate(LocalDate date) {
        // Create the prepared statement
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS WHERE \"date\" > ?")) {
            // Set value on statement
            prepStatement.setDate(1, Date.valueOf(date));
            // Execute the statement and pull out the result set
            try (ResultSet result = prepStatement.executeQuery()) {
                ArrayList<Claim> claims = new ArrayList<>();
                // While there are more rows in the result set
                while (result.next()) {
                    // Pull values from row
                    int id = result.getInt("id");
                    String memId = result.getString("mem_id");
                    LocalDate currentDate = result.getDate("date").toLocalDate();
                    String rationale = result.getString("rationale");
                    String status = result.getString("status");
                    double amount = result.getDouble("amount");
                    // Create Claim object with values and add to list
                    claims.add(createClaim(id, memId, currentDate, rationale, status, amount));
                }
                // Convert list to array and return
                return claims.toArray(new Claim[0]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if there is an issue with the database interaction
        return null;
    }
    
    /**
     * Gets the next open id for a claim
     * @return the next valid id
     */
    @Override
    public int getNextId() {
        // Get all claims in the system
        Claim[] allClaims = getAllClaims();
        int maxId = -1;
        // Loop over each claim to calculate the highest id
        for (Claim claim : allClaims) {
            if (claim.getId() > maxId) {
                maxId = claim.getId();
            }
        }
        // Return the next valid id; the current maximum + 1
        return maxId + 1;
    }

    private Claim createClaim(int id, String memId, LocalDate date, String rationale, String status, double amount) {
        // Create new Claim object
        Claim claim = new Claim();
        // Set values on object
        claim.setId(id);
        claim.setMemId(memId);
        claim.setDate(date);
        claim.setRationale(rationale);
        claim.setStatus(ClaimStatus.valueOf(status.toUpperCase().trim()));
        claim.setAmount(amount);
        // Return object
        return claim;
    }

    // </editor-fold>
    
}
