package view.interfaces;

import controller.interfaces.IControllerManageArtworkExhibit;
import view.classes.ManageArtworkExhibit;

/**
 * Interface for the {@link ManageArtworkExhibit}.
 * @author Elisa Casadio
 *
 */

public interface IManageArtworkExhibit {

	/**
	 * Attaches the controller to the {@link ManageArtworkExhibit}.
	 * 
	 * @param ctrl
	 * 			the controller.
	 */
	void attachController(final IControllerManageArtworkExhibit ctrl);
	
	/**
	 * Adds a new artwork to be displayed. Depending on the boolean value, the 
	 * data is added to the right table or to the left table.
	 * 
	 * @param isDX
	 * 			true if the row is to be added to the right table otherwise false.
	 * @param fields
	 * 			the fields to add to the left or right table.
	 */
	void addData(final boolean isDX, final Object... fields);
	
	/**
	 * Deletes the selected artwork to the left table.
	 * 
	 * @param row
	 * 			the selected table's row.
	 */
	void clearData(final int row);
	
}
