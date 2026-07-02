package model;

import java.util.NoSuchElementException;

import model.WaveInfo.Difficulty;

/**
 * This class is used to deliver new instances of WaveInfo
 * and generic level-related information whenever required.
 * It can also return the LevelDataImpl in order to resume the 
 * game-play later.
 */
public interface LevelParametizer {

	/**
	 * Calls a new WaveInfo from the generator, if available;
	 * otherwise throws an Exception.
	 * 
	 * @return a new WaveInfo with LastWaveNumber
	 * @throws NoSuchElementException
	 */
	WaveInfo newWave() throws NoSuchElementException;

	/**
	 * Checks if a new Wave can be launched.
	 * 
	 * @return TRUE if Total Waves >= Last Wave + 1,
	 *          TRUE if is an Endless Level,
	 *          FALSE otherwise
	 */
	boolean hasNext();

	/**
	 * When the game is to be closed, use this method to recover
	 * LevelDataImpl and save for later resuming.
	 * 
	 * @return LevelData relative to ongoing game
	 */
	LevelData closeLevel();

	/**
	 * Returns the Difficulty used to initialize Level.
	 * 
	 * @return Level Difficulty
	 */
	Difficulty getLevelDifficulty();

}