package model;

import dao.ClaimDao;
import dao.ClaimDaoImpl;
import dao.MemberDao;
import dao.MemberDaoImpl;
import dao.PaymentDao;
import dao.PaymentDaoImpl;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Calculates and processes the charges for the annual turnover.
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 */
public class TurnoverCalculator {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public TurnoverCalculator(Connection connection) {
        this.connection = connection;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Gets the relevant claims from January 1st of the current year.
     * @return the claims from current year.
     */
    public Claim[] getRelevantClaims() {
        ClaimDao claimDao = new ClaimDaoImpl(connection);
        Claim[] yearlyClaims = claimDao.getClaimsFromDate(LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1));
        ArrayList<Claim> approvedClaims = new ArrayList();
        for (Claim claim : yearlyClaims) {
            if (claim.getStatus() == ClaimStatus.APPROVED) {
                approvedClaims.add(claim);
            }
        }
        return approvedClaims.toArray(new Claim[0]);
    }
    
    /**
     * Gets the relevant approved members.
     * @return the approved members.
     */
    public Member[] getRelevantMembers() {
        MemberDao memberDao = new MemberDaoImpl(connection);
        return memberDao.getMembers(MemberStatus.APPROVED);
    }
     
    /**
     * Gets the total turnover of the claims.
     * @return the total turnover of all relevant claims.
     */
    public double getTotalTurnover() {
        Claim[] claims = getRelevantClaims();
        double total = 0;
        for (Claim claim : claims) {
            total += claim.getAmount();
        }
        return total;
    }
    
    /**
     * Charges members their segment of total turnover.
     * @return the member charge from all relevant claims.
     */
    public boolean chargeMembers() {
        // Get information
        double totalTurnover = getTotalTurnover();
        Member[] members = getRelevantMembers();
        double memberCharge = totalTurnover / members.length;
        
        // Creates data access object
        PaymentDao paymentDao = new PaymentDaoImpl(connection);
        
        // Get the next paymentId
        int paymentId = paymentDao.getNextId();
        
        // Add payment charge for each member
        for (Member member : members) {
            Payment payment = new Payment();
            payment.setId(paymentId);
            payment.setMemId(member.getId());
            payment.setTypeOfPayment("CHARGE");
            payment.setAmount(-memberCharge);
            LocalDateTime dateTime = LocalDateTime.now();
            payment.setDate(dateTime.toLocalDate());
            payment.setTime(dateTime.toLocalTime());
            if (!paymentDao.addPayment(payment)) {
                return false;
            }
            paymentId++;
        }
        return true;
    }
    
    // </editor-fold>
    
}
