package controller;

import controller.data.PersistentException;
import view.images.ImageSetFactory;

public interface GameController {

	/**
	 * Toggles the Debug Mode for the current Game
	 */
	public void toggleDebugMode();

	/**
	 * Submits the round identified by DecoderId / RoundId
	 * @param decoderId Id of Decoder
	 * @param roundId Id of Round
	 */
	public void submitRound(int decoderId, int roundId);
	
	/**
	 * Increment the Choice value identified by DecoderId / RoundId / ChoiceId
	 * @param decoderId Id of Decoder
	 * @param roundId Id of Round
	 * @param choiceId Id of Choice
	 */
	public void nextChoice(int decoderId, int roundId, int choiceId);	
	
	/**
	 * Changes the Image Set used by the View
	 * @param setGroup Image Set Group
	 */
	public void changeImageSet(ImageSetFactory.SetGroup setGroup);
	
	/**
	 * Saves the current Game
	 * @throws PersistentException thrown if an error occurs during Save process
	 */
	public void saveGame() throws PersistentException;
}
