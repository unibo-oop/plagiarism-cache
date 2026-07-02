package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.BorderFactory;
import hollowmen.view.SingletonFrame;

/**
 * The {@code OptionDialog} abstract class allows to create and show a
 * dialog containing choice buttons or game's information.
 * @author Juls
 *
 */
public abstract class OptionDialog extends CustomDialog {

	private static final long serialVersionUID = 2920846859970793103L;

	public OptionDialog(Frame frame) {
		super(frame);
		this.setPreferences();
	}
	
	protected void setPreferences() {
		this.setSize(700, 500);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(SingletonFrame.getInstance());
		this.setModal(false);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	/**
	 * The {@code loadImages} abstract method will load images.
	 */
	protected abstract void loadImages();
}
