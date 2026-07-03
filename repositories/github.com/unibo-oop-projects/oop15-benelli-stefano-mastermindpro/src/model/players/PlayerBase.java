package model.players;

import java.io.Serializable;

/**
 * 
 * @author Stefano Benelli
 * Basic implementation of Player
 */
public abstract class PlayerBase implements Player, Serializable {

	private static final long serialVersionUID = 5697050148209067970L;

	private String username;
	private final PlayerType playerType;

	protected PlayerBase(final String username, final PlayerType playerType){
		this.username = username;
		this.playerType = playerType;
	}
	
	/**
	 * Set Player Username
	 * @param username Username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get Player Username
	 * @return Username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get Player Type
	 * @return Player Type
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * Get a description of the player
	 * @return Player description in format "Username - PlayerType"
	 */
	public String getDisplayName() {
		return this.username + " - " + this.playerType.toString();
	}
}