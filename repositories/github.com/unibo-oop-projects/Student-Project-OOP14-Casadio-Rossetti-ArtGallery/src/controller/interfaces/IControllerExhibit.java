package controller.interfaces;

import javax.swing.JFrame;

import controller.classes.ControllerExhibit;

/**
 * The interface for the {@link ControllerExhibit}.
 * @author Elisa Casadio
 *
 */

public interface IControllerExhibit {

	/**
	 * Show a new empty form to add a new exhibit to the archive of the 
	 * exhibits of the art gallery.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandNew(final JFrame frame);
	
	/**
	 * Show a form setted with the informations of the selected exhibit in the 
	 * table to permit the modification of the informations. Show an error 
	 * message if the user wants to edit the exhibit's information when the 
	 * exhibit has already started.
	 * 
	 * @param index
	 * 			the position of the exhibit in the list.
	 * @param frame
	 * 			the frame of the view.
	 * 			
	 */
	void commandEdit(final int index, final JFrame frame);
	
	/**
	 * Deletes the selected exhibit from the view and from the model by asking 
	 * the user for confirmation. Show an error message if the user wants to 
	 * delete the exhibit when the exhibit has already started.
	 * 
	 * @param index
	 * 			the position of the exhibit in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandDelete(final int index, final JFrame frame);
	
	/**
	 * Open a new view where is shown the archive of the artworks and the 
	 * artworks exposed in the selected exhibit.
	 * 
	 * @param index
	 * 			the position of the exhibit in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandArtwork(final int index, final JFrame frame);
	
	/**
	 * Checks that no exhibit has artworks assigned, otherwise shows an error 
	 * message and obstructs the closing of the view. If there aren't exhibits 
	 * without artworks assigned, closes the frame and saves the model in to a 
	 * file. Show an error message if the writing fails.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandClose(final JFrame frame);
	
}
