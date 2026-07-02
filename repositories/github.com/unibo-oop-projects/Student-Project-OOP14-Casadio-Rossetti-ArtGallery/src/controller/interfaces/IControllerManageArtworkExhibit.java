package controller.interfaces;

import controller.classes.ControllerManageArtworkExhibit;

/**
 * The interface for the {@link ControllerManageArtworkExhibit}.
 * @author Elisa Casadio
 *
 */

public interface IControllerManageArtworkExhibit {

	/**
	 * Adds a new artwork to the exhibit by saving its code. Displays an error 
	 * message if the user wants change the list of the artworks when the 
	 * exhibit has already finished or when the artwork that the user wants to 
	 * add is already in the list.
	 * 
	 * @param index
	 * 			the index of the artwork in the archive of the artworks.
	 */
	void commandAdd(final int index);
	
	/**
	 * Deletes the selected artwork by the exhibit. Displays an error message 
	 * if the user wants to delete an artwork when the exhibit has already 
	 * finished.
	 * 
	 * @param index
	 * 			the index of the artwork in the exhibit.
	 */
	void commandDelete(final int index);
	
}
