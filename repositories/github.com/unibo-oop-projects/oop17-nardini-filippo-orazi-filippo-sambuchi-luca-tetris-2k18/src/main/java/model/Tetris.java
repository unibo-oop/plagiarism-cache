package main.java.model;

import java.util.List;

/**
 * Interface of the Tetris game. It is the core of the model part of the
 * application. It gives the methods to query the state of a Tetris game.
 */
public interface Tetris {
	
	/**
	 * Getter for the current score.
	 */
	int getScore();
	
	/**
	 * Getter for the cleared lines.
	 */
	int getLines();
	
	/**
	 * Getter for the current level.
	 */
	int getCurrentLevel();
	
	/**
	 * Getter for the current tetromino.
	 */
	Tetromino getCurrentTetromino();
	
	/**
	 * Getter for the game board.
	 */
	List<Square> getBoard();
		
	/**
	 * Sends the input to the game.
	 */
	void sendInput(List<Input> inputs);
	
	/**
	 * Function that updates the state of the game.
	 * @param time
	 */
	void update(double time);
	
	/**
	 * Check if the player has lost
	 * 
	 */
	boolean isGameOver();
	
	/**
	 * reset the variable
	 */
	
	void reset();
	/**
	 * deleting the lines 
	 * @param number
	 */
	
	void lineElimination(List<Integer> list);
	
	/**
	 * puts the tetramino in a temporary support
	 * 
	 */
	
	void hold();
	
	
	
}
