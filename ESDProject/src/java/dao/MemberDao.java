package dao;

import model.Member;
import model.MemberStatus;

/**
 * Provides the interface for accessing member data.
 * @author Matthew Carpenter 14012396
 * @author Rachel Bailey 13006455
 */
public interface MemberDao {
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Adds a new member to the system.
     * @param member the member to add to the system
     * @return true if the member was added; otherwise false
     */
    boolean addMember(Member member);
    
    /**
     * Gets the member with the specified id.
     * @param id the id of the member to get
     * @return the member with the specified id if the operation was a success; otherwise null
     */
    Member getMember(String id);
    
    /**
     * Gets all the members in the system.
     * @return an array of all members in the system if successful; otherwise null
     */
    Member[] getAllMembers();
    
    /**
     * Gets all the verified members in the system.
     * @param status the status of the member
     * @return an array of the members in the system with the specified status if successful; otherwise null
     */
    Member[] getMembers(MemberStatus status);
    
    /**
     * Updates the specified member in the system.
     * @param member the member to update
     * @return true if the update was successful, otherwise false
     */
    boolean updateMember(Member member);
    
    // </editor-fold>
    
}