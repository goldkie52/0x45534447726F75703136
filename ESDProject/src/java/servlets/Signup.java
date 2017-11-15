package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Registrar;
import model.SignupResult;

/**
 * This servlet signs up new users.
 * @author James Broadberry 14007903
 * @author Matthew Carpenter 14012396
 */
public class Signup extends HttpServlet {
    
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
        String firstName = request.getParameter("firstname").trim();
        String lastName = request.getParameter("lastname").trim();
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        
        Connection connection = (Connection)request.getServletContext().getAttribute("databaseConnection");
        
        Registrar registrar = new Registrar(connection);
        SignupResult result = registrar.register(firstName, lastName, dob, address);
        if (!result.isRequestValid()) {
            String redirectString = "/user/signup.jsp?";
            List<String> arguments = new ArrayList<>();
            if (!result.isUserValid()) {
                arguments.add("user=invalid");
            }
            if (!result.isDobValid()) {
                arguments.add("dob=invalid");
            }
            for (int i = 0; i < arguments.size(); i++) {
                redirectString += arguments.get(i);
                if (i != arguments.size() - 1) {
                    redirectString += ",";
                }
            }
            response.sendRedirect(redirectString);
        } else if (result.isConnectionError()) {
            
        } else {
            request.getSession().setAttribute("loggedInUser", result.getNewUser());
            response.sendRedirect("index.jsp");
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
