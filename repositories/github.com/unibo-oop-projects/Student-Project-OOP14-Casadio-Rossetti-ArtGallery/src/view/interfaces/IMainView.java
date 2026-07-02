package view.interfaces;

import controller.interfaces.IMainViewController;

/**
 * This is the interface for the main view of the program.
 * @author Elisa Casadio
 * @author Sofia Rosetti
 *
 */
public interface IMainView {
	
	/**
	 * This method attaches the relative controller to the view.
	 * 
	 * @param controller
	 * 					the controller
	 */
	void attachController(final IMainViewController controller);

}
