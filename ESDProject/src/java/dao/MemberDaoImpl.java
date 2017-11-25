package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Member;
import model.MemberStatus;

/**
 * Provides the implementation for accessing member data from the database.
 *
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 * @see dao.MemberDao
 */
public class MemberDaoImpl implements MemberDao {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Connection connection;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Initializes a new instance of the MemberDaoImpl class.
     *
     * @param connection the connection to the database
     */
    public MemberDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public boolean addMember(Member member) {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO MEMBERS VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            prepStatement.setString(1, member.getId());
            prepStatement.setString(2, member.getName());
            prepStatement.setString(3, member.getAddress());
            prepStatement.setDate(4, Date.valueOf(member.getDob().toString()));
            prepStatement.setDate(5, Date.valueOf(member.getDor().toString()));
            prepStatement.setString(6, member.getStatus().toString());
            prepStatement.setDouble(7, member.getBalance());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Member getMember(String id) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS WHERE \"id\" = ?")) {
            prepStatement.setString(1, id);
            try (ResultSet result = prepStatement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString("name");
                    String address = result.getString("address");
                    LocalDate dob = result.getDate("dob").toLocalDate();
                    LocalDate dor = result.getDate("dor").toLocalDate();
                    String statusString = result.getString("status");
                    double balanceString = result.getDouble("balance");
                    return createMember(id, name, address, dob, dor, statusString, balanceString);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Member[] getAllMembers() {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS");
                ResultSet result = prepStatement.executeQuery()) {
            ArrayList<Member> members = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String address = result.getString("address");
                LocalDate dob = result.getDate("dob").toLocalDate();
                LocalDate dor = result.getDate("dor").toLocalDate();
                String statusString = result.getString("status");
                double balanceString = result.getDouble("balance");
                members.add(createMember(id, name, address, dob, dor, statusString, balanceString));
            }
            return members.toArray(new Member[0]);
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Member[] getMembers(MemberStatus status) {
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM MEMBERS WHERE \"status\" = ?")) {
            prepStatement.setString(1, status.toString());
            ArrayList<Member> members = new ArrayList<>();
            try (ResultSet result = prepStatement.executeQuery()) {
                while (result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    String address = result.getString("address");
                    LocalDate dob = result.getDate("dob").toLocalDate();
                    LocalDate dor = result.getDate("dor").toLocalDate();
                    double balanceString = result.getDouble("balance");
                    members.add(createMember(id, name, address, dob, dor, status.toString(), balanceString));
                }
            }
            return members.toArray(new Member[0]);
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateMember(Member member) {
        try (PreparedStatement prepStatement = connection.prepareStatement("UPDATE MEMBERS SET \"name\" = ?, \"address\" = ?, \"dob\" = ?, \"dor\" = ?, \"status\" = ?, \"balance\" = ? WHERE \"id\" = ?")) {
            prepStatement.setString(1, member.getName());
            prepStatement.setString(2, member.getAddress());
            prepStatement.setDate(3, Date.valueOf(member.getDob()));
            prepStatement.setDate(4, Date.valueOf(member.getDor()));
            prepStatement.setString(5, member.getStatus().toString());
            prepStatement.setDouble(6, member.getBalance());
            prepStatement.setString(7, member.getId());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MemberDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Member createMember(String id, String name, String address, LocalDate dob, LocalDate dor, String status, double balance) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setAddress(address);
        member.setDob(dob);
        member.setDor(dor);
        member.setStatus(MemberStatus.valueOf(status.toUpperCase().trim()));
        member.setBalance(balance);
        return member;
    }

    // </editor-fold>
    
}
