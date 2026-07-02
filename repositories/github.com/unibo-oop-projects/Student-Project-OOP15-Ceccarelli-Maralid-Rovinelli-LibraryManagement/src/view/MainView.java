/**
 *@author Ceccarelli 
 */
package view;
import javax.swing.JPanel;

import view.observer.ViewObserver;


public interface MainView {
	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void showMessage(String message);
	
	/**
	 * It allows you to replace the main panel of the GUI with the one passed as input
	 * 
	 * @param panel
	 * */
	void replaceMainPanel (JPanel panel);
	/**
	 * set the main panel
	 * 
	 * @return 
	 */
	NorthPanel getNorthPanel();
	/**
	 * set the NorthPanelObserver in the view
	 * 
	 * @param observer
	 */
	void attachObserver(ViewObserver observer);
	/**
	 * using this method to change the menu
	 * 
	 * @param logged
	 */
	void changeLogStatus(Boolean logged); 
	
}
