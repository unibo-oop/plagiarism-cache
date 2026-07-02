package model;


public interface Enemy extends LivingEntity{
	/**
	 * set enemy new position 
	 * @return return true if an enemy is inside the screen else return false
	 * 
	 */
	public boolean updateMovement();
	

	/**
	 * move enemy based on deltaX and deltaY
	 * @param deltaX amount used for move enemy coordinate x
	 * @param deltaY amount used for move enemy coordinate y
	 * 
	 */
	public void move(int deltaX, int deltaY);
	
	/**
	 * check if enemy is inside screen
	 * @return return true if enemy is inside screen else false
	 * 
	 */
	public boolean checkBottomBorder();
}
				




