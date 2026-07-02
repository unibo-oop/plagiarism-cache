package controller.interfaces;

import view.classes.ArtworkForm;
import controller.classes.ControllerArtworkForm;

/**
 * The interface for the {@link ControllerArtworkForm}.
 * @author Elisa Casadio
 *
 */

public interface IControllerArtworkForm {

	/**
	 * Checks the accuracy of the form fields and saves the information of the 
	 * artwork in the model and in the view. Show an error message if the 
	 * fields are incorrect.
	 * 
	 * @param code
	 * 			the code of the artwork.
	 * @param title
	 * 			the title of the artwork.
	 * @param author
	 * 			the author of the artwork.
	 * @param isAC
	 * 			true if was selected "Avanti Cristo" otherwise false.
	 * @param isDC
	 * 			true if was selected "Dopo Cristo" otherwiese false.
	 * @param year
	 * 			the year of the artwork.
	 * @param isPaint
	 * 			true if was selected "Pittura".
	 * @param artD
	 * 			the type of artistic discipline of the artwork.
	 * @param techn
	 * 			the painting technique or material of the artwork.
	 * @param height
	 * 			the height of artwork.
	 * @param width
	 * 			the width of artwork.
	 * @param depth
	 * 			the depth of artwork.
	 * @param descr
	 * 			the description of artwork.
	 * @param form
	 * 			the current active form.
	 */
	void commandConfirm(final Long code, final String title, final String author,
			final boolean isAC, final boolean isDC, final int year,
			final boolean isPaint, final String artD, final String techn,
			final double height, final double width, final double depth,
			final String descr, final ArtworkForm form);
	
}
