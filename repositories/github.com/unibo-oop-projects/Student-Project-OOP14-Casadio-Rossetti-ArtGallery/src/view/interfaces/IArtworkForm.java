package view.interfaces;

import model.interfaces.IArtGallery;
import view.classes.ArtworkForm;
import controller.interfaces.IControllerArtworkForm;

/**
 * Interface for the artwork form.
 * @author Elisa Casadio
 *
 */
public interface IArtworkForm {
	
	/**
	 * Attachs the controller to the {@link ArtworkForm}.
	 * 
	 * @param ctrl
	 * 			the controller.
	 */
	void attachController(final IControllerArtworkForm ctrl);

	/**
	 * Return the code of the artwork.
	 * 
	 * @return the artwork's code.
	 */
	Long getCode();
	
	/**
	 * Return the title of the artwork typed.
	 * 
	 * @return the artwork's title.
	 */
	String getTitleArt();
	
	/**
	 * Return the author of the artwork typed.
	 * 
	 * @return the artwork's author.
	 */
	String getAuthor();
	
	/**
	 * Return true if the selected dating was "Avanti Cristo", otherwise false.
	 * 
	 * @return if was selected "Avanti Cristo".
	 */
	boolean isAC();
	
	/**
	 * Return true if the selected dating was "Dopo Cristo", otherwise false.
	 * 
	 * @return if was selected "Dopo Cristo".
	 */
	boolean isDC();
	
	/**
	 * Return the selected year in which the artwork was created.
	 * 
	 * @return the year of the artwork.
	 */
	int getYear();
	
	/**
	 * Return true if the selected technique was "Pittura", otherwise false.
	 * 
	 * @return if was selected "Pittura".
	 */
	boolean isPainting();
	
	/**
	 * Return the type of selected artistic discipline of the artwork,
	 * otherwise null if was not selected anything.
	 * 
	 * @return the type of artistic discipline or null.
	 */
	String getArtisticDisc();
	
	/**
	 * Return the selected painting technique or material used for the artwork.
	 * 
	 * @return the selected painting technique or material of the artwork.
	 */
	String getTechnique();
	
	/**
	 * Return the height of artwork typed.
	 * 
	 * @return the height of artwork.
	 */
	double getHeightArt();
	
	/**
	 *  Return the width of artwork typed.
	 * 
	 * @return the width of artwork.
	 */
	double getWidthArt();
	
	/**
	 * Return the depth of artwork typed.
	 * 
	 * @return the depth of artwork.
	 */
	double getDepthArt();
	
	/**
	 * Return the description of artwork typed.
	 * 
	 * @return the description of artwork.
	 */
	String getDescription();
	
	/**
	 * Resets all fields.
	 */
	void reinit();
	
	/**
	 * Sets all fields of the artwork chosen.
	 * 
	 * @param index
	 * 			the artwork's position in the list.
	 * @param model
	 * 			the model of the art gallery.
	 */
	void setForm(final int index, final IArtGallery model);

}
