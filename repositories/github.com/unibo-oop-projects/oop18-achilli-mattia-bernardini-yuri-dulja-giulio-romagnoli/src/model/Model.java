package model;

/**
 * 
 * @author mattia
 *	Model of the application.
 */

public interface Model {
	
	/**
     * Initialize state of model to start the game.
     * 
     */
	public void initGame();
	
	/**
     * Update the state of the application.
     * 
     * @param timeElapsed
     *            time elapsed from last update
     */
    void update(int timeElapsed);

    /**
     * 
     * @return true if game is over
     */
    boolean checkGameOver();
}
