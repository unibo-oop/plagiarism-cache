package model.interfaces;

/**
 * Interface for the artwork that is or has been in the art gallery.
 * @author Elisa Casadio
 *
 */

public interface IArtwork {
	
	/**
	 * Return the code of the artwork.
	 * 
	 * @return the code of the artwork.
	 */
	Long getCode();
	
	/**
	 * Return the title of the artwork.
	 * 
	 * @return the title of the artwork.
	 */
	String getTitle();
	
	/**
	 * Return the author of the artwork.
	 * 
	 * @return the author of the artwork.
	 */
	String getAuthor();
	
	/**
	 * Return the year in which the artwork was created.
	 * 
	 * @return the year of the artwork.
	 */
	int getYear();
	
	/**
	 * Return the type of artistic discipline of the artwork.
	 * It can be painting or sculpture.
	 * 
	 * @return the type of artistic discipline of the artwork.
	 */
	String getArtisticDiscipline();
	
	/**
	 * Return the painting technique or material used for the artwork.
	 * 
	 * @return the painting technique or material of the artwork.
	 */
	String getTechnique();
	
	/**
	 * Return the height of artwork.
	 * 
	 * @return the height of artwork.
	 */
	double getHeight();
	
	/**
	 * Return the width of artwork.
	 * 
	 * @return the width of artwork.
	 */
	double getWidth();
	
	/**
	 * Return the depth of artwork.
	 * 
	 * @return the depth of artwork.
	 */
	double getDepth();
	
	/**
	 * Return the description of artwork.
	 * 
	 * @return the description of artwork.
	 */
	String getDescription();

}
