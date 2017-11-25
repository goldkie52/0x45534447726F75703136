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
        
        User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("/");
            return;
        }
        
        String stringAmount = request.getParameter("amount");
        if (stringAmount == null) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp").forward(request, response);
            return;
        }
        
        double amount;
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        if (amount == 0) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?amount=invalid").forward(request, response);
            return;
        }
        
        // Get connection from Servlet Context
        Connection connection = (Connection)request.getServletContext().getAttribute("databaseConnection");
        PaymentDao paymentDao = new PaymentDaoImpl(connection);
        
        // Find the next paymentId
        Payment[] allPayment = paymentDao.getAllPayments();
        int paymentId = 0;
        for (Payment payment : allPayment) {
            if (payment.getId() >= paymentId) {
                paymentId = payment.getId() + 1;
            }
        }
        
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setMemId(loggedInUser.getId());
        payment.setTypeOfPayment("FEE");
        payment.setAmount(amount);
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setDate(dateTime.toLocalDate());
        payment.setTime(dateTime.toLocalTime());
        
        if (!paymentDao.addPayment(payment)) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?success=false").forward(request, response);
        } else {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?success=true").forward(request, response);
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
