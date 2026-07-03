package controller;

import java.util.List;

import javax.swing.JButton;

import model.CellImpl;
import model.PlayerImpl;

/**
 * Application is the center interface
 *
 */
public interface Application {
	
	/**
	 * This method returns a boolean value that tells if all the token are playing
	 * 
	 * @return true if all the token of a player is playing
	 */
	public boolean getPlaying();
	
	/**
	 * This method returns a boolean value that tells if a yellow token can arrive to the finish cells
	 * 
	 * @return true if a yellow token can arrive to the finish cells
	 */
	public boolean isArrivingYellow();
	
	/**
	 * This method change the boolean value that represents if the player can put a token on arriving cells 
	 * 
	 * @param arrivingYellow
	 * 		new boolean value
	 */
	public void setArrivingYellow(boolean arrivingYellow);

	/**
	 * This method returns a boolean value that tells if a green token can arrive to the finish cells
	 * 
	 * @return true if a green token can arrive to the finish cells
	 */
	public boolean isArrivingGreen();
	
	/**
	 * This method change the boolean value that represents if the player can put a token on arriving cells 
	 * 
	 * @param arrivingGreen
	 * 		new boolean value
	 */
	public void setArrivingGreen(boolean arrivingGreen);
	
	/**
	 * This method returns a boolean value that tells if a blue token can arrive to the finish cells
	 * 
	 * @return true if a blue token can arrive to the finish cells
	 */
	public boolean isArrivingBlue();
	
	/**
	 * This method change the boolean value that represents if the player can put a token on arriving cells 
	 * 
	 * @param arrivingBlue
	 * 		new boolean value
	 */
	public void setArrivingBlue(boolean arrivingBlue);

	/**
	 * This method returns a boolean value that tells if an orange token can arrive to the finish cells
	 * 
	 * @return true if an orange token can arrive to the finish cells
	 */
	public boolean isArrivingOrange();
	
	/**
	 * This method change the boolean value that represents if the player can put a token on arriving cells 
	 * 
	 * @param arrivingOrange
	 * 		new boolean value
	 */
	public void setArrivingOrange(boolean arrivingOrange);

	/**
	 * This method returns a boolean value that tells if a purple token can arrive to the finish cells
	 * 
	 * @return true if a purple token can arrive to the finish cells
	 */
	public boolean isArrivingPurple();
	
	/**
	 * This method change the boolean value that represents if the player can put a token on arriving cells 
	 * 
	 * @param arrivingPurple
	 * 		new boolean value
	 */
	public void setArrivingPurple(boolean arrivingPurple);

	/**
	 * This method returns a boolean value that tells if a red token can arrive to the finish cells
	 * 
	 * @return true if a red token can arrive to the finish cells
	 */
	public boolean isArrivingRed();
	
	/**
	 * This method change the boolean value that represents if the player can put a token on arriving cells 
	 * 
	 * @param arrivingRed
	 * 		new boolean value
	 */
	public void setArrivingRed(boolean arrivingRed);

	/**
	 * This method returns a boolean value that tells if a player could press a button of the game board
	 * 
	 * @return true the player could press a button of the game board
	 */
	public boolean isChangeTable();

	/**
	 * This method changes the value of a boolean variable if a player could press a button of the game board
	 * 
	 * @param changeTable
	 * 		boolean variable that tells if a player could press a button of the game board
	 */
	public void setChangeTable(boolean changeTable);

	/**
	 * This method returns the last value of the dice
	 * 
	 * @return the last value of the dice
	 */
	public int getlastDice();

	/**
	 * This method change the value of the last dice when a player generate a new random number
	 * 
	 * @param lastDice
	 * 		the last value of the dice
	 */
	public void setLastDice(int lastDice);

	/**
	 * This method returns the player's turn
	 * 
	 * @return the player's turn
	 */
	public int getTurns();

	/**
	 * This method changes the player's turn
	 * 
	 * @param turns
	 * 		the player's turn
	 */
	public void setTurns(int turns);

	/**
	 * This method returns the list of the players
	 * 
	 * @return the list of the players
	 */
	public List<PlayerImpl> getPlayers();
	
	/**
	 * This method changes the value of the boolean variable to the original state
	 * 
	 * @param nPlayer
	 * 		number of players
	 */
	public void initializeBooleanVariables(int nPlayer);

	/**
	 * This method returns the names that players have choose during the registration
	 * 
	 * @return names of players
	 */
	public String[] getNames();
	
	/**
	 * This method returns the boolean value of thieves that players have choose during the registration
	 * 
	 * @return thieves of the match
	 */
	public Boolean[] getThieves();

	/**
	 * This method returns the boolean value of CPU players that players have choose during the registration
	 * 
	 * @return CPU players of the match
	 */
	public Boolean[] getHumans();

	/**
	 * This method creates a new instance of the model before the start of the match
	 * 
	 * @param nPlayers
	 * 		number of players
	 * @param load
	 * 		true if the start match is load 
	 */
	public void startMatch(int nPlayers, boolean load);

	/**
	 * This method return the current player
	 * 
	 * @return the current player
	 */
	public PlayerImpl getPlayer();

	/**
	 * This method generates a new random value for the dice
	 * 
	 * @return the value of dice
	 */
	public int rollDice();

	/**
	 * This methods increases the value of turn
	 * 
	 * @param nPlayers
	 * 		number of players
	 */
	public void increaseTurns(int nPlayers);

	/**
	 * This method generates a list of possible moves of the current human player
	 * 
	 * @param nPlayers
	 * 		number of players
	 * @return the possible moves of current player
	 */
	public List<Integer> manageTurns(int nPlayers);

	/**
	 * This method generates a list of possible moves of the current CPU player
	 * 
	 * @param nPlayers
	 * 		number of players
	 * @return the possible moves of current player
	 */
	public List<Integer> manageTurnsCPU(int nPlayers);

	/**
	 * This method changes the values of the game board when a player moves a token
	 * 
	 * @param pos
	 * 		the new position of a token
	 * @param nPlayers
	 * 		number of players
	 */
	public void moveToken(int pos, int nPlayers);

	/**
	 * This method checks if the start position of a player is occupied
	 * 
	 * @return true if the start position of a player is occupied
	 */
	public boolean isStartOccupied();

	/**
	 * This method returns a list of the cells where there is a token
	 * 
	 * @return a list of the cells where there is a token
	 */
	public List<CellImpl> getOriginPosition();
	
	/**
	 * This method refresh the image of the base buttons of the game board
	 */
	public void refreshBaseButtons();

	/**
	 * This method refresh the image of the arrive buttons of the game board
	 */
	public void refreshArriveButtons();
	
	/**
	 * This method refresh the image of the game buttons of the game board when a match is load
	 */
	public void refreshGameboard();

	/**
	 * This method change the image of a button of the game board
	 * 
	 * @param player
	 * 		player that moves a token
	 * @param b
	 * 		button to change
	 */
	public void setColorToken(int player, JButton b);

	/**
	 * This method disable all the buttons
	 */
	public void setAllNotEnabled();

	/**
	 * This method enable all the buttons
	 */
	public void setAllEnabled();

	/**
	 * This method orders a list
	 * 
	 * @param list
	 * 		list to order
	 * @return an order list
	 */
	public List<Integer> bubbleSort(List<Integer> list);
}
