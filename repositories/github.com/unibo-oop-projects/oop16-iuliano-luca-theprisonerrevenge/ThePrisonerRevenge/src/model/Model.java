package model;

import java.awt.Graphics2D;
import java.util.Map;
import states.State;
import states.StateEnum;

/**
 * This interface extend {@link KeyDetector}. This is the MODEL interface for
 * this application. The Model includes the functionality for updating
 * application status. Although it is independent of view, it exhibits the
 * methods required to notify the view of the various changes required by the
 * controller after user interaction.
 * 
 * @author Luca
 *
 */
public interface Model extends KeyDetector {
	/**
	 * Set the state of the application in the relative StateEnum.
	 * 
	 * @param state
	 *            a {@link StateEnum} to set.
	 */
	void setState(final StateEnum state);

	/**
	 * Get the current State of application.
	 * 
	 * @return {@link State}.
	 * @throws NullPointerException
	 *             if the current state is null.
	 */
	State getState() throws NullPointerException;

	/**
	 * Get the stateMap where with all the possible {@link StateEnum} that can
	 * be the application.
	 * 
	 * @return Map<{@link StateEnum},{@link State}>.
	 * @throws NullPointerException
	 *             if the list is null.
	 */
	Map<StateEnum, State> getStateMap() throws NullPointerException;

	/**
	 * Gets graphics elements present in the current {@link State} and put them
	 * on Graphics2D pass in argument.
	 * 
	 * @param graphics
	 *            the Graphics2D where put the elements.
	 */
	void getGraphics(final Graphics2D graphics);

	/**
	 * Call the update method of the current {@link State} to perform auto functions
	 * (like the animations) and update player movement as a result of user
	 * interaction.
	 */
	void updateModel();
	
	/**
	 * Reset the game state present on StateList to get start for a new game.
	 */
	void resetGame();
}
