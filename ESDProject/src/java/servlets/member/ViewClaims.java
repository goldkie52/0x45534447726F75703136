package servlets.member;

import dao.ClaimDao;
import dao.ClaimDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.User;

/**
 * Retrieves all payments for the currently logged in member and forwards onto view-claims.jsp.
 * @author Matthew Carpenter 14012396
 * @author Charlotte Harris 14008503
 */
public class ViewClaims extends HttpServlet {

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
        // Get connection from Servlet Context
        Connection connection = (Connection)request.getServletContext().getAttribute("databaseConnection");
        // Create DAO and get all payments in system
        ClaimDao claimDao = new ClaimDaoImpl(connection);
        
        User loggedInUser = null;
        if (request.getSession().getAttribute("loggedInUser") != null) {
            loggedInUser = ((User) request.getSession().getAttribute("loggedInUser"));
        }
        
        //DOUBLE CHECK CALL IS CORRECT!
        Claim[] claims = claimDao.getClaims(loggedInUser.getId());
        
        // Set payments into attribute and forward onto view
        request.setAttribute("claims", claims);
        request.getRequestDispatcher("/member/claims/view-claims.jsp").forward(request, response);
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

}
