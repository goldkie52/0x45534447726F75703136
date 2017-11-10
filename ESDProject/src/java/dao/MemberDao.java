package dao;

import model.Member;

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
     * @return all members in the system if the operation was a success; otherwise null
     */
    Member[] getAllMembers();
    
    /**
     * Updates the specified member in the system.
     * @param member the member to update
     * @return true if the update was successful, otherwise false
     */
    boolean updateMember(Member member);
    
    // </editor-fold>
    
}