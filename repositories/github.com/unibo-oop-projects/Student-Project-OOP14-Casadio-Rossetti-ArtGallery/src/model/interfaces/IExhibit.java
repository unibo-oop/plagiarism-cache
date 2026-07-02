package model.interfaces;

import java.util.Calendar;
import java.util.List;

/**
 * Interface for the exhibit of the art gallery.
 * @author Elisa Casadio
 *
 */

public interface IExhibit {
	
	/**
	 * Return the code of the exhibit.
	 * 
	 * @return the code of the exhibit.
	 */
	Long getCode();
	
	/**
	 * Return the title of the exhibit.
	 * 
	 * @return the title of the exhibit.
	 */
	String getTitleExhibit();
	
	/**
	 * Return the name of the curator of this exhibit.
	 * 
	 * @return the name of the curator.
	 */
	String getCurator();
	
	/**
	 * Return the commencement date of the exhibit.
	 * 
	 * @return the commencement date of the exhibit.
	 */
	Calendar getBeginning();
	
	/**
	 * Return the end date of the exhibit.
	 * 
	 * @return the end date of the exhibit.
	 */
	Calendar getEnd();
	
	/**
	 * Adds the code of the artwork to the list of the exhibit's artworks.
	 * 
	 * @param code
	 * 			the code of the artwork.
	 */
	void addArtwork(final Long code);
	
	/**
	 * Return the list of the codes of the artwork of this exhibit.
	 * 
	 * @return the list of the codes of the artwork.
	 */
	List<Long> getArtworks();
	
	/**
	 * Return the number of pieces of the artworks of this exhibit.
	 * 
	 * @return the number of pieces of the artworks.
	 */
	int getNumPieces();
	
	/**
	 * Return the cost of this exhibit in Euros.
	 * 
	 * @return the cost of this exhibit.
	 */
	double getCostExhibit();
	
	/**
	 * Return the cost of the ticket for this exhibit.
	 * 
	 * @return the cost of the ticket.
	 */
	double getCostTicket();

}
