package view;

import java.awt.event.KeyListener;

/**
 * This is the VIEW interface for this application.
 * 
 * @author Luca
 *
 */
public interface View {
	/**
	 * Get the Graphics Elements from the {@link model.Model} and put them on
	 * graphics to be print.
	 */
	void updateView();

	/**
	 * Add a KeyListener passed in argument to this view.
	 * 
	 * @param listener
	 *            KeyListener to add.
	 */
	void addListener(final KeyListener listener);

	/**
	 * This method print a viewport build on panel who contains all graphics.
	 */
	void printView();
}
