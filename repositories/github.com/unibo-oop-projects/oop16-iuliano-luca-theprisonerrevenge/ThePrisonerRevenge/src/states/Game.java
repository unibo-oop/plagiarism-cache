package states;

import levels.Level;

/**
 * This class represent the Game interface. It includes all the methods that a
 * Game must have to manage the elements present on level.
 * 
 * @author Luca
 *
 */
public interface Game extends State {
	/**
	 * Return the Level who contains all the elements in game.
	 * 
	 * @return a {@link levels.Level} the Level.
	 */
	Level getLevel();

	/**
	 * Call the method to reset the level.
	 */
	void resetGame();
}
