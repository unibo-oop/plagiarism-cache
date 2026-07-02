package view.interfaces;

import java.util.Calendar;

import model.interfaces.IArtGallery;
import view.classes.ExhibitForm;
import controller.interfaces.IControllerExhibitForm;

/**
 * Interface for the exhibit form.
 * @author Elisa Casadio
 *
 */

public interface IExhibitForm {

	/**
	 * Attachs the controller to the {@link ExhibitForm}.
	 * 
	 * @param ctrl
	 * 			the controller.
	 */
	void attachController(final IControllerExhibitForm ctrl);
	
	/**
	 * Return the code of the exhibit.
	 * 
	 * @return the exhibit's code.
	 */
	Long getCode();
	
	/**
	 * Return the title of the exhibit typed.
	 * 
	 * @return the exhibit's title.
	 */
	String getTitleEx();
	
	/**
	 * Return the curator of the exhibit typed.
	 * 
	 * @return the exhibit's curator.
	 */
	String getCurator();
	
	/**
	 * Return the selected commencement date of the exhibit.
	 * 
	 * @return the exhibit's beginning date.
	 */
	Calendar getDateBeginning();
	
	/**
	 * Return the selected end date of the exhibit.
	 * 
	 * @return the exhibit's end date.
	 */
	Calendar getDateEnd();
	
	/**
	 * Return the selected cost of this exhibit in Euros.
	 * 
	 * @return the exhibit's cost.
	 */
	Double getCostEx();
	
	/**
	 * Return the selected cost of the ticket of this exhibit in Euros.
	 * 
	 * @return the ticket's cost.
	 */
	Double getCostTicket();
	
	/**
	 * Resets all fields.
	 */
	void reinit();
	
	/**
	 * Sets all fields of the exhibit chosen.
	 * 
	 * @param index
	 * 			the exhibit's position in the list.
	 * @param model
	 * 			the model of the art gallery.
	 */
	void setForm(final int index, final IArtGallery model);
	
}
