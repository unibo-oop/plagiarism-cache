package model;

import java.io.Serializable;

/**
 * This class implements a Player
 *
 */
public class PlayerImpl implements Player, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;  				//it contains the name of the player.
	private int PlayerId; 				//it contains the identificator of the player.
	private int nTimesHasBeenEating;	//it is a counter that contains the number of times the player has been eaten.
	private int nTimesHasEated;			//it is a counter that contains the number of times the player has eaten.
	private Color color;				//it's the color that the player has into the gameboard.	
	private boolean isAThief; 			//this variable is true if the player is a thief.
	private boolean isHuman; 			//this variable is true if the player is an human and false if is a CPU player.
	private int nTokenAtHome;			//it is a counter that contains the number of token at home.
	private int nTokenArrived;			//it is a counter that contains the number of token arrived
	
	public PlayerImpl(String name, int playerId, int nTimesHasBeenEating, int nTimesHasEated, Color color, boolean isAThief, boolean isHuman, int nTokenAtHome, int nTokenArrived){
		this.name = name;
		this.PlayerId = playerId;
		this.nTimesHasBeenEating = nTimesHasBeenEating;
		this.nTimesHasEated = nTimesHasEated;
		this.color = color;
		this.isAThief = isAThief;
		this.isHuman = isHuman;
		this.setnTokenAtHome(nTokenAtHome);
		this.setnTokenArrived(nTokenArrived);
	}
	
	//Constructor for PlayerImplTest:
	public PlayerImpl(String name, int playerId,Color color){
		this(name, playerId, 0 , 0, color, false, true, 4, 0);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPlayerId() {
		return PlayerId;
	}
	
	public void setPlayerId(int playerId) {
		PlayerId = playerId;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isAThief() {
		return isAThief;
	}
	
	public void setAThief(boolean isAThief) {
		this.isAThief = isAThief;
	}

	public boolean getIsHuman() {
		return isHuman;
	}

	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}
	
	public int getNTimesHasBeenEating() {
		return nTimesHasBeenEating;
	}
	
	public void setNTimesHasBeenEating(int nTimesHasBeenEating) {
		this.nTimesHasBeenEating = nTimesHasBeenEating;
	}
	
	public void incrementNTimesHasBeenEating(){
		this.nTimesHasBeenEating++;
	}
	
	public int getNTimesHasEated() {
		return nTimesHasEated;
	}
	
	public void setNTimesHasEated(int nTimesHasEated) {
		this.nTimesHasEated = nTimesHasEated;
	}
	
	public void incrementNTimesHasEated(){
		this.nTimesHasEated++;
	}

	public int getnTokenAtHome() {
		return nTokenAtHome;
	}

	public void setnTokenAtHome(int nTokenAtHome) {
		this.nTokenAtHome = nTokenAtHome;
	}

	public int getnTokenArrived() {
		return nTokenArrived;
	}

	public void setnTokenArrived(int nTokenArrived) {
		this.nTokenArrived = nTokenArrived;
	}

	public String toString(){
		String s = "Player " + this.PlayerId + ", name: " + this.name + ", color: " + this.color + ", has been eating : " + this.nTimesHasBeenEating + 
				" times and has eated: " + this.nTimesHasEated + " times."; 
		return s;
	}
}
