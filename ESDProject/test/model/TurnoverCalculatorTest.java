package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import test.BaseDbTestClass;

/**
 * Tests the TurnoverCalculator class.
 * @author Matthew Carpenter 14012396
 */
public class TurnoverCalculatorTest extends BaseDbTestClass {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public TurnoverCalculatorTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of getRelevantClaims method, of class TurnoverCalculator.
     */
    @Test
    public void testGetRelevantClaims() {
        Claim[] expResult = addTestClaims();
        Claim oldClaim = createClaim(4, "id5", LocalDate.parse("2016-01-07"), "rationale5", 10);
        addClaimToTestDatabase(oldClaim);
        TurnoverCalculator instance = new TurnoverCalculator(connection);
        Claim[] result = instance.getRelevantClaims();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getRelevantMembers method, of class TurnoverCalculator.
     */
    @Test
    public void testGetRelevantMembers() {
        Member[] expResult = addTestMembers();
        Member member = createMember("test_member5", "name5", "address5", LocalDate.now(), LocalDate.now(), 10.05);
        member.setStatus(MemberStatus.APPLIED);
        TurnoverCalculator instance = new TurnoverCalculator(connection);
        Member[] result = instance.getRelevantMembers();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getTotalTurnover method, of class TurnoverCalculator.
     */
    @Test
    public void testGetTotalTurnover() {
        addTestClaims();
        TurnoverCalculator instance = new TurnoverCalculator(connection);
        double expResult = 40;
        double result = instance.getTotalTurnover();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of chargeMembers method, of class TurnoverCalculator.
     */
    @Test
    public void testChargeMembers() {
        addTestClaims();
        Member[] members = addTestMembers();
        TurnoverCalculator instance = new TurnoverCalculator(connection);
        boolean expResult = true;
        boolean result = instance.chargeMembers();
        assertEquals(expResult, result);
        for (Member member : members) {
            Payment payment = new Payment();
            payment.setMemId(member.getId());
            payment.setAmount(10);
            payment.setDate(LocalDate.now());
            payment.setTime(LocalTime.now());
            payment.setTypeOfPayment("CHARGE");
            assertTrue(isPaymentPresentInTestDatabase(payment));
        }
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
            Logger.getLogger(TurnoverCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private Member createMember(String id, String name, String address, LocalDate dob, LocalDate dor, Double balance) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setAddress(address);
        member.setDob(dob);
        member.setDor(dor);
        member.setStatus(MemberStatus.APPLIED);
        member.setBalance(balance);
        return member;
    }
    
    private boolean addMemberToTestDatabase(Member member) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO MEMBERS VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            prepStatement.setString(1, member.getId());
            prepStatement.setString(2, member.getName());
            prepStatement.setString(3, member.getAddress());
            prepStatement.setDate(4, Date.valueOf(member.getDob()));
            prepStatement.setDate(5, Date.valueOf(member.getDor()));
            prepStatement.setString(6, member.getStatus().toString().toUpperCase());
            prepStatement.setDouble(7, member.getBalance());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TurnoverCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private Claim[] addTestClaims() {
        int[] ids = new int[] { 0,1,2,3 };
        String[] memIds = new String[] { "id1", "id2", "id3", "id4" };
        LocalDate[] dates = new LocalDate[] {LocalDate.parse("2017-01-07"), LocalDate.parse("2017-01-07"), LocalDate.parse("2017-01-07"), LocalDate.parse("2017-01-07")};
        String[] rationales = new String[] { "rationale1", "rationale2", "rationale3", "rationale4" };
        double [] amount = new double [] {10, 10, 10, 10};
        Claim[] expResult = new Claim[ids.length];
        for (int i = 0; i < ids.length; i++) {
            Claim claim = createClaim(ids[i], memIds[i], dates[i], rationales[i], amount[i]);
            expResult[i] = claim;
            addClaimToTestDatabase(claim);
        }
        return expResult;
    }
    
    private Member[] addTestMembers() {
        String[] members = new String[] { "test_member1", "test_member2", "test_member3", "test_member4" };
        String[] names = new String[] { "name1", "name2", "name3", "name4" };
        String[] addresses = new String [] {"address1", "address2", "address3", "address4"};
        LocalDate[] dobs = new LocalDate [] {LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now()};
        LocalDate[] dors = new LocalDate [] {LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now()};
        double [] balances = new double [] {10.01, 10.02, 10.03, 10.04};
        Member[] expResult = new Member[members.length];
        for (int i = 0; i < members.length; i++) {
            Member member = createMember(members[i], names[i], addresses[i], dobs[i], dors[i], balances[i]);
            member.setStatus(MemberStatus.APPROVED);
            expResult[i] = member;
            addMemberToTestDatabase(member);
        }
        return expResult;
    }
    
    private boolean isPaymentPresentInTestDatabase(Payment payment) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE \"mem_id\" = ? AND \"type_of_payment\" = ? AND \"amount\" = ? AND \"date\" = ? AND \"time\" = ?")) {
            prepStatement.setString(1, payment.getMemId());
            prepStatement.setString(2, payment.getTypeOfPayment());
            prepStatement.setDouble(3, payment.getAmount());
            prepStatement.setDate(4, Date.valueOf(payment.getDate()));
            prepStatement.setTime(5, Time.valueOf(payment.getTime()));
            try (ResultSet queryResult = prepStatement.executeQuery()) {
                return queryResult.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurnoverCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // </editor-fold>
    
}
