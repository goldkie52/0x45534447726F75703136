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
    
    /**
     * Initialises a new instance of the Registrar class.
     * @param connection the connection to the database
     */
    public Registrar(Connection connection) {
        this.connection = connection;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Registers a new user to the system with the provided details.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param dob the date of birth of the user
     * @param address the address of the user
     * @return a SignupResult holding the result of the registration
     */
    public SignupResult register(String firstName, String lastName, String dob, String address) {
        SignupResult result = new SignupResult();
        String fullname = firstName + " " + lastName;
        String username = generateUsernameFromFirstAndLast(firstName, lastName);
        String registedDate = getCurrentDate();
        
        Date dobDate = validateDate(dob, "dd-MM-yyyy");
        result.setDobValid(dobDate != null);
        
        UserDao userDao = new UserDaoImpl(this.connection);
        User existingUser = userDao.getUser(username);
        result.setUserValid(existingUser == null);
        if (!result.isRequestValid()) {
            return result;
        }
        
        String generatedPassword = generatePasswordFromDate(dobDate);
        
        User userToAdd = new User();
        userToAdd.setId(username);
        userToAdd.setPassword(generatedPassword);
        userToAdd.setStatus(UserStatus.APPLIED);
        boolean addedUser = userDao.addUser(userToAdd);

        if (addedUser) {
            result.setNewUser(userToAdd);
        }
        
        MemberDao memberDao = new MemberDaoImpl(this.connection);
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
    
    /**
     * Generates a username from a given full name
     * @param first the first name
     * @param last the last name
     * @return the generated username
     */
    private String generateUsernameFromFirstAndLast(String first, String last) {
        return first.toLowerCase().charAt(0) + "-" + last.toLowerCase();
    }
    
    /**
     * Returns the current date in the format dd-mm-yyyy
     * @return the current date in the format dd-mm-yyyy
     */
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    
    /**
     * Validates that the provided string is a date in the given format
     * @param date the String to parse the date from
     * @param dateFormat a String representing the expected format
     * @return a Date object with the value parsed from the string; or null if the value was not valid
     */
    private Date validateDate(String date, String dateFormat) {
        SimpleDateFormat dateValidator = new SimpleDateFormat(dateFormat);
        dateValidator.setLenient(false);
        try {
            return dateValidator.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    /**
     * Generates a password from the given date
     * @param date the Date object to generate the password from
     * @return the password that the date produces
     */
    private String generatePasswordFromDate(Date date) {
        String passwordFormatString = "ddMMyy";
        SimpleDateFormat datePasswordFormatter = new SimpleDateFormat(passwordFormatString);
        return datePasswordFormatter.format(date);
    }
    
    /**
     * Parses a LocalDate object from the provided string and date format
     * @param date the String object to parse the value from
     * @param dateFormat a String representing the expected format
     * @return a LocalDate object with the value parsed from the String
     */
    private LocalDate parseLocalDate(String date, String dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(date, dateTimeFormatter);
    }
    
    // </editor-fold>
    
}
