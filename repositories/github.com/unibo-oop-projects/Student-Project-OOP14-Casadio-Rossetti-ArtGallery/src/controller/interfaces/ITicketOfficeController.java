package controller.interfaces;

import javax.swing.table.DefaultTableModel;

import view.classes.TicketOfficeView;

/**
 * This is the interface for the TicketOfficeController.
 * @author Sofia Rosetti
 *
 */
public interface ITicketOfficeController {

	/**
	 * This method saves the model in the specified file.
	 */
	void save();
	
	/**
	 * This method adds a new TicketOfficeView to this controller.
	 * @param v
	 * 			the new view
	 */
	void addView(TicketOfficeView v);
	
	/**
	 * This method performs the action relative to the pressure of the home button.
	 */
	void commandClose();
	
	/**
	 * This method performs the action relative to the pressure of the confirm button.
	 * @param ex
	 * 			the selected exhibit
	 * @param percentage
	 * 			the selected discount percentage
	 * @param number
	 * 			the number of tickets of the purchase
	 * @param price
	 * 			the original price of the ticket 
	 */
	void commandConfirm(final String ex, final String percentage, 
			final String number, final double price);
	
	/**
	 * This method uploads from the ArtGallery all the open exhibits 
	 * and puts them into a DefaultTableModel.
	 * @return the DefaultTableModel containing the exhibits and the prices
	 */
	DefaultTableModel uploadExhibits();
	
}
