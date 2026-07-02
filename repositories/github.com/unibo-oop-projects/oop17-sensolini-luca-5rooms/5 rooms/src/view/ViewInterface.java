package view;

import java.awt.Canvas;
/**
 * @author RealTutsGML (youtube)
 * This class draws and manages all graphics.
 */
public interface ViewInterface {
    /**
     * Draws all graphics at every loop.
	 */
	public void render();
    /**
     * Hides the in game graphics (currently NOT working).
	 */
	public void hide();
    /**
     * Shows the in game graphics (currently NOT working).
	 */
	public void show();
    /**
     * Gets the main canvas.
     * @return canvas
	 */
	public Canvas getMainCanvas();
    /**
     * Hides the main menu.
	 */
	public void menuHide();
    /**
     * Shows the main menu.
	 */
	public void menuShow();
    /**
     * Shows the victory screen.
	 */
	public void WinScreen();

}
