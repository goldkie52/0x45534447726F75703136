/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import model.MemberStatus;
import model.User;
import model.UserStatus;

/**
 *
 * @author james
 */
public class Signup extends HttpServlet {

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
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        String username = "";
        String fullname = firstname + " " + lastname;
        String dob = request.getParameter("dob");
        String generatedPassword = "";
        String address = request.getParameter("address");
        String registedDate = getCurrentDate();

        // Generate the username
        username = generateUsernameFromFirstAndLast(firstname, lastname);

        // Validate the date
        Date dobDate = validateDate(dob, "dd-MM-yyyy");

        if (dobDate == null) {
            response.sendRedirect("/user/signup.jsp?dob=invalid");
            return;
        }

        // Generate a password from the dob
        generatedPassword = generatePasswordFromDate(dobDate);

        System.out.println("First: " + firstname);
        System.out.println("Last: " + lastname);
        System.out.println("Username: " + username);
        System.out.println("Fullname: " + fullname);
        System.out.println("DOB: " + dob);
        System.out.println("Address: " + address);
        System.out.println("Password: " + generatedPassword);
        System.out.println("DOR: " + registedDate);

        try {
            // Create the connection to the DB
            // This connection could later be put into the site config
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");

            //Check if user exists and redirect if already exists
            UserDao userDao = new UserDaoImpl(connection);
            User user = userDao.getUser(username);

            if (user != null) {
                response.sendRedirect("/user/signup.jsp?user=invalid");
                return;
            }

            User userToAdd = new User();
            userToAdd.setId(username);
            userToAdd.setPassword(generatedPassword);
            userToAdd.setStatus(UserStatus.APPLIED);
            userDao.addUser(userToAdd);

            MemberDao memberDao = new MemberDaoImpl(connection);
            Member memberToAdd = new Member();
            memberToAdd.setId(username);
            memberToAdd.setAddress(address);
            memberToAdd.setBalance(0);
            memberToAdd.setName(fullname);
            memberToAdd.setStatus(MemberStatus.APPLIED);
            memberToAdd.setDob(parseLocalDate(dob, "dd-MM-yyyy"));
            memberToAdd.setDor(parseLocalDate(registedDate, "dd-MM-yyyy"));
            memberDao.addMember(memberToAdd);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected LocalDate parseLocalDate(String date, String dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(date, dateTimeFormatter);
    }

    protected String generateUsernameFromFirstAndLast(String first, String last) {
        return first.charAt(0) + "-" + last;
    }

    protected String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    protected String generatePasswordFromDate(Date date) {
        String passwordFormatString = "ddMMyy";
        SimpleDateFormat datePasswordFormatter = new SimpleDateFormat(passwordFormatString);
        return datePasswordFormatter.format(date);
    }

    protected Date validateDate(String date, String dateFormat) {

        SimpleDateFormat dateValidator = new SimpleDateFormat(dateFormat);
        dateValidator.setLenient(false);

        try {
            return dateValidator.parse(date);
        } catch (ParseException ex) {
            return null;
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
