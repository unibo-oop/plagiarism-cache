package observer;

import java.io.IOException;

import javax.swing.JTable;

import model.Player;
/**
 * class that defines the method to increase statistics and saving this statistic into a file 
 * @author francesco
 *
 */
public interface MatchViewObserver {

    /**
    * Method that increase the points scored by a player
    * @param p
    * @param value
    */
    public void increasePoints(Player p, int value);
    /**
     * Method that decrease points scored by a player
     * @param p
     * @param value
     */
    public void decreasePoints(Player p,int value);
    /**
     * Method that increase the offensive rebounds of the player
     * @param p
     */
    public void increaseOffRebounds(Player p);
    /**
     * Method that decrese the offensive rebounds of the player
     * @param p
     */
    public void decreaseOffRebounds(Player p);
    /**
     * Method that increase the defensive rebounds of the player
     * @param p
     */
    public void increaseDefRebounds(Player p);
    /**
     * Method that decrease the difensive rebounds of the player
     * @param p
     */
    public void decreaseDefRebounds(Player p);
    /**
     *Method that increase the assist of the player
     * @param p
     */
    public void increseAssists(Player p);
    /**
     * Method that decrease the assists of the player
     * @param p
     */
    public void decreaseAssists(Player p);
    /**
     * Method that increase blocks of the player
     * @param p
     */
    public void increaseBlocks(Player p);
    /**
     * Method that decrease blocks of the player
     * @param p
     */
    public void decreaseBlocks(Player p);
    /**
     * Method that increase personal fouls 
     * @param p
     */
    public void incresePersonalFouls(Player p);
    /**
     * Method that decrease personal fouls
     * @param p
     */
    public void decreasePeronsalFouls(Player p);
    /**
     * Method that increase turnovers of the player
     * @param p
     */
    public void increaseTurnovers(Player p);
    /**
     * Method that decrease turnovers of the player
     * @param p
     */
    public void decreaseTurnovers(Player p);
   /**
    * Method that increase the steal of the ball by a player
    * @param p
    */
    public void increaseSteals(Player p);
    /**
     * Method that decrease the steal of the ball by a player
     * @param p
     */
    public void decreaseSteals(Player p);
    /**
     * Method used to save the final result of a match into the model and into a excel file
     * @param homeTable
     * @param guestTable
     * @param homeName
     * @param guestName
     * @param path
     * @throws IOException
     */
	void saveMatch(JTable homeTable, JTable guestTable, String homeName, String guestName, String path) throws IOException;	
}
