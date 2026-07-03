package model;

/**
 * Player is the interface that corresponds to a gamer.
 *
 */

public interface Player {

	/**
	   *
	   * @return the player's name.
	   */
	String getName();
	
	/**
	   * This method set the player's name.
	   *
	   * @param name
	   *          player's name.
	   */
	void setName(String name);
	
	/**
	   *
	   * @return the player's Id.
	   */
	int getPlayerId();
	
	/**
	   * This method set the player's Id.
	   *
	   * @param playerId
	   *          player's Id.
	   */
	void setPlayerId(int playerId);
	
	/**
	 * 
	 * @return the player's color of the team
	 */
	Color getColor();
	
	/**
	 * This method set the player's color of the team
	 * @param color
	 * 		color of the team
	 */
	void setColor(Color color);
	
	/**
	 * 
	 * @return true if the player is a thief.
	 */
	boolean isAThief();
	
	/**
	 * This method set the property true if the player is an thief
	 * @param isAThief
	 * 		true if the player is a thief.
	 */
	void setAThief(boolean isAThief);

	/**
	 * 
	 * @return true if the player is an human.
	 */
	boolean getIsHuman();
	
	/**
	 * This method set the property true if the player is an human
	 * @param isHuman
	 * 		true if the player is an human.
	 */
	void setIsHuman(boolean isHuman);
	
	/**
	   *
	   * @return the counter of the times that the player has been eating.
	   */
	int getNTimesHasBeenEating();
	
	/**
	   * This method set the counter of the times that the player has been eating.
	   *
	   * @param nTimesHasBeenEating
	   *          times that the player has been eating.
	   */
	void setNTimesHasBeenEating(int nTimesHasBeenEating);
	
	/**
	 *  This method increase the counter of the times that the player has been eating.
	 */
	void incrementNTimesHasBeenEating();
	
	/**
	   *
	   * @return the counter of the times that the player has eated.
	   */
	int getNTimesHasEated();
	
	/**
	   * This method set the counter of the times that the player has eated.
	   *
	   * @param nTimesHasEated
	   *          times that the player has eated.
	   */
	void setNTimesHasEated(int nTimesHasEated);
	
	/**
	 * 
	 * @return the the number of token at home for the player
	 */
	public int getnTokenAtHome();

	/**
	 * This method set the counter of the token at home for the player
	 * @param nTokenAtHome
	 * 		number of token at home
	 */
	public void setnTokenAtHome(int nTokenAtHome);

	/**
	 * 
	 * @return the the number of token at home for the player
	 */
	public int getnTokenArrived();

	/**
	 * This method set the counter of the token arrived for the player
	 * @param nTokenAtHome
	 * 		number of token arrived
	 */
	public void setnTokenArrived(int nTokenArrived);

	
	String toString();
}
