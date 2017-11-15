package servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Authenticator;
import model.LoginResult;

/**
 * This servlet handles validating the user and setting loggedInUser
 * session variable.
 * @author James Broadberry 14007903
 * @author Matthew Carpenter 14012396
 */
public class Login extends HttpServlet {
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Connection connection = (Connection)request.getServletContext().getAttribute("databaseConnection");
        
        Authenticator authenticator = new Authenticator(connection);
        LoginResult result = authenticator.authenticate(username, password);
            
        if (result.isValid()) {
            //Set the user variable in the session
            request.getSession().setAttribute("loggedInUser", result.getUser());

            //Return back to the home page
            response.sendRedirect("index.jsp");
        } else {
            //Invalidate their session
            request.getSession().invalidate();

            //Return back to the page with an error message
            response.sendRedirect("/user/login.jsp?invalid=true");
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
