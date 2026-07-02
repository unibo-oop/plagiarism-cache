package view.interfaces;

import java.util.Calendar;

import view.classes.ExhibitView;
import controller.interfaces.IControllerExhibit;

/**
 * Interface for the list of exhibit of the art gallery.
 * @author Elisa Casadio
 *
 */

public interface IExhibitView {
	
	/**
	 * Attaches the controller to the {@link ExhibitView}.
	 * 
	 * @param ctrl
	 * 			the controller.
	 */
	void attachController(final IControllerExhibit ctrl);
	
	/**
	 * Adds a new exhibit to be displayed.
	 * 
	 * @param code
	 * 			the exhibit's code.
	 * @param title
	 * 			the exhibit's title.
	 * @param curator
	 * 			the exhibit's curator.
	 * @param dateB
	 * 			the commencement date of the exhibit.
	 * @param dateE
	 * 			the end date of the exhibit.
	 * @param nPieces
	 * 			the number of pieces of the artworks.
	 * @param costEx
	 * 			the exhibit's cost.
	 * @param costTicket
	 * 			the ticket's cost of the exhibit.
	 */
	void addData(final Long code, final String title, final String curator, 
			final Calendar dateB, final Calendar dateE, final int nPieces, 
			final double costEx, final double costTicket);
	
	/**
	 * Changes the selected exhibit.
	 * 
	 * @param row
	 * 			the selected table's row.
	 * @param code
	 * 			the exhibit's code.
	 * @param title
	 * 			the exhibit's title.
	 * @param curator
	 * 			the exhibit's curator.
	 * @param dateB
	 * 			the commencement date of the exhibit.
	 * @param dateE
	 * 			the end date of the exhibit.
	 * @param nPieces
	 * 			the number of pieces of the artworks.
	 * @param costEx
	 * 			the exhibit's cost.
	 * @param costTicket
	 * 			the ticket's cost of the exhibit.
	 */
	void editData(final int row, final Long code, final String title, 
			final String curator, final Calendar dateB, final Calendar dateE, 
			final int nPieces, final double costEx, final double costTicket);
	
	/**
	 * Changes the cell "Num. pezzi" of the specific selected exhibit.
	 * 
	 * @param nPieces
	 * 			the new value of the number of pieces of the artworks.
	 * @param row
	 * 			the selected table's row.
	 */
	void editNPiecesCell(final int nPieces, final int row);
	
	/**
	 * Deletes the selected exhibit.
	 * 
	 * @param row
	 * 			the selected table's row.
	 */
	void clearData(final int row);

}
