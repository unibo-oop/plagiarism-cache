package view.interfaces;

import controller.classes.TicketOfficeController;

/**
 * This is the interface for the TicketOfficeView.
 * @author Sofia Rosetti
 *
 */
public interface ITicketOfficeView {

	/**
	 * This method attaches the relative controller to the view.
	 * 
	 * @param controller
	 * 					the controller
	 */
	void attachController(TicketOfficeController controller);
	
	/**
	 * This method creates the JTable which will contain the exhibits 
	 * whose tickets are available to be bought.
	 */
	void createTab();
	
	/**
	 * This method updates the JLabel containing the total of the purchase.
	 * 
	 * @param newTotal
	 * 				the total import of the purchase
	 */
	void updateTotal(double newTotal);
}
