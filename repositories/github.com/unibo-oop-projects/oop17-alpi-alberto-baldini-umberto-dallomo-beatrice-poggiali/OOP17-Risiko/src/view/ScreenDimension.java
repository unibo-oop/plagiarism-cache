package view;

import java.awt.Toolkit;
/**
 * Support class to get the screen size based on the PC on which the game is played
 *
 */
public class ScreenDimension {
	private static final int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
    /**
     * 
     * @return The screen's width
     */
    public static int getWidth() {
		return width;
	}
	/**
	 * 
	 * @return The screen's height
	 */
	public static int getHeight() {
		return height;
	}

}
