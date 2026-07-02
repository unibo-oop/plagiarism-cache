package menuController;

import menu.State;

import util.AudioImpl;
/**
 * A controller that manages the menu 
 *
 */
public interface menuController {

	/** The method that change the State of the menu
	 * 
	 * @param state
	 */
	void changeState(State state);
	
	/** The method return the audio manager
	 * 
	 * @return the audio manager
	 */
	AudioImpl getAudio();
}
