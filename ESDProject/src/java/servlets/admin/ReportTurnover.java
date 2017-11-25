package servlets.admin;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.Member;
import model.TurnoverCalculator;

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
        TurnoverCalculator turnoverCalculator = new TurnoverCalculator(connection);
        
        // Get information from turnover class
        Claim[] claims = turnoverCalculator.getRelevantClaims();
        Member[] members = turnoverCalculator.getRelevantMembers();
        double totalClaimValue = turnoverCalculator.getTotalTurnover();
        
        //Set attributes
        request.setAttribute("turnoverClaims", claims);
        request.setAttribute("turnoverMembers", members);
        request.setAttribute("totalClaimValue", totalClaimValue);
        request.setAttribute("memberAmount", totalClaimValue / members.length);
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
