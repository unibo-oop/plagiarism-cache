package controller.interfaces;

import javax.swing.JFrame;

import controller.classes.ControllerArtwork;

/**
 * Interface for {@link ControllerArtwork}.
 * @author Elisa Casadio
 *
 */
public interface IControllerArtwork {

	/**
	 * Show a new empty form to add a new artwork to the archive of the artwork
	 * of the art gallery. It also determines the code that must have the 
	 * artwork in the archive.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandNew(final JFrame frame);
	
	/**
	 * Show a form setted with the informations of the selected artwork in the 
	 * table to permit the modification of the informations.
	 * 
	 * @param index
	 * 			the position of the artwork in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandEdit(final int index, final JFrame frame);
	
	/**
	 * Show a form setted with the informations of the selected artwork in the 
	 * table to permit  the user to see the informations.
	 * 
	 * @param index
	 * 			the position of the artwork in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandShow(final int index, final JFrame frame);
	
	/**
	 * Deletes the selected artwork from the view and from the model by asking 
	 * the user for confirmation. Show an error message if the artwork is or 
	 * was exposed in an exhibit.
	 * 
	 * @param index
	 * 			the position of the artwork in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandDelete(final int index, final JFrame frame);
	
	/**
	 * Closes the frame and saves the model in to a file. Show an error message
	 * if the writing fails.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandClose(final JFrame frame);
	
}
