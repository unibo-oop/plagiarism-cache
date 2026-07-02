package menu;

import javax.swing.JPanel;

/**
 *	The basic interface that all state has to implements
 */
public interface State {
	
	/**
	 * This method is used by Board method to show all the object of the current state.
	 * @return the state main panel
	 */
	public JPanel getMainPanel();
	
}
