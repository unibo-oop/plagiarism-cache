package controller.view;

import view.GameP;
import view.StateV;

/**
 * An asynchronous update interface for receiving notifications
 * about View information as the View is constructed.
 */
public interface ViewObserver {
	
	/**
	 * This method is called when information about an View
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param panel the panel
	 * @param state the state
	 */
	void update(GameP panel,StateV state);

}
