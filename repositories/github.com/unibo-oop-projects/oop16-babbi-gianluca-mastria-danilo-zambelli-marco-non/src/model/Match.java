package model;

import java.util.List;
import java.util.Map;

/**
 * Match is the center interface 
 *
 */
public interface Match{

	/**
	 * @return the match's number of players.
	 */
	public int getNPlayers();
	
	/**
	   * This method set the match's number of players.
	   *
	   * @param nPlayers
	   *          number of players.
	   */
	public void setNPlayers(int nPlayers);
	
	/**
	 * 
	 * @return
	 */
	public Map<TokenImpl, CellImpl> getGameBoard();
	
	/**
	 * 
	 * @param gameBoard
	 */
	public void setGameBoard(Map<TokenImpl, CellImpl> gameBoard);
	
	/**
	 * 
	 * @return
	 */
	public List<PlayerImpl> getPlayers();
	
	/**
	 * 
	 * @param players
	 */
	public void setPlayers(List<PlayerImpl> players);
	
	
	/**
	 * This method returns the actual player from the list
	 * @param player
	 * 		is the turn present in the class application
	 * @return the actual player
	 */
	public PlayerImpl getIstance(int player);
	
	/**
	 * This method has to control if all the token of the actual player are out
	 * @param turns
	 * 	it corresponds to the actual player
	 * @return true if all the token of the actual player are out
	 */
	public boolean areAllOut(int turns);
	
	/**
	 * This method has to control if the starting position of the actual player is busy.
	 * @param turns
	 * 		it corresponds to the actual player
	 * @return true if the starting position of the actual player is busy.
	 */
	public boolean isStartOccupied(int turns);
	
	/**
	 * This method has to return the cells to light up
	 * @param jump
	 * 		is the value extract from the dice
	 * @param turns
	 * 		it corresponds to the actual player
	 * @param nPlayer
	 * 		it's the number of players who are playing.
	 * @return a list of the possible  destination cells 
	 */
	public List<CellImpl> getMoves(int jump, int turns, int nPlayer);
	
	/**
	 * This method initializes one token in the starting position. 
	 * @param turns
	 * 		it corresponds to the actual player
	 * @param nPlayer
	 * 		it's the number of players who are playing.
	 */
	public void startToken(int turns, int nPlayer);
	
	/**
	 * This method changes the values of the map and of the players.
	 * @param pos
	 * 		it's the actual position of the token
	 * @param turns
	 * 		it corresponds to the actual player
	 * @param dice
	 * 		
	 * @param nPlayers
	 * 		it's the number of players who are playing.
	 *  
	 */
	public void changeGameBoard(int pos, int turns, int dice, int nPlayers);
	
	/**
	 * This method check if the player has won
	 * @param turns
	 * 		it corresponds to the actual player	
	 * @return true if the player wins.
	 */
	public boolean playerWin(int turns);
	
	/**
	 * 
	 * @return
	 */
	public Map<TokenImpl, CellImpl> getOriginValues();
	
	/**
	 * This method returns the token position that is arriving. Then it will be removed from the view.
	 * @param turns
	 * 		it corresponds to the actual player
	 * @param dice
	 * 		is the value extract from the dice.
	 * @param nPlayers
	 * 		it's the number of players who are playing.
	 * @return the position of the token that is arreved.
	 */
	public int getPositionArrived(int turns, int dice, int nPlayers);
	
	/**
	 * This method returns the entering-token's steps
	 * @param turns
	 * 		it corresponds to the actual player
	 * @param dice
	 * 		is the value extract from the dice
	 * @param nPlayers
	 * 		it's the number of players who are playing.
	 * @return
	 */
	public int getNStepArrived(int turns, int dice, int nPlayers);
	
	/**
	 * This method changes the properties of the token that has arrived
	 * @param pos
	 * 		it's the actual position of the token
	 * @param nPlayers
	 * 		it's the number of players who are playing.
	 */
	public void changeInArrive(int pos, int nPlayers);
	
	/**
	 * This method has to return the cells where the CPU player could go
	 * @param jump
	 * 		it is the value extract from the dice.
	 * @param turns
	 * 		it corresponds to the actual player
	 * @param nPlayer
	 * 		it's the number of players who are playing.
	 * @return the list where the CPU player could go, but it needs to be converted
	 */
	public List<Integer> getMovesCPU(int jump, int turns, int nPlayer);
	
	/**
	 * This method has to convert the number of step in the position
	 * @param nStep
	 * 		it is the value for the token to convert in the value for the cell.
	 * @param turns
	 * 		it corresponds to the actual player.
	 * @param nPlayer
	 * 		it's the number of players who are playing.
	 * @return the position.
	 */
	public int getPositionFromNStep(int nStep, int turns, int nPlayer);
}
