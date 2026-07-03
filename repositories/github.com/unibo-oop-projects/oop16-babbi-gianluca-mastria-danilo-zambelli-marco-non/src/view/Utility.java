/**
 * 
 */
package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public interface Utility {

	/**set the text of result
	 * @param s
	 */
	public void setTextResult(String s);
	
	/**set the dice enabled is bool is true, false otherwise
	 * @param bool
	 */
	public void setDiceEnabled(boolean bool);
	
	/**create the message dialog
	 * @param s
	 */
	public void showMessage(String s);
	
	/**set properties for button
	 * @param b
	 * @param arrive
	 */
	public void setButton(JButton b, ArrayList<Integer> arrive);
	
	/**set properties for label
	 * @param label
	 * @param player
	 */
	public void setLabel(JLabel label, String player);
	
	/**
	 * @return the frame
	 */
	public JFrame getFrame();
}
