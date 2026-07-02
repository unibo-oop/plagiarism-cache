package controller;

/**
 * Interface of the game controller
 */
public interface GameController {

	/**
	 * Return the state of the game loop.
	 * 
	 * @return true if the game loop is running, false otherwise
	 */
	public boolean isRunning();

	/**
	 * Stop the game loop and open the game over scene
	 */
	public void gameOver();

	/**
	 * Stop the game loop and open the victory scene
	 */
	public void victory();

	/**
	 * Return the width of the panel where the game is running
	 * 
	 * @return an integer which represent the width of the panel
	 */
	public int getWindowWidth();

	/**
	 * Return the height of the panel where the game is running
	 * 
	 * @return an integer which represent the height of the panel
	 */
	public int getWindowHeight();

}
