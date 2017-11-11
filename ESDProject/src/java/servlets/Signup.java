package servlets;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import model.MemberStatus;
import model.Registrar;
import model.SignupResult;
import model.User;
import model.UserStatus;

/**
 * This servlet signs up new users.
 * @author James Broadberry 14007903
 * @author Matthew Carpenter 14012396
 */
public class Signup extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private static Connection connection;
    
    // </editor-fold>
    
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
        
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");
            Registrar registrar = new Registrar(connection);
            SignupResult result = registrar.register(firstName, lastName, dob, address);
            if (!result.isRequestValid()) {
                String redirectString = "/user/signup.jsp?";
                List<String> arguments = new ArrayList<String>();
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
                
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
