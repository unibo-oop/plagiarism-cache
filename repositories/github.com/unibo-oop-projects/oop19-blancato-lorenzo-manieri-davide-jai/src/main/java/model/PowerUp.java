package model;


public interface PowerUp extends Movable, GameObject{

	/**
	 * 	Update the PowerUp position
	 * @return
	 * 		return true if powerUp is within the window
	 * 		
	 */
	public boolean updateMovement();
	
	/**
	 * 	Method for upgrade player
	 */
	public abstract void upgradePlayer();
	
}
