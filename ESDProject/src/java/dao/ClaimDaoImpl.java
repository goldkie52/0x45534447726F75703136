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
 * @see dao.ClaimDao
 */
public class ClaimDaoImpl implements ClaimDao {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Initializes a new instance of the ClaimDaoImpl class.
     *
     * @param connection the connection to the database
     */
    public ClaimDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public boolean addClaim(Claim claim) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO CLAIMS VALUES (?, ?, ?, ?, ?, ?)")) {
            prepStatement.setInt(1, claim.getId());
            prepStatement.setString(2, claim.getMemId());
            prepStatement.setDate(3, Date.valueOf(claim.getDate()));
            prepStatement.setString(4, claim.getRationale());
            prepStatement.setString(5, claim.getStatus().toString());
            prepStatement.setDouble(6, claim.getAmount());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Claim getClaim(int id) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS WHERE \"id\" = ?")) {
            prepStatement.setString(1, Integer.toString(id));
            try (ResultSet result = prepStatement.executeQuery()) {
                if (result.next()) {
                    String memId = result.getString("mem_id");
                    LocalDate date = result.getDate("date").toLocalDate();
                    String rationale = result.getString("rationale");
                    String status = result.getString("status");
                    double amount = result.getDouble("amount");
                    return createClaim(id, memId, date, rationale, status, amount);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Claim[] getAllClaims() {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS");
                ResultSet result = prepStatement.executeQuery()) {
            ArrayList<Claim> claims = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String memId = result.getString("mem_id");
                LocalDate date = result.getDate("date").toLocalDate();
                String rationale = result.getString("rationale");
                String status = result.getString("status");
                double amount = result.getDouble("amount");
                claims.add(createClaim(id, memId, date, rationale, status, amount));
            }
            return claims.toArray(new Claim[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateClaim(Claim claim) {
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE CLAIMS SET \"mem_id\" = ?, \"date\" = ?, \"rationale\" = ?, \"status\" = ?, \"amount\" = ? WHERE \"id\" = ?")) {
            prepStatement.setString(1, claim.getMemId());
            prepStatement.setDate(2, Date.valueOf(claim.getDate().toString()));
            prepStatement.setString(3, claim.getRationale());
            prepStatement.setString(4, claim.getStatus().toString());
            prepStatement.setDouble(5, claim.getAmount());
            prepStatement.setInt(6, claim.getId());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Claim[] getClaims(String memId) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS WHERE \"mem_id\" = ?")) {
            prepStatement.setString(1, memId);
            ResultSet result = prepStatement.executeQuery();
            ArrayList<Claim> claims = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                LocalDate date = result.getDate("date").toLocalDate();
                String rationale = result.getString("rationale");
                String status = result.getString("status");
                double amount = result.getDouble("amount");
                claims.add(createClaim(id, memId, date, rationale, status, amount));
            }
            return claims.toArray(new Claim[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Claim createClaim(int id, String memId, LocalDate date, String rationale, String status, double amount) {
        Claim claim = new Claim();
        claim.setId(id);
        claim.setMemId(memId);
        claim.setDate(date);
        claim.setRationale(rationale);
        claim.setStatus(ClaimStatus.valueOf(status.toUpperCase().trim()));
        claim.setAmount(amount);
        return claim;
    }

    // </editor-fold>
}
