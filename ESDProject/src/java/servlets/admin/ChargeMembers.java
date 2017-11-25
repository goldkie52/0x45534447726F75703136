package servlets.admin;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dao.PaymentDao;
import dao.PaymentDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import model.MemberStatus;
import model.Payment;

/**
 * Calculates the annual turnover report and forwards onto report-turnover.jsp.
 * @author Rachel Bailey 13006455
 * @author Matthew Carpenter 14012396
 */
public class ChargeMembers extends HttpServlet {

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

        String stringAmount = request.getParameter("memberAmount");
        if (stringAmount == null) {
            request.getRequestDispatcher("/admin/report-turnover.jsp?success=false").forward(request, response);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/admin/report-turnover.jsp?success=false").forward(request, response);
            return;
        }

        Connection connection = (Connection) request.getServletContext().getAttribute("databaseConnection");
        MemberDao memberDao = new MemberDaoImpl(connection);
        PaymentDao paymentDao = new PaymentDaoImpl(connection);

        // Get the next paymentId
        int paymentId = paymentDao.getNextId();
        
        // Find the next memberId
        Member[] allVerifiedMembers = memberDao.getAllVerifiedMembers(MemberStatus.APPROVED);
        for (Member member : allVerifiedMembers) {
            Payment payment = new Payment();
            payment.setId(paymentId);
            payment.setMemId(member.getId());
            payment.setTypeOfPayment("CHARGE");
            payment.setAmount(amount);
            LocalDateTime dateTime = LocalDateTime.now();
            payment.setDate(dateTime.toLocalDate());
            payment.setTime(dateTime.toLocalTime());
            if (!paymentDao.addPayment(payment)) {
                request.getRequestDispatcher("/admin/report-turnover.do?success=false").forward(request, response);
            }
            paymentId++;
        }
        request.getRequestDispatcher("/admin/report-turnover.do?success=true").forward(request, response);
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
