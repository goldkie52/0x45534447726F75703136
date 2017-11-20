package servlets.member;

import dao.PaymentDao;
import dao.PaymentDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Payment;
import model.User;
import sun.util.calendar.CalendarUtils;

/**
 * Creates a new payment for the member from the request sent by make-payment.jsp.
 * @author Matthew Carpenter 14012396
 */
public class MakePayment extends HttpServlet {

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
        String stringAmount = request.getParameter("amount");
        if (stringAmount == null) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp").forward(request, response);
        }
        
        float amount = 0;
        try {
            amount = Float.parseFloat(stringAmount);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/member/payments/make-payment.jsp?userInput=invalid").forward(request, response);
        }
        
        User loggedInUser = null;
        if (request.getSession().getAttribute("loggedInUser") != null) {
            loggedInUser = ((User) request.getSession().getAttribute("loggedInUser"));
        }
        else{
            request.getRequestDispatcher("/member/payments/make-payment.jsp?userInput=invalid").forward(request, response);
        }
        
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
            request.getRequestDispatcher("/member/payments/make-payment.jsp?databaseReturn=invalid").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/member/payments/make-payment.jsp?databaseReturn=valid").forward(request, response);
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
