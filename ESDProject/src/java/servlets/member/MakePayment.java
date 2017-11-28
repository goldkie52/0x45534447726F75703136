package servlets.member;

import dao.PaymentDao;
import dao.PaymentDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Payment;
import model.User;

/**
 * Creates a new payment for the member from the request sent by make-payment.jsp.
 * @author Matthew Carpenter 14012396
 * @author Kieran Harris 14010534
 */
public class MakePayment extends HttpServlet {

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
        User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");
        // If not set, redirect to home page
        if (loggedInUser == null) {
            response.sendRedirect("/");
            return;
        }
        
        // Retrieve payment amount value from request
        String stringAmount = request.getParameter("amount");
        // If not set, forward to make-payment page with amount parameter set to invalid
        if (stringAmount == null) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        // Parse double value from amount parameter
        double amount;
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (NumberFormatException e) {
            // If not a valid double, forward to make-payment page with amount parameter set to invalid
            request.getRequestDispatcher("/member/payments/make-payment.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        // If amount value is 0, forward to make-payment page with amount parameter set to invalid
        if (amount == 0) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        // Get connection from servlet context
        Connection connection = (Connection)request.getServletContext().getAttribute("databaseConnection");
        // Create Payment data access object
        PaymentDao paymentDao = new PaymentDaoImpl(connection);
        
        // Get the next valid id for a payment
        int paymentId = paymentDao.getNextId();
        
        // Create new Payment object
        Payment payment = new Payment();
        // Set values on object
        payment.setId(paymentId);
        payment.setMemId(loggedInUser.getId());
        payment.setTypeOfPayment("FEE");
        payment.setAmount(amount);
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setDate(dateTime.toLocalDate());
        payment.setTime(dateTime.toLocalTime());
        
        // Add Payment to system and forward to make-payment page with success parameter set accordingly
        boolean success = paymentDao.addPayment(payment);
        request.getRequestDispatcher("/member/payments/make-payment.jsp?success=" + success).forward(request, response);
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
