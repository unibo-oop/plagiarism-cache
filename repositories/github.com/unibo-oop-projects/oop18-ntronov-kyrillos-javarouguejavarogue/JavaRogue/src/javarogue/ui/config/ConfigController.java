package javarogue.ui.config;

import java.util.List;

import javarogue.utility.Resolution;

/**
 * 
 * Controller of the Configuration Window, coordinates selection of game
 * options.
 *
 */
public interface ConfigController {

	/**
	 * Links Model.
	 */
	public void setModel(ConfigModel model);

	/**
	 * 
	 * @return Available resolutions.
	 */
	public List<Resolution> getResolutionList();

	/**
	 * 
	 * @param resolution
	 *            Resolution to be saved.
	 */
	public void saveResolution(Resolution resolution);
	
	public void saveFullscreen(boolean isFullscreen);
	
	/**
	 * 
	 * @return Available tilesets.
	 */
	public List<String> getTileSets();
	
	/**
	 * 
	 * @param tileset Tileset to be saved.
	 */
	public void saveTileSet(String tileset);

	/**
	 * Attempts to launch game window, if all the configuration settings are valid,
	 * returns true and builds the static Config class, otherwise does nothing and
	 * returns false.
	 * 
	 * @return True if successful, false otherwise.
	 */
	public boolean launchGame();

	/**
	 * Returns a random seed and saves it.
	 * 
	 * @return a random seed.
	 */
	public Long getRandomSeed();

	/**
	 * 
	 * @param seed Seed to be saved
	 */
	public void saveSeed(long seed);

}
