package servlets.member;

import dao.ClaimDao;
import dao.ClaimDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.ClaimStatus;
import model.User;

/**
 * Creates a new claim for the member from the request sent by make-claim.jsp.
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
        
        User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("/");
            return;
        }
        
        String stringAmount = request.getParameter("amount");
        if (stringAmount == null) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp").forward(request, response);
            return;
        }
        
        double amount;
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        if (amount == 0) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        String stringRationale = request.getParameter("rationale");
        if (stringRationale == null) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?rationale=invalid").forward(request, response);
            return;
        }
        
        // Get connection from Servlet Context
        Connection connection = (Connection)request.getServletContext().getAttribute("databaseConnection");
        ClaimDao claimDao = new ClaimDaoImpl(connection);
        
        // Get the next claimId
        int claimId = claimDao.getNextId();
        
        Claim claim = new Claim();
        claim.setId(claimId);
        claim.setMemId(loggedInUser.getId());
        LocalDateTime dateTime = LocalDateTime.now();
        claim.setDate(dateTime.toLocalDate());
        claim.setRationale(stringRationale);
        claim.setStatus(ClaimStatus.PENDING);
        claim.setAmount(amount);
        
        if (!claimDao.addClaim(claim)) {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?success=false").forward(request, response);
        } else {
            request.getRequestDispatcher("/member/claims/make-claim.jsp?success=true").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
