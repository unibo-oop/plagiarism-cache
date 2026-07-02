package model;

import java.io.Serializable;
import java.util.List;

import exceptions.PersonAlreadyAddedException;
/**
 * Define a team
 * @author lucadalseno
 *
 */
public interface Team extends Serializable{
    /**
     * Getter for team's name
     * @return the Team's name
     */
    public String getName();
    /**
     * Getter for trnasfer colour
     * @return the transfer jersey's colour
     */
    public String getTransferJerseyColour();
    /**
     * Getter for home colour
     * @return the home jersey's colour
     */
    public String getHomeJerseyColour();
    /**
     * Getter for company name
     * @return the company name
     */
    public String getCompany();
    /**
     * Getter for vat number
     * @return vat number of the compnay
     */
    public String getVatNumber();
    /**
     * Add a player to the team
     * @param p: the player to add
     * @throws PersonAlreadyAddedException if the person is already added to that team
     */
    public void addPlayer(Player p) throws PersonAlreadyAddedException;
    /**
     * Add a staff member to the team
     * @param s:the staff member to add
     * @throws PersonAlreadyAddedException if the person is already added to that team
     */
    public void addStaff(Staff s) throws PersonAlreadyAddedException;
    /**
     * Remove a player
     * @param p: the player to remove
     */
    public void removePlayer(Player p);
    /**
     * Remove a staff member
     * @param s: the staff member to remove
     */
    public void removeStaff(Staff s);
    /**
     * List all players in the team  
     * @return a List of all the player
     */
    List<Player> getPlayers();
    /**
     * List all the staff members in the team
     * @return a list of all the staff member 
     */
    List<Staff> getStaff();
}