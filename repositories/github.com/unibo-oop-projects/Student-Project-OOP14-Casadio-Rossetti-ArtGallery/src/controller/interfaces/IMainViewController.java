package controller.interfaces;

import view.interfaces.IMainView;
import controller.classes.MainViewController;

/**
 * The interface for the {@link MainViewController}.
 * @author Elisa Casadio
 * @author Sofia Rosetti
 *
 */

public interface IMainViewController {

	/**
	 * Adds a new main view to this controller.
	 * @param v
	 * 			the new view.
	 */
	void addView(final IMainView v);
	
	/**
	 * Creates a new model for the archive of the art gallery or loads it from 
	 * a file and shows the view of the artworks. Shows an error message if is 
	 * occurred a problem during the open of the file.
	 */
	void artworkCmd();
	
	/**
	 * Creates a new model for the archive of the art gallery or loads it from
	 * a file and shows the view of the exhibits. Shows an error message if is
	 * occurred a problem during the open of the file or if there aren't saved
	 * artworks in the archive.
	 */
	void exhibitCmd();
	
	/**
	 * Creates a new model for the ticket office or loads it from a file.
	 * It also creates or loads the art gallery model, in order to have
	 * a complete view of all the exhibits.
	 * Then the method creates the relative controller, checking if new
	 * exhibits have to be added and saved to the file, and adds the view.
	 */
	void ticketOfficeCmd();
	
	/**
	 * Creates a new model for the balance or loads it from a file.
	 * Then the method creates the relative controller and adds the view.
	 */
	void balanceCmd();
	
	/**
	 * Creates a new model for the classification, the relative controller
	 * and adds the view. 
	 */
	void classificationCmd();
	
	/**
	 * Closes the program.
	 */
	void commandQuit();
	
}
