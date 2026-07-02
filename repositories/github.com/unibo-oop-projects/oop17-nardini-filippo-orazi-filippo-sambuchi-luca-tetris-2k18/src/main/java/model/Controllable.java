package main.java.model;

import main.java.model.Tetromino.Direction;
import main.java.model.Tetromino.RotationSense;

/**
 * Interface for a controllable Tetris game. It allows to tell the model to
 * execute game actions.
 */
public interface Controllable {

	/**
	 * Rotates the current tetromino in the given rotation sense.
	 */
	void rotate(RotationSense r);
	
	/**
	 * Moves the current tetromino in the given direction.
	 */
	void move(Direction d);
	
	/**
	 * Setter for the soft-drop mode.
	 */
	void setSoftDrop(boolean activated);
}
