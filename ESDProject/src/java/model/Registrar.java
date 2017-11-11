package model;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Registers a new user to the system.
 * @author Matthew Carpenter 14012396
 * @author James Broadberry 14007903
 */
public class Registrar {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Registrar(Connection connection) {
        this.connection = connection;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    public SignupResult register(String firstName, String lastName, String dob, String address) {
        SignupResult result = new SignupResult();
        String fullname = firstName + " " + lastName;
        String username = generateUsernameFromFirstAndLast(firstName, lastName);
        String generatedPassword = "";
        String registedDate = getCurrentDate();
        
        Date dobDate = validateDate(dob, "dd-MM-yyyy");
        result.setDobValid(dobDate != null);
        
        UserDao userDao = new UserDaoImpl(connection);
        User existingUser = userDao.getUser(username);
        result.setUserValid(existingUser == null);
        if (!result.isRequestValid()) {
            return result;
        }
        
        User userToAdd = new User();
        userToAdd.setId(username);
        userToAdd.setPassword(generatedPassword);
        userToAdd.setStatus(UserStatus.APPLIED);
        boolean addedUser = userDao.addUser(userToAdd);

        MemberDao memberDao = new MemberDaoImpl(connection);
        Member memberToAdd = new Member();
        memberToAdd.setId(username);
        memberToAdd.setAddress(address);
        memberToAdd.setBalance(0);
        memberToAdd.setName(fullname);
        memberToAdd.setStatus(MemberStatus.APPLIED);
        memberToAdd.setDob(parseLocalDate(dob, "dd-MM-yyyy"));
        memberToAdd.setDor(parseLocalDate(registedDate, "dd-MM-yyyy"));
        boolean addedMember = memberDao.addMember(memberToAdd);
        
        result.setConnectionError(!addedUser || !addedMember);
        
        return result;
    }
    
    private String generateUsernameFromFirstAndLast(String first, String last) {
        return first.charAt(0) + "-" + last;
    }
    
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
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
    
    private String generatePasswordFromDate(Date date) {
        String passwordFormatString = "ddMMyy";
        SimpleDateFormat datePasswordFormatter = new SimpleDateFormat(passwordFormatString);
        return datePasswordFormatter.format(date);
    }
    
    private LocalDate parseLocalDate(String date, String dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(date, dateTimeFormatter);
    }
    
    // </editor-fold>
    
}
