package com.biaren.sportclubmanager.corebundle.model.interfaces;

import java.util.Optional;

import com.biaren.sportclubmanager.adminbundle.model.interfaces.User;
import com.biaren.sportclubmanager.corebundle.model.AdministrationMember;
import com.biaren.sportclubmanager.corebundle.model.EmployeeMember;
import com.biaren.sportclubmanager.corebundle.model.FacilityImpl;
import com.biaren.sportclubmanager.corebundle.model.SponsorImpl;
import com.biaren.sportclubmanager.corebundle.model.StaffMember;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;

public interface BaseModel {
        
    /**
     * Add new user
     * @param u {@link User}
     */
    void addNewUser(User u);
    
    /**
     * Add new player
     * @param p {@link SoccerPlayer}
     */
    void addNewPlayer(SoccerPlayer p);
    
    /**
     * Add new staff member
     * @param s {@link StaffMember}
     */
    void addNewStaffMember(StaffMember s);
    
    
    /**
     * Add new administration member
     * @param a {@link AdministrationMember}
     */
    void addNewAdministratioMember(AdministrationMember a);
    
    /**
     * Add new employee member
     * @param e {@link EmployeeMember}
     */
    void addNewEmployeeMember(EmployeeMember e);
    
    /**
     * Add new facility
     * @param f {@link FacilityImpl}
     */
    void addNewFacility(FacilityImpl f);
    
    /**
     * Add new Sponsor
     * @param s {@link SponsorImpl}
     */
    void addNewSponsor(SponsorImpl s);
    
    /**
     * Add new Match
     * @param s {@link SoccerMatchData}
     */
    void addNewMatch(SoccerMatchData s);
    
    /**
     * Remove user
     * @param u {@link User}
     */
    void removeUser(User u);
    
    /**
     * Remove player
     * @param p {@link SoccerPlayer}
     */
    void removePlayer(SoccerPlayer p);
    
    /**
     * Remove staff member
     * @param c {@link StaffMember}
     */
    void removeStaffMember(StaffMember c);
    
    /**
     * Remove administration member
     * @param a {@link AdministrationMember}
     */
    void removeAdministratioMember(AdministrationMember a);
    
    /**
     * Remove employee member
     * @param e {@link EmployeeMember}
     */
    void removeEmployeeMember(EmployeeMember e);
    
    /**
     * Remove facility
     * @param f {@link FacilityImpl}
     */
    void removeFacility(FacilityImpl f);
    
    /**
     * Remove sponsor
     * @param s {@link SponsorImpl}
     */
    void removeSponsor(Sponsor s);
    
    /**
     * Remove match
     * @param s {@link SoccerMatchData}
     */
    void removeMatch(SoccerMatchData s);
    
    /**
     * Get team name
     * @return {@link String} with team name
     */
    String getTeamName();
    
    /**
     * String get business name
     * @return {@link String} with business team name
     */
    String getBusinessName();
    
    /**
     * Set team name
     * @param s {@link String} team name
     */
    void setTeamName(String s);
    
    /**
     * Set business team name
     * @param s {@link String} business team name
     */
    void setBusinessName(String s);
    
    /**
     * Get user from username
     * @param s {@link String} s username
     * @return {@link Optional} user if present
     */
    Optional<User> getUserFromUsername(String s);
}
