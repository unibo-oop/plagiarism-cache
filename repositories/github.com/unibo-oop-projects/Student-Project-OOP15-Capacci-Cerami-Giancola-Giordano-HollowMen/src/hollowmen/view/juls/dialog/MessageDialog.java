package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.BorderFactory;

import hollowmen.view.SingletonFrame;

/**
 * The {@code MessageDialog} abstract class represents a dialog showing
 * the user a message.
 * @author Juls
 *
 */
public abstract class MessageDialog extends CustomDialog {

	private static final long serialVersionUID = 4641997191314208379L;

	public MessageDialog(Frame frame) {
		super(frame);		
		this.setPreferences();	
	}
	
	protected void setPreferences() {
		this.setSize(500, 200);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(SingletonFrame.getInstance());
		this.setModal(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
	}
	
	/**
	 * The method sets the color of the foreground (= text color)
	 * @param color
	 */
	protected void setTextForeground(Color color) {
		message.setForeground(color);
	}
	
	/**
	 * The method adds a text
	 * @param text
	 */
	protected void addMessage(String text) {
		message.setText(text);
	}


}
