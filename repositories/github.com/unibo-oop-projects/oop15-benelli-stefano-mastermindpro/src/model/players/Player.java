package model.players;

/**
 * 
 * @author Stefano Benelli
 * Basic representation of Player
 */
public interface Player {
	
	/**
	 * Set Player Username
	 * @param username Username
	 */
	void setUsername(String username);
	
	/**
	 * Get Player Username
	 * @return Username
	 */
	String getUsername();
	
	/**
	 * Get Player Type
	 * @return Player Type
	 */
	PlayerType getPlayerType();
	
	/**
	 * Get a description of the player
	 * @return Player description
	 */
	String getDisplayName();
}
