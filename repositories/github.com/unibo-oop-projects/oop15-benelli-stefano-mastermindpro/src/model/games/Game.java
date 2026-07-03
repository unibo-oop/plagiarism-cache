package model.games;

import java.util.List;

import model.exceptions.InvalidConfigException;
import model.observers.Observable;
import model.players.*;

/**
 * 
 * @author Stefano Benelli
 * Basic representation of Game
 */
public interface Game extends Observable<Game> {

	/**
	 * Returns the Game Configuration
	 * @return Game Configuration parameters
	 */
	GameConfig getGameConfig();

	/**
	 * Returns the Encoder object
	 * @return Encoder object
	 */
	Encoder getEncoder();
	
	/**
	 * Set the Encoder object
	 * @param encoder Encoder object
	 */
	void setEncoder(Encoder encoder);
	
	/**
	 * Returns the List of Decoders
	 * @return List of Decoders
	 */
	List<Decoder> getDecoders();

	/**
	 * Loads the default values for Game fields
	 */
	void loadDefaults();

	/**
	 * Validates the Game Config parameters
	 * @throws InvalidConfigException thrown if Validation fails
	 */
	void validateConfig() throws InvalidConfigException;
	
	/**
	 * Initialize game objects based on the Game Config parameters
	 */
	void initializeGame();

	/**
	 * Get the Game State
	 * @return true if Game is completed
	 */
	boolean getGameCompleted();
	
}
