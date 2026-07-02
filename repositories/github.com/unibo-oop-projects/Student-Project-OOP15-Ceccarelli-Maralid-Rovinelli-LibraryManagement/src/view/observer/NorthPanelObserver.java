/**
 *@author Ceccarelli 
 */
package view.observer;

public interface NorthPanelObserver {
	/**
	 * go back to home when click the button "Home"
	 */
	void buttonHomeClicked();

	/**
	 * go to login view
	 */
	void buttonLoginClicked();

	/**
	 * go to login view if click on button "Logout"
	 */
	void buttonLogoutClicked();

}
