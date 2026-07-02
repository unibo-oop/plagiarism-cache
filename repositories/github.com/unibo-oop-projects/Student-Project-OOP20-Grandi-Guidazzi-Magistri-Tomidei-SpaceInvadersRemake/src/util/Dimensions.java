package util;

import java.awt.Toolkit;

/**
 * A class to get all the infos about the dimension of the screen and the components used in the project. 
 */
public class Dimensions {
	/**
	 * A method to determine the prefer height.
	 * @return the prefer window height.
	 */
	public int getPreferScreenHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height * 7 / 10;
	}
	
	/**
	 * A method to determine the prefer width.
	 * @return the prefer window width.
	 */
	public int getPreferScreenWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width * 7 / 10;
	}
	
	/**
	 * A method to determine the prefer label height.
	 * @return the prefer label height.
	 */
	public int getMaxLabelHeight() {
		return this.getPreferScreenHeight()/10*2;
	}
	
	/**
	 * A method to determine the prefer label height.
	 * @return the prefer label width.
	 */
	public int getMaxLabelWidth() {
		return this.getPreferScreenWidth();
	}
	
	/**
	 * A method to determine the prefer button height.
	 * @return the prefer button height.
	 */
	public int getMaxButtonHeight() {
		return this.getPreferScreenHeight()/10;
	}
	
	/**
	 * A method to determine the prefer TextField height.
	 * @return the prefer TextField height.
	 */
	public int getMaxTextFieldHeight() {
		return this.getPreferScreenHeight()/10;
	}
	
	/**
	 * A method to determine the prefer TextField height.
	 * @return the prefer TextField width.
	 */
	public int getMaxTextFieldWidth() {
		return this.getPreferScreenWidth()/3;
	}

}
