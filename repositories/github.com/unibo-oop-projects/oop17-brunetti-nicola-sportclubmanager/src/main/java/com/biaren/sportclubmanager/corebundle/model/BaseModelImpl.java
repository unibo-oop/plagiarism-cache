package com.biaren.sportclubmanager.corebundle.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.biaren.sportclubmanager.adminbundle.model.UserImpl;
import com.biaren.sportclubmanager.adminbundle.model.interfaces.User;
import com.biaren.sportclubmanager.corebundle.model.interfaces.BaseModel;
import com.biaren.sportclubmanager.corebundle.model.interfaces.Facility;
import com.biaren.sportclubmanager.corebundle.model.interfaces.Sponsor;
import com.biaren.sportclubmanager.corebundle.model.interfaces.SportVenue;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.sportclubmanager.utility.enums.TextStrings;
import com.biaren.utility.BiarenPathHandler;
import com.biaren.utility.BiarenReadFile;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * Base model to holds all entities of the system.
 * Provides method to works on entities or get defensive copy
 * of them to do some works
 * @author nbrunetti
 *
 */
public class BaseModelImpl implements BaseModel {
    
    private static BaseModelImpl SINGLETON;
        
    private String teamName;
    private String businessName;
    
    private final ObservableSet<User> users;
    private final ObservableSet<SoccerPlayer> players;
    private final ObservableSet<StaffMember> staff;
    private final ObservableSet<AdministrationMember> administration;
    private final ObservableSet<EmployeeMember> employees;
    private final ObservableSet<FacilityImpl> facilities;
    private final ObservableSet<SponsorImpl> sponsor;
    private final ObservableSet<SoccerMatchData> matches;
        
