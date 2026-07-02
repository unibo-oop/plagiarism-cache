package javarogue.ui.game;

import java.util.Optional;

import javarogue.level.Level;

/**
 * 
 * Controller of the Game Window, handles the player input.
 *
 */
public interface GameController {

	/**
	 * Links Model
	 * 
	 * @param model
	 */
	public void setModel(GameModel model);

	/**
	 * 
	 * Generates the Level data
	 * 
	 * @param level Depth of the level to generate.
	 */
	public void generateLevels();
	
	/**
	 * Changes the current active level.
	 * 
	 * @param level Destination level.
	 */
	public void changeLevel(int level);

	/**
	 * 
	 * @return Optional of the generated Level or Empty if none has been generated.
	 */
	public Optional<Level> getCurrentLevel();

}
