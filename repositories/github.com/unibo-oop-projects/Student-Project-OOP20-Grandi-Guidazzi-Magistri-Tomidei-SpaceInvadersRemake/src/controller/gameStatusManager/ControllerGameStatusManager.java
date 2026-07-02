package controller.gameStatusManager;

import controller.GameStatus;

/**
 * Interface to monitor the status of the {@link GameController}
 */
public interface ControllerGameStatusManager {

	/**
	 * Set the {@link GameStatus} equal to stopped
	 */
	public void setStop();

	/**
	 * Puts the thread that call this method to wait if the game status is equal to paused
	 */
	public void isGamePaused();

	/**
	 * Get the current {@link GameStatus}
	 * 
	 * @return the value which represent the current state of the game
	 */
	public GameStatus getGameStatus();

	/**
	 * Set the game status equal to running after a game resume
	 */
	public void setResume();

	/**
	 * Set the game status equal to running
	 */
	public void setStart();

}