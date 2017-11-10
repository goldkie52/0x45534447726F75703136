package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Member;
import model.MemberStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the MemberDaoImpl class.
 * @author Rachel Bailey 13006455
 */
public class MemberDaoImplTest {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private static Connection connection;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public MemberDaoImplTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Test Lifecycle">
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZ_Assoc_Test");
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
        try (PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM MEMBERS")) {
            prepStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tests">

    /**
     * Test of addMember method, of class MemberDaoImpl, when the member does not already exist in the database.
     */
    @Test
    public void testAddMember_MemberNotInDatabase() {
        Member member = createMember("test_member", "name", "address", LocalDate.now(), LocalDate.now(), 10.10);
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.addMember(member);
        assertEquals(expResult, result);
        assertEquals(true, isMemberPresentInTestDatabase(member));
    }
    
    /**
     * Test of addMember method, of class MemberDaoImpl, when the member already exists in the database.
     */
    @Test
    public void testAddMember_MemberInDatabase() {
        Member member = createMember("test_member", "name", "address", LocalDate.now(), LocalDate.now(), 10.10);
        assertTrue(addMemberToTestDatabase(member));
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.addMember(member);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMember method, of class MemberDaoImpl, when the member exists in the database.
     */
    @Test
    public void testGetMember_MemberExists() {
        String id  = "test_member";
        Member expResult = createMember(id, "name", "address", LocalDate.now(), LocalDate.now(), 10.10);
        assertTrue(addMemberToTestDatabase(expResult));
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        Member result = instance.getMember(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMember method, of class MemberDaoImpl, when the member does not exist in the database.
     */
    @Test
    public void testGetMember_MemberDoesNotExist() {
        String id  = "test_member";
        Member expResult = null;
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        Member result = instance.getMember(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllMembers method, of class MemberDaoImpl, when there are members in the database.
     */
    @Test
    public void testGetAllMembers_MembersPresent() {
        String[] members = new String[] { "test_member1", "test_member2", "test_member3", "test_member4" };
        String[] names = new String[] { "name1", "name2", "name3", "name4" };
        String[] addresses = new String [] {"address1", "address2", "address3", "address4"};
        LocalDate[] dobs = new LocalDate [] {LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now()};
        LocalDate[] dors = new LocalDate [] {LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now()};
        Double [] balances = new Double [] {10.01, 10.02, 10.03, 10.04};
        Member[] expResult = new Member[members.length];
        for (int i = 0; i < members.length; i++) {
            Member member = createMember(members[i], names[i], addresses[i], dobs[i], dors[i], balances[i]);
            expResult[i] = member;
            addMemberToTestDatabase(member);
        }
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        Member[] result = instance.getAllMembers();
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of getAllMembers method, of class MemberDaoImpl, when there are no members in the database.
     */
    @Test
    public void testGetAllMembers_NoMembers() {
        Member[] expResult = new Member[0];
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        Member[] result = instance.getAllMembers();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of updateMember method, of class MemberDaoImpl, when the member exists in the database.
     */
    @Test
    public void testUpdateMember_MemberExists() {
        String id = "test_member";
        Member member = createMember(id, "name", "address", LocalDate.now(), LocalDate.now(), 10.10);
        addMemberToTestDatabase(member);
        member.setStatus(MemberStatus.SUSPENDED);
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        boolean expResult = true;
        boolean result = instance.updateMember(member);
        assertEquals(expResult, result);
        isMemberPresentInTestDatabase(member);
    }
    
    /**
     * Test of updateMember method, of class MemberDaoImpl, when the member does not exist in the database.
     */
    @Test
    public void testUpdateMember_MemberDoesNotExist() {
        String id = "test_member";
        Member member = createMember(id, "name", "address", LocalDate.now(), LocalDate.now(), 10.10);
        member.setStatus(MemberStatus.SUSPENDED);
        MemberDaoImpl instance = new MemberDaoImpl(connection);
        boolean expResult = false;
        boolean result = instance.updateMember(member);
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    private Member createMember(String id, String name, String address, LocalDate dob, LocalDate dor, Double balance) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setAddress(address);
        member.setDob(dob);
        member.setDor(dor);
        member.setStatus(MemberStatus.APPROVED);
        member.setBalance(balance);
        return member;
    }
    
    private boolean isMemberPresentInTestDatabase(Member member) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS WHERE \"id\" = ?")) {
            prepStatement.setString(1, member.getId());
            try (ResultSet queryResult = prepStatement.executeQuery()) {
                if (queryResult.next()) {
                    String nameResult = queryResult.getString("name");
                    String addressResult = queryResult.getString("address");
                    LocalDate dobResult = queryResult.getDate("dob").toLocalDate();
                    LocalDate dorResult = queryResult.getDate("dor").toLocalDate();
                    String statusResultString = queryResult.getString("status").trim();
                    double balanceResult = queryResult.getDouble("balance");
                    MemberStatus statusResult = MemberStatus.valueOf(statusResultString);
                    return member.getName().equals(nameResult) && member.getAddress().equals(addressResult) &&
                            member.getDob().equals(dobResult) && member.getDor().equals(dorResult)
                            && member.getStatus().equals(statusResult) && member.getBalance() == balanceResult;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean addMemberToTestDatabase(Member member) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO MEMBERS VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            prepStatement.setString(1, member.getId());
            prepStatement.setString(2, member.getName());
            prepStatement.setString(3, member.getAddress());
            prepStatement.setDate(4, Date.valueOf(member.getDob()));
            prepStatement.setDate(5, Date.valueOf(member.getDor()));
            prepStatement.setString(6, member.getStatus().toString().toUpperCase());
            prepStatement.setDouble(7, member.getBalance());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // </editor-fold>
    
}
