package controller.interfaces;

import java.util.Calendar;

import view.classes.ExhibitForm;
import controller.classes.ControllerExhibitForm;


/**
 * The interface for the {@link ControllerExhibitForm}.
 * @author Elisa Casadio
 *
 */

public interface IControllerExhibitForm {

	/**
	 * Checks the accuracy of the form fields and saves the informations about 
	 * the exhibit in the model and in the view. Show an error message if the 
	 * fields are incorrect.
	 * 
	 * @param code
	 * 			the exhibit's code.
	 * @param title
	 * 			the exhibit's title.
	 * @param curator
	 * 			the exhibit's curator.
	 * @param dateB
	 * 			the beggining date of the exhibit.
	 * @param dateE
	 * 			the end date of the exhibit.
	 * @param costEx
	 * 			the cost of the exhibit.
	 * @param costTicket
	 * 			the cost of the ticket of the exhibit.
	 * @param form
	 * 			the current active form.
	 */
	void commandConfirm(final Long code, final String title, 
			final String curator, final Calendar dateB, final Calendar dateE,
			final double costEx, final double costTicket, 
			final ExhibitForm form);
	
}
