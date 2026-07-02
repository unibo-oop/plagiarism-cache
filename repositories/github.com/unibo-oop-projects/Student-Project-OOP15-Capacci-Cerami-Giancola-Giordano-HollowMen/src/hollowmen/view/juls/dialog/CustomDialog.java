package hollowmen.view.juls.dialog;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;

import hollowmen.controller.ViewObserver;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.SingletonFrame;

/**
 * The {@code CustomDialog} abstract class represents the head of
 * the dialog hierarchy.
 * @author Juls
 */
public abstract class CustomDialog extends JDialog {

	private static final long serialVersionUID = 3277374667436705243L;
	protected String name;
	protected ViewObserver v;
	protected final JLabel background = new JLabel();
	protected final JLabel message = new JLabel();

	
	public CustomDialog(Frame frame) {
		super(SingletonFrame.getInstance());
		this.addBackground();
		v = UtilitySingleton.getInstance().getObserver();
	}

	/**
	 * The {@code setPreferences} method will set the look&feel of the dialog.
	 */
	protected abstract void setPreferences();
	
	/**
	 * The {@code addBackground} method tries to load the image needed as background
	 * of the dialog and eventually set it.
	 */
	protected void addBackground() {
		background.setIcon(UtilitySingleton.getInstance().getStorage().get("dialog"));
		this.setContentPane(background);	
	}
}
