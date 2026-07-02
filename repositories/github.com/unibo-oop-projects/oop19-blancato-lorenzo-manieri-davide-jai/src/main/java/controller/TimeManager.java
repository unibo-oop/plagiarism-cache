package controller;


public interface TimeManager {
	
	
	/**
	 * Set player fire speed timer
	 * 
	 * @param period
	 * 		Period when player can fire
	 */
	public void setFireTimer(long period);
	
	/**
	 * Set enemy fire speed
	 * 
	 * @param period
	 * 		Period when enemy can fire
	 */
	public void setEnemyFireTimer(long period);
	
	/**
	 * Set player shield duration
	 */
	public void setShieldTimer();
	
	/**
	 * Set period of blinking
	 */
	public void setBlinkingTimer();
	
	/**
	 * Reset fire timer
	 */
	public void resetFireTimer() ;
	
	/**
	 * Reset enemy fire timer
	 */
	public void resetEnemyFireTimer();
	
	/**
	 * Reset shield timer 
	 */
	public void resetShield();
	
	/** 
	 * Reset blinking timer
	 */
	public void resetBlinking();
	
	/**
	 * Stop blinking timer and shield timer to avoid these timers continue when Game is in pause
	 */
	public void pause();
	
	/**
	 * Resume blinking and shield timers when the game resume
	 */
	public void resume();
	
	/**
	 * Call all reset method
	 */
	public void resetAllTimer();
}
