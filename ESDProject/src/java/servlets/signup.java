/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author james
 */
public class signup extends HttpServlet {

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
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String dob = request.getParameter("dob");
        String generatedPassword = "";
        String address = request.getParameter("address");   
        String registedDate = getCurrentDate();
        
        // Validate the date
        Date dobDate = validateDate(dob, "dd-MM-yyyy");
        
        if(dobDate == null){
            response.sendRedirect("/user/register.jsp?dob=invalid");
        }
        
        // Generate a password from the dob
        generatedPassword = generatePasswordFromDate(dobDate);

        
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(dobDate);
        System.out.println(address);
        System.out.println(generatedPassword);
        System.out.println(registedDate);
        try {
            // Create the connection to the DB
            // This connection could later be put into the site config
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc");

            

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    
    protected String generatePasswordFromDate(Date date){
        String passwordFormatString = "ddMMyy";
        SimpleDateFormat datePasswordFormatter = new SimpleDateFormat(passwordFormatString);
        return datePasswordFormatter.format(date);
    }
    
    protected Date validateDate(String date, String dateFormat){
        
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
