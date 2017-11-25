package servlets.admin;

import dao.ClaimDao;
import dao.ClaimDaoImpl;
import dao.MemberDao;
import dao.MemberDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.Member;
import model.MemberStatus;

/**
 * Calculates the annual turnover report and forwards onto report-turnover.jsp.
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 */
public class ReportTurnover extends HttpServlet {

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
        
        // Get connection
        Connection connection = (Connection) request.getServletContext().getAttribute("databaseConnection");
        
        //Creates Claim DAO and Member DAO
        ClaimDao claimDao = new ClaimDaoImpl(connection);
        MemberDao memberDao = new MemberDaoImpl(connection);
        
        //Retrieves all claims into system for the current year and all verified members
        Claim[] claims = claimDao.getClaimsFromDate(LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1));
        Member[] members = memberDao.getAllVerifiedMembers(MemberStatus.APPROVED);
        
        //Initialise total for claims in current year
        double totalClaimValue = 0;
        //Total the claim amounts
        for (Claim claim : claims) {
            totalClaimValue += claim.getAmount();
        }
        
        //Set attributes
        request.setAttribute("turnoverClaims", claims);
        request.setAttribute("turnoverMembers", members);
        request.setAttribute("totalClaimValue", totalClaimValue);
        request.setAttribute("memberAmount", totalClaimValue/members.length);
        request.getRequestDispatcher("/admin/report-turnover.jsp").forward(request, response);
        
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
