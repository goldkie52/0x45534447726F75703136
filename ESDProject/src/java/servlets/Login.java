package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 * This servlet handles validating the user and setting loggedInUser
 * session variable.
 * @author James Broadberry 14007903
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static Connection connection;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            // Create the connection to the DB
            // This connection could later be put into the site config
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");
            
            // Set up the User DAO
            UserDao userDao = new UserDaoImpl(connection);
            
            // Attempt to get a user with the inputted ID from the DAO
            User user = userDao.getUser(username);
            
            // Check if the user exists and whether the password was correct
            if (user != null && user.getPassword().equals(password)) {
                //The username and password was correct
                
                //Set the user variable in the session
                request.getSession().setAttribute("loggedInUser", user);

                //Return back to the home page
                response.sendRedirect("index.jsp");
            } else {
                //The username and/or password was incorrect
                
                //Invalidate their session
                request.getSession().invalidate();

                //Return back to the page with an error message
                response.sendRedirect("/user/login.jsp?invalid=true");
                
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

}
