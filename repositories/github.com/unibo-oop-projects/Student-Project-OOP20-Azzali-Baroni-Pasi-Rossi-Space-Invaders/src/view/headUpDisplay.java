package view;


import javax.swing.JPanel;

import model.PlayerImpl;

/**
 * The Class headUpDisplay.
 */
public abstract class headUpDisplay extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MAX_RGB. */
	public static final int MAX_RGB = 255;
	
	/** The Constant FONT_P. */
	public static final int FONT_P = 25;
	
	/**
	 * Render.
	 *
	 * @param player the player
	 * @param score the score
	 * @param level the level
	 */
	public abstract void render(PlayerImpl player, int score, int level);

}
