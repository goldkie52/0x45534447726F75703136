/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import dao.ClaimDao;
import dao.ClaimDaoImpl;
import dao.MemberDao;
import dao.MemberDaoImpl;
import dao.PaymentDao;
import dao.PaymentDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.Member;
import model.MemberStatus;
import model.Payment;
import model.User;

/**
 * Calculates the annual turnover report and forwards onto report-turnover.jsp.
 *
 * @author Rachel Bailey 13006455
 */
public class ChargeMembers extends HttpServlet {

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
        }

        float amount = 0;
        try {
            amount = Float.parseFloat(stringAmount);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/admin/report-turnover.jsp?success=false").forward(request, response);
        }

        Connection connection = (Connection) request.getServletContext().getAttribute("databaseConnection");
        MemberDao memberDao = new MemberDaoImpl(connection);
        PaymentDao paymentDao = new PaymentDaoImpl(connection);

        // Find the next paymentId
        Payment[] allPayment = paymentDao.getAllPayments();
        int paymentId = 0;
        for (Payment payment : allPayment) {
            if (payment.getId() >= paymentId) {
                paymentId = payment.getId() + 1;
            }
        }
        
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

}
