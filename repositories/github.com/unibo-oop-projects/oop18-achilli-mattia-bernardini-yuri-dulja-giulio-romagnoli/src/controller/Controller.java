package controller;

/**
 * 
 * @author mattia
 *	Controller of the application, interacts with model and view.
 */

public interface Controller {
	
	/**
	 * Initialize game model
	 */
	void initGame();
	
	/*
	 * Start game loop.
	 */
	void startGame();
	
	/**
	 * Stop the game.
	 */
	void stopGame();
}
