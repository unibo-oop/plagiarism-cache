package view.interfaces;

import view.classes.ArtworkView;
import controller.interfaces.IControllerArtwork;

/**
 * Interface for the list of artwork of that are or have been in the art 
 * gallery.
 * @author Elisa Casadio
 *
 */

public interface IArtworkView {
	
	/**
	 * Attachs the controller to the {@link ArtworkView}.
	 * 
	 * @param ctrl
	 * 			the controller.
	 */
	void attachController(final IControllerArtwork ctrl);
	
	/**
	 * Adds a new artwork to be displayed.
	 * 
	 * @param fields
	 * 			the fields to add to the table.
	 */
	void addData(final Object... fields);
	
	/**
	 * Changes the selected artwork.
	 * 
	 * @param row
	 * 			the selected table's row.
	 * @param fields
	 * 			the modified fields to add to the table.
	 * 			
	 */
	void editData(final int row, final Object... fields);
	
	/**
	 * Deletes the selected artwork.
	 * 
	 * @param row
	 * 			the selected table's row.
	 */
	void clearData(final int row);

}
