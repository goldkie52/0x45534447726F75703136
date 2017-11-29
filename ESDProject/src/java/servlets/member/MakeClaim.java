package servlets.member;

import dao.ClaimDao;
import dao.ClaimDaoImpl;
import dao.MemberDao;
import dao.MemberDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.ClaimStatus;
import model.Member;
import model.User;

/**
 * Creates a new claim for the member from the request sent by make-claim.jsp.
 *
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 */
public class MakeClaim extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve logged in user from session
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        // If not set, redirect to home page
        if (loggedInUser == null) {
            response.sendRedirect("/");
            return;
        }

        // Retrieve claim amount value from request
        String stringAmount = request.getParameter("amount");
        // If not set, forward to make-claim page
        if (stringAmount == null) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp").forward(request, response);
            return;
        }

        // Parse double value from amount parameter
        double amount;
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (NumberFormatException e) {
            // If not a valid double, forward to make-claim page with amount parameter set to invalid
            request.getRequestDispatcher("/member/claims/make-claim.jsp?amount=invalid").forward(request, response);
            return;
        }

        // If amount value is 0, forward to make-claim page with amount parameter set to invalid
        if (amount == 0) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?amount=invalid").forward(request, response);
            return;
        }

        // Retrieve claim rationale value from request
        String stringRationale = request.getParameter("rationale");
        // If not set, forward to make-claim page with rationale parameter set to invalid
        if (stringRationale == null) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?rationale=invalid").forward(request, response);
            return;
        }

        // Get connection from servlet context
        Connection connection = (Connection) request.getServletContext().getAttribute("databaseConnection");
        // Create Claim data access object
        ClaimDao claimDao = new ClaimDaoImpl(connection);

        // Create Member data access object
        MemberDao memberDao = new MemberDaoImpl(connection);

        // Get access for currrent logged in member
        Member member = memberDao.getMember(loggedInUser.getId());
        
        // Calculate how long member has been registered for
        Period difference = Period.between(member.getDor(), LocalDate.now());
        
        // If member has been registered for less than 6 months, reject claim
        if (difference.toTotalMonths() <= 6) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?registrationPeriod=invalid").forward(request, response);
            return;
        }
        
        // Get all claims for logged in user
        Claim[] userClaims = claimDao.getClaims(loggedInUser.getId());
        int totalYearlyClaims = 0;
        
        // Total the number of approved claims for the user this year
        for (Claim claim : userClaims) {
            if (claim.getStatus() == ClaimStatus.APPROVED && claim.getDate().getYear() == LocalDate.now().getYear()) {
                totalYearlyClaims++;
            }
        }
        
        // If member has made 2 or more claims that year
        if (totalYearlyClaims >= 2) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?yearlyClaimLimit=invalid").forward(request, response);
            return;
        }

        // Get the next valid id for a claim
        int claimId = claimDao.getNextId();

        // Create new Claim object
        Claim claim = new Claim();
        // Set values on object
        claim.setId(claimId);
        claim.setMemId(loggedInUser.getId());
        claim.setDate(LocalDate.now());
        claim.setRationale(stringRationale);
        claim.setStatus(ClaimStatus.PENDING);
        claim.setAmount(amount);

        // Add Claim to system and forward to make-claim page with success parameter set accordingly
        boolean success = claimDao.addClaim(claim);
        request.getRequestDispatcher("/member/claims/make-claim.jsp?success=" + success).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet Methods">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // </editor-fold>
    
}
