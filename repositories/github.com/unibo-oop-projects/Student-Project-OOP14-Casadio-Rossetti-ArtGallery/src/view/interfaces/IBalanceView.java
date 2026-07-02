package view.interfaces;

import controller.classes.BalanceController;

/**
 * This is the interface for the BalanceView.
 * @author Sofia Rosetti
 *
 */
public interface IBalanceView {
	
	/**
	 * This method attaches the relative controller to the view.
	 * 
	 * @param controller
	 * 					the controller
	 */
	void attachController(BalanceController controller);

	/**
	 * This method creates the JTable which will contain the exhibits 
	 * whose tickets are available to be bought.
	 */
	void createTab();
	
	/**
	 * This method updates the relative JLabel with the given import.
	 *  
	 * @param newTotal
	 * 				the new import 
	 */
	void updateTotal(double newTotal);
}
