package view;

import controller.MainController;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface MainPanel {

	/**
	 * exit
	 */
	public void quitExit();

	/**
	 * this method use the pattern Observer
	 * 
	 * @param observerve
	 */
	public void addObserver(MainController observerve);
	
	
	/**
	 * this method set the mainPanel
	 * 
	 */
	public void setMainPanel();

}
