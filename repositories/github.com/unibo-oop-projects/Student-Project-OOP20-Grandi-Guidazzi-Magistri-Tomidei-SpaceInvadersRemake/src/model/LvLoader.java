package model;

import java.util.Optional;

/**
 * interface for loading levels
 *
 */
public interface LvLoader {
	
	/**
	 * method that return the level
	 * @param levelNumber
	 * @return
	 */
    Optional<Level> loadLevel(int levelNumber);
}
