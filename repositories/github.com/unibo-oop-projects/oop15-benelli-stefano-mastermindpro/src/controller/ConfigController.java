package controller;

import model.exceptions.InvalidConfigException;
import model.players.Decoder;

public interface ConfigController {
		
	/**
	 * adds a new decoder to list with default values
	 */
	public void addDecoder();

	/**
	 * removes an existing decoder from list
	 * @param decoder Decoder to remove
	 */
	public void removeDecoder(Decoder decoder);
	
	/**
	 * Save and Validate Game Configuration
	 * @throws InvalidConfigException thrown if Validation fails
	 */
	public void saveSettings() throws InvalidConfigException;
}
