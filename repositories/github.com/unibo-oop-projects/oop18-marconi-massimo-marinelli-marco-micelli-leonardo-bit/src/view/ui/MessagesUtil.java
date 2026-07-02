package view.ui;

import javax.swing.JOptionPane;

/**
 * This class handles end-of-game messages.
 */
public final class MessagesUtil {

        private MessagesUtil() { }

        /**
	 * This method creates a messagebox in case of win.
	 */
	public static void youWon() {
		JOptionPane.showMessageDialog(null, "You defeated the boss!");
		System.exit(0);
	}
	
	/**
	 * This method creates a messagebox in case of lose.
	 */
	public static void youLose() {
		JOptionPane.showMessageDialog(null, "You Died...");
		System.exit(0);
	}
}
