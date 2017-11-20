package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Claim;
import model.ClaimStatus;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import test.BaseDbTestClass;

/**
 * Tests the ClaimDaoImpl class.
 * @author Charlotte Harris 14008503
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 */
public class ClaimDaoImplTest  extends BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ClaimDaoImplTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of addClaim method, of class ClaimDaoImpl, when the claim does not already exist in the database.
     */
    @Test
    public void testAddClaim_ClaimNotInDatabase() {
        Claim claim = createClaim(0, "memId", LocalDate.now(), "rationale", 10.10);
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.addClaim(claim);
        assertEquals(expResult, result);
        assertEquals(true, isClaimPresentInTestDatabase(claim));
    }
    
    /**
     * Test of getClaim method, of class ClaimDaoImpl, when the claim exists in the database.
     */
    @Test
    public void testGetClaim_ClaimExists() {
        int id  = 0;
        Claim expResult = createClaim(id, "memId", LocalDate.now(), "rationale", 10.10);
        assertTrue(addClaimToTestDatabase(expResult));
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim result = instance.getClaim(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getClaim method, of class ClaimDaoImpl, when the claim does not exist in the database.
     */
    @Test
    public void testGetClaim_ClaimDoesNotExist() {
        int id  = 0;
        Claim expResult = null;
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim result = instance.getClaim(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllClaims method, of class ClaimDaoImpl, when there are claims in the database.
     */
    @Test
    public void testGetAllClaims_ClaimsPresent() {
        int[] id = new int[] { 0,1,2,3 };
        String[] memIds = new String[] { "id1", "id2", "id3", "id4" };
        LocalDate[] dates = new LocalDate[] {LocalDate.parse("2015-01-07"), LocalDate.parse("2015-01-07"), LocalDate.parse("2015-01-07"), LocalDate.parse("2015-01-07")};
        String[] rationales = new String[] { "rationale1", "rationale2", "rationale3", "rationale4" };
        double [] amount = new double [] {10.01, 10.02, 10.03, 10.04};
        Claim[] expResult = new Claim[id.length];
        for (int i = 0; i < id.length; i++) {
            Claim claim = createClaim(id[i], memIds[i], dates[i], rationales[i], amount[i]);
            expResult[i] = claim;
            addClaimToTestDatabase(claim);
        }
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim[] result = instance.getAllClaims();
        assertArrayEquals(expResult, result);
    }
    
        /**
     * Test of getAllClaimsFromDate method, of class ClaimDaoImpl, when there are claims in the database for current year.
     */
    @Test
    public void testGetAllClaimsFromDate_ClaimsPresent() {
        int[] id = new int[] { 0,1,2,3 };
        String[] memIds = new String[] { "id1", "id2", "id3", "id4" };
        LocalDate[] dates = new LocalDate[] {LocalDate.parse("2017-01-07"), LocalDate.parse("2017-01-07"), LocalDate.parse("2017-01-07"), LocalDate.parse("2017-01-07")};
        String[] rationales = new String[] { "rationale1", "rationale2", "rationale3", "rationale4" };
        double [] amount = new double [] {10.01, 10.02, 10.03, 10.04};
        Claim[] expResult = new Claim[id.length];
        for (int i = 0; i < id.length; i++) {
            Claim claim = createClaim(id[i], memIds[i], dates[i], rationales[i], amount[i]);
            expResult[i] = claim;
            addClaimToTestDatabase(claim);
        }
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim[] result = instance.getClaimsFromDate(LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1));
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of getAllClaims method, of class ClaimDaoImpl, when there are no claims in the database.
     */
    @Test
    public void testGetAllClaims_NoClaims() {
        Claim[] expResult = new Claim[0];
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim[] result = instance.getAllClaims();
        assertArrayEquals(expResult, result);
    }
    
        /**
     * Test of getAllClaimsFromDate method, of class ClaimDaoImpl, when there are no claims in the database from current year.
     */
    @Test
    public void testGetAllClaimsFromDate_NoClaims() {
        Claim[] expResult = new Claim[0];
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim[] result = instance.getClaimsFromDate(LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1));
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of updateMember method, of class MemberDaoImpl, when the member exists in the database.
     */
    @Test
    public void testUpdateClaim_ClaimExists() {
        int id = 0;
        Claim claim = createClaim(id, "memId", LocalDate.now(), "rationale", 10.10);
        addClaimToTestDatabase(claim);
        claim.setStatus(ClaimStatus.PENDING);
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.updateClaim(claim);
        assertEquals(expResult, result);
        isClaimPresentInTestDatabase(claim);
    }
    
    /**
     * Test of updateClaim method, of class ClaimDaoImpl, when the claim does not exist in the database.
     */
    @Test
    public void testUpdateClaim_ClaimDoesNotExist() {
        int id = 0;
        Claim claim = createClaim(id, "memId", LocalDate.now(), "rationale", 10.10);
        claim.setStatus(ClaimStatus.REJECTED);
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.updateClaim(claim);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllClaims method, of class ClaimDaoImpl, when there are claims for a member in the database.
     */
    @Test
    public void testGetClaims_ClaimsPresent() {
        /*String id  = "0";
        Claim expResult = createClaim(Integer.parseInt(id), "memId", LocalDate.now(), "rationale", 10.10);
        assertTrue(addClaimToTestDatabase(expResult));
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim result = instance.getClaim(id);
        assertEquals(expResult, result);*/
        
        
        int[] id= new int[] { 0,1,2,3 };
        String memId = "memId";
        LocalDate[] dates = new LocalDate [] {LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now()};
        String[] rationales = new String[] { "rationale1", "rationale2", "rationale3", "rationale4" };
        double [] amount = new double [] {10.01, 10.02, 10.03, 10.04};
        Claim[] expResult = new Claim[id.length];
        for (int i = 0; i < id.length; i++) {
            Claim claim = createClaim(id[i], memId, dates[i], rationales[i], amount[i]);
            expResult[i] = claim;
            addClaimToTestDatabase(claim);
        }
        
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim[] result = instance.getClaims(memId);
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of getClaims method, of class ClaimDaoImpl, when there are no claims for the member in the database.
     */
    @Test
    public void testGetClaims_NoClaims() {
        Claim[] expResult = new Claim[0];
        ClaimDaoImpl instance = new ClaimDaoImpl(connection);
        Claim[] result = instance.getClaims("memId");
        assertArrayEquals(expResult, result);
    }
    
    // </editor-fold>
    
    private Claim createClaim(int id, String memId, LocalDate date, String rationale, double amount) {
        Claim claim = new Claim();
        claim.setId(id);
        claim.setMemId(memId);
        claim.setDate(date);
        claim.setRationale(rationale);
        claim.setStatus(ClaimStatus.PENDING);
        claim.setAmount(amount);
        return claim;
    }
    
    private boolean isClaimPresentInTestDatabase(Claim claim) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM CLAIMS WHERE \"id\" = ?")) {
            prepStatement.setString(1, Integer.toString(claim.getId()));
            try (ResultSet queryResult = prepStatement.executeQuery()) {
                if (queryResult.next()) {
                    String memIdResult = queryResult.getString("mem_id");
                    LocalDate dateResult = queryResult.getDate("date").toLocalDate();
                    String rationaleResult = queryResult.getString("rationale");
                    String statusResultString = queryResult.getString("status").trim();
                    double amountResult = queryResult.getDouble("amount");
                    ClaimStatus statusResult = ClaimStatus.valueOf(statusResultString);
                    return claim.getMemId().equals(memIdResult) && claim.getDate().equals(dateResult) && claim.getRationale().equals(rationaleResult) && claim.getStatus().equals(statusResult) && claim.getAmount() == amountResult;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean addClaimToTestDatabase(Claim claim) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO CLAIMS VALUES (?, ?, ?, ?, ?, ?)")) {
            prepStatement.setInt(1, claim.getId());
            prepStatement.setString(2, claim.getMemId());
            prepStatement.setDate(3, Date.valueOf(claim.getDate()));
            prepStatement.setString(4, claim.getRationale());
            prepStatement.setString(5, claim.getStatus().toString().toUpperCase());
            prepStatement.setDouble(6, claim.getAmount());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // </editor-fold>
    
}