    /**
     * Constructor.
     */
    @SuppressWarnings({"unchecked" })
    private BaseModelImpl() {
        this.users = FXCollections.observableSet((Set<User>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.USERS.getNameForPath(), 
                new TypeReference<Set<UserImpl>>() {}));
        this.players = FXCollections.observableSet((Set<SoccerPlayer>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.PLAYERS.getNameForPath(), 
                new TypeReference<Set<SoccerPlayer>>() {}));
        this.staff = FXCollections.observableSet((Set<StaffMember>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.STAFF.getNameForPath(), 
                new TypeReference<Set<StaffMember>>() {}));
        this.administration = FXCollections.observableSet((Set<AdministrationMember>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.ADMINISTRATION.getNameForPath(), 
                new TypeReference<Set<AdministrationMember>>() {}));
        this.employees = FXCollections.observableSet((Set<EmployeeMember>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.EMPLOYEES.getNameForPath(), 
                new TypeReference<Set<EmployeeMember>>() {}));
        this.facilities = FXCollections.observableSet((Set<FacilityImpl>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.FACILITIES.getNameForPath(), 
                new TypeReference<Set<FacilityImpl>>() {}));
        this.sponsor = FXCollections.observableSet((Set<SponsorImpl>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.SPONSOR.getNameForPath(), 
                new TypeReference<Set<SponsorImpl>>() {}));
        this.matches = FXCollections.observableSet((Set<SoccerMatchData>) DataPersistance.loadData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.MATCHES.getNameForPath(), 
                new TypeReference<Set<SoccerMatchData>>() {}));
    }
    
    /**
     * Gets instance of {@link BaseModelImpl}
     * @return {@link BaseModelImpl}
     */
    public static BaseModelImpl getInstance() {
        if(SINGLETON == null) {
            SINGLETON = new BaseModelImpl();
        }
        return SINGLETON;
    }
    
    @Override
    public void addNewUser(final User u) {
        this.users.add(u);
    }
    
    @Override
    public void addNewPlayer(final SoccerPlayer p) {
        this.players.add(p);
    }
    
    @Override
    public void addNewStaffMember(final StaffMember c) {
        this.staff.add(c);
    }
    
    @Override
    public void addNewAdministratioMember(final AdministrationMember c) {
        this.administration.add(c);
    }
    
    public void addNewEmployeeMember(final EmployeeMember c) {
        this.employees.add(c);
    }
    
    @Override
    public void addNewFacility(final FacilityImpl f) {
        this.facilities.add(f);
    }
    
    @Override
    public void addNewSponsor(final SponsorImpl s) {
        this.sponsor.add(s);
    }
    
    @Override
    public void addNewMatch(final SoccerMatchData s) {
        this.matches.add(s);
    }

    @Override
    public void removeUser(final User u) {
        this.users.remove(u);
    }

    @Override
    public void removePlayer(final SoccerPlayer p) {
        this.players.remove(p);
    }

    @Override
    public void removeStaffMember(final StaffMember c) {
        this.staff.remove(c);
    }

    @Override
    public void removeAdministratioMember(final AdministrationMember c) {
        this.administration.remove(c);
    }
    
    @Override
    public void removeEmployeeMember(final EmployeeMember c) {
        this.employees.remove(c);
    }
    
    @Override
    public void removeFacility(final FacilityImpl f) {
        this.facilities.remove(f);
    }
    
    @Override
    public void removeSponsor(final Sponsor s) {
        this.sponsor.remove(s);
    }
    
    @Override
    public void removeMatch(final SoccerMatchData s) {
        this.matches.remove(s);
    }
    
    @Override
    public void setTeamName(final String name) {
        this.teamName = name;
    }
    
    public void setBusinessName(final String name) {
        this.businessName = name;
    }
    
    @Override
    public String getTeamName() {
        return this.teamName;
    }
    
    @Override
    public String getBusinessName() {
        return this.businessName;
    }
    
    /**
     * Get players as {@link ObservableSet}
     * @return players
     */
    public ObservableSet<SoccerPlayer> getPlayers() {
        return this.players;
    }
    
    /**
     * Get users as {@link ObservableSet}
     * @return users
     */
    public ObservableSet<User> getUsers() {
        return this.users;
    }
    
    /**
     * Get staff as {@link ObservableSet}
     * @return staff
     */
    public ObservableSet<StaffMember> getStaff() {
        return this.staff;
    }

    /**
     * Get employees as {@link ObservableSet}
     * @return employees
     */
    public ObservableSet<EmployeeMember> getEmployees() {
        return this.employees;
    }
    
    /**
     * Get administration members as {@link ObservableSet}
     * @return administration members
     */
    public ObservableSet<AdministrationMember> getAdministration() {
        return this.administration;
    }
    
    /**
     * Get facilities as {@link ObservableSet}
     * @return facilities
     */
    public ObservableSet<FacilityImpl> getFacilities() {
        return this.facilities;
    }
    
    /**
     * Get sponsor as {@link ObservableSet}
     * @return sponsor
     */
    public ObservableSet<SponsorImpl> getSponsor() {
        return this.sponsor;
    }
    
    /**
     * Get soccer matches as {@link ObservableSet}
     * @return soccer matches
     */
    public ObservableSet<SoccerMatchData> getMatches() {
        return this.matches;
    }
    
    /**
     * Get users as {@link Set}
     * @return users
     */
    public Set<User> getUsersSet() {
        return new HashSet<>(this.users);
    }
    
    /**
     * Get players as {@link Set}
     * @return players
     */
    public Set<SoccerPlayer> getPlayersSet() {
        return new HashSet<>(this.players);
    }
    
    /**
     * Get staff as {@link Set}
     * @return staff
     */
    public Set<StaffMember> getStaffSet() {
        return new HashSet<>(this.staff);
    }
    
    /**
     * Get administration members as {@link Set}
     * @return administration members
     */
    public Set<AdministrationMember> getAdministrationSet() {
        return new HashSet<>(this.administration);
    }
    
    /**
     * Get facilities as {@link Set}
     * @return facilities
     */
    public Set<Facility> getFacilitiesSet() {
        return new HashSet<>(this.facilities);
    }
    
    /**
     * Get employees as {@link Set}
     * @return employees
     */
    public Set<EmployeeMember> getEmployeesSet() {
        return new HashSet<>(this.employees);
    }
    
    /**
     * Get sponsor as {@link Set}
     * @return sponsor
     */
    public Set<Sponsor> getSponsorSet() {
        return new HashSet<>(this.sponsor);
    }
    
    /**
     * Get matches as {@link Set}
     * @return matches
     */
    public Set<SoccerMatchData> getMatchesSet() {
        return new HashSet<>(this.matches);
    }
    
    /**
     * Get players as {@link List}
     * @return players
     */
    public List<SoccerPlayer> getPlayersList() {
        return new ArrayList<>(this.players);
    }
    
    /**
     * Get staff as {@link List}
     * @return staff
     */
    public List<StaffMember> getStaffMembersList() {
        return new ArrayList<>(this.staff);
    }
    
    /**
     * Get administration member as {@link List}
     * @return administration member
     */
    public List<AdministrationMember> getAdministrationMembersList() {
        return new ArrayList<>(this.administration);
    }
    
    /**
     * Get users as {@link List}
     * @return users
     */
    public List<User> getUsersList() {
        return new ArrayList<>(this.users);
    }
    
    /**
     * Get facilities as {@link List}
     * @return facilities
     */
    public List<FacilityImpl> getFacilitiesList() {
        return new ArrayList<>(this.facilities);
    }
    
    /**
     * Get employees as {@link List}
     * @return employees
     */
    public List<EmployeeMember> getEmployeesMembersList() {
        return new ArrayList<>(this.employees);
    }
    
    /**
     * Get sport venues as {@link List}
     * @return sport venues
     */
    public Set<Facility> getSportVenue() {
        return new HashSet<>(this.facilities.stream().filter(x -> x instanceof SportVenue).collect(Collectors.toSet()));
    }
    
    /**
     * Get sponsor as {@link List}
     * @return sponsor
     */
    public List<SponsorImpl> getSponsorList() {
        return new ArrayList<>(this.sponsor);
    }
    
    /**
     * Get matches as {@link List}
     * @return matches
     */
    public List<SoccerMatchData> getMatchesList() {
        return new ArrayList<>(this.matches);
    }
        
    @Override
    public Optional<User> getUserFromUsername(final String username) {
        return this.users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }
    
    /**
     * Check if is first system opening
     * @return true if is first opening, false otherwise
     */
    public static boolean isFirstOpening() {
        return ! BiarenReadFile.fileExists(new File(BiarenPathHandler.getRootDirPath() + File.separator + TextStrings.FIRST_OPENING_FILENAME.toString()));
    }
     
    /**
     * Check if system should do setup
     * @return true if system should do setup
     */
    public static boolean shouldDoSetup() {
        return ! BiarenReadFile.fileExists(new File(BiarenPathHandler.getRootDirPath() + File.separator + TextStrings.DO_SETUP_FILENAME.toString()));
    }
}
