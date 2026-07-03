package pvz.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import pvz.model.entity.plant.PlantType;
import pvz.view.input.Input;
import pvz.controller.data.*;

public interface ControllerInterface {
	/**
	 * It quits the game.
	 */

	void quit();

	/**
	 * It ends the gameLoop .
	 * 
	 */

	void abortGameLoop();

	/**
	 * It resumes the gameLoop after a pause.
	 */

	void resumeGameLoop();

	/**
	 * It suspends the gameLoop.
	 */

	void pauseGameLoop();

	/**
	 * It returns true the gameLoop is running.
	 * 
	 * @return isRunning.
	 */

	boolean isRunning();

	/**
	 * It returns true if the gameLoop is in pause.
	 * 
	 * @return isInPause.
	 */

	boolean isInPause();

	/**
	 * It permits to register the player specified with playerName.
	 * 
	 * 
	 * @param playerName
	 * 
	 * @return returns false if playerName already exist
	 */

	boolean registerPlayer(final String playerName);

	/**
	 * It sets the current player.
	 * 
	 * @param playerName
	 */

	void loadPlayer(final String playerName);

	/**
	 * It saves player's info in a file.
	 * 
	 */
	void savePlayerInfo();

	/**
	 * It returns all the players registered in an external file.
	 * 
	 * @return all the players registered
	 */

	List<String> getRegisteredPlayers();

	/**
	 * It returns N best high scores of all players.
	 * 
	 * 
	 * @return map with all modes high scores
	 * 
	 */

	Map<Mode, List<Score>> getHighScores();

	/**
	 * It returns playerName's high scores for the mode selected
	 * 
	 * 
	 * @param playerName
	 * @param mode
	 * @return list of playerName's highs cores
	 */

	List<Score> getPlayerHighScores(String playerName, Mode mode);

	/**
	 * It returns the available energy.
	 * 
	 * @return energy available
	 */

	int getCurrentEnergy();

	/**
	 * If the player is set, it returns his plants unlocked.
	 * 
	 * @return plants unlocked
	 */

	List<PlantType> getPlantsUnlocked();

	/**
	 * It returns the number of levels unlocked
	 * 
	 * @return num levels unlocked
	 */

	int getLevelsUnlocked();

	/**
	 * It returns the total number of levels
	 * 
	 * @return number of levels
	 */

	int getTotLevels();

	/**
	 * It starts the gameLoop specifying the level and plants selected
	 * 
	 * @param level
	 * @param plants
	 */

	void startGameLoop(final Optional<Integer> level, Set<PlantType> plants);

	/**
	 * It returns credits of the game.
	 * 
	 * @return credits
	 */

	List<String> getCredits();

	/**
	 * It sets game's FPS(FRAME-PER-SECOND)
	 * 
	 * @param fps
	 */

	void setFPS(int fps);

	/**
	 * It sets the current mode
	 * 
	 * @param mode
	 */

	void setMode(Mode mode);

	/**
	 * It returns plant's info
	 * 
	 * @param plant
	 * @return plant's info
	 */

	List<String> getPlantInfo(PlantType plant);

	/**
	 * Returns available values for FPS.
	 * 
	 * @return FPS values
	 */
	
	List<Integer> availableFPS();
	
	/**
	 * It returns the current player's name .
	 * 
	 * @return current player name
	 */
	
	String currentPlayer();
	
	/**
	 * It returns the current FPS value.
	 * 
	 * @return fps
	 */
	
	int getFPS();

}
