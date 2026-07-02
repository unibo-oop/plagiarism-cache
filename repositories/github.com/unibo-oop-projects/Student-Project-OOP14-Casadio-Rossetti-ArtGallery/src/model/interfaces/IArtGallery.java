package model.interfaces;

import java.util.List;

import model.classes.Artwork;
import model.classes.Exhibit;

/**
 * Interface for the archive of the art gallery.
 * @author Elisa Casadio
 *
 */

public interface IArtGallery {
	
	/**
	 * Adds the artwork to the list of artworks that have been exhibited in the
	 * art gallery.
	 * 
	 * @param artwork
	 * 			the artwork.
	 */
	void addArtwork(final Artwork artwork);
	
	/**
	 * Return the list of all artworks that have been exhibited in the art
	 * gallery.
	 * 
	 * @return the list of artworks.
	 */
	List<Artwork> getArtwork();

	/**
	 * Adds the exhibit to the list of exhibits of the art gallery.
	 * 
	 * @param exhibit
	 * 			the exhibit.
	 */
	void addExhibit(final Exhibit exhibit);
	
	/**
	 * Return the list of all exhibits of the art gallery.
	 * 
	 * @return the list of exhibits.
	 */
	List<Exhibit> getExhibit();
	
}
