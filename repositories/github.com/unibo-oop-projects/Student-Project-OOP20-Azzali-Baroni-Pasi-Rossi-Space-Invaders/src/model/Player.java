package model;

/**
 * The Interface Player.
 */
public interface Player {
	
	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	int getHealth();
	
	/**
	 * Sets the health.
	 *
	 * @param lifePoints the new health
	 */
	void setHealth(int lifePoints);
	
	/**
	 * Gets the cool down.
	 *
	 * @return the cool down
	 */
	int getCoolDown();
	
	/**
	 * Sets the cool down.
	 *
	 * @param coolDown the new cool down
	 */
	void setCoolDown(int coolDown);
	
	/**
	 * Gets the shield.
	 *
	 * @return the shield
	 */
	int getShield();
	
	/**
	 * Sets the s hield.
	 *
	 * @param shieldLife the new s hield
	 */
	void setSHield(int shieldLife);
	
	/**
	 * Gets the shot ready.
	 *
	 * @return the shot ready
	 */
	int getShotReady();
	
	/**
	 * Sets the shot ready.
	 *
	 * @param time the new shot ready
	 */
	void setShotReady(int time);
	
	/**
	 * Reset position.
	 */
	void resetPosition();
	
}
