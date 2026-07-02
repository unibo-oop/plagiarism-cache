package javarogue.ui.game;

import java.util.Optional;

import javarogue.level.Level;

/**
 * 
 * Model of the Game Window, handles the level data.
 *
 */
public interface GameModel {

	/**
	 * 
	 * Changes the current active level.
	 * 
	 * @param level Depth of the level to generate.
	 */
	public void changeLevel(int level);
	
	/**
	 * Generates a sequence of levels for the game.
	 */
	public void generateLevels();

	/**
	 * 
	 * @return Optional of the generated Level or Empty if none has been generated.
	 */
	public Optional<Level> getLevel();

}
