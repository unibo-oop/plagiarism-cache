package view.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * 
 * @author Stefano Benelli
 * This is an utility class used to size and center a JFrame in the screen
 */
public class FrameHelper {
	
	/**
	 * Set window in center of screen with size set to half of screen size
	 * @param frame Frame to be displayed
	 */
	public static void setupWindow(Window frame) {
		setupWindow(frame, 2);
	}
	
	/**
	 * Set window in center of screen with size set to the desided portion of total screen size
	 * @param frame Frame to be displayed
	 * @param portionSize portion Size
	 */
	public static void setupWindow(Window frame, int portionSize) {
		
		setupWindow(frame, new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize().width / portionSize, Toolkit.getDefaultToolkit()
				.getScreenSize().height / portionSize));		
	}

	/**
	 * Set window in center of screen with size set to the desided Dimension
	 * @param frame Frame to be displayed
	 * @param customDim Custom Frame Dimension
	 */
	public static void setupWindow(Window frame, Dimension customDim) {
		
		//set window size proportionally to the screen size
		frame.setMinimumSize(customDim);

		//set window location to center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2 - frame.getSize().width/2, dim.height/2 - frame.getSize().height/2);
	}
}
