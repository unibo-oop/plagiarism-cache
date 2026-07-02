package hollowmen.view.juls.buttons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * The {@code CustomButton} represents the head of the Hierarchy
 * of the buttons used in the app.
 * @author Juls
 *
 */
public abstract class CustomButton  extends JButton {
	
	private static final long serialVersionUID = -1680309409986913252L;
	protected boolean isOver, isClicked, isUnlocked;
	protected boolean isAvailable = true;
	protected MyMouseAdapter ma = new MyMouseAdapter();

	public CustomButton() {
		super.addMouseListener(ma);
	}
	
	public CustomButton(String text) {
		super(text);
		super.addMouseListener(ma);
	}
	
	public CustomButton(Icon icon) {
		super(icon);
		super.addMouseListener(ma);
	}

	/**
	 * The {@code setPreferences} abstract method will set the characteristics 
	 * and the look&feel of the button.
	 */
	public abstract void setPreferences();
	
	/**
	 * The {@code addMouseListener} method adds a mouse listener to the button.
	 * @param ma - the custom MouseAdapter taken from {@link MyMouseAdapter}.
	 */
	public void addMouseListener(MyMouseAdapter ma) {}
	
	@Override
	public void setEnabled(boolean b) {
		if( b ) {
			isAvailable = true;
		} else {
			isAvailable = false;
		}
		super.setEnabled(b);;
	}
	
	
	/**
	 * The {@code MyMouseAdapter} inner class implements some of the methods
	 * of the MouseAdapter class adding some boolean flags. The flags will be used
	 * in the paintComponent() method in order to determine which image must be set
	 * on the button.
	 * 
	 * @author Juls
	 */
	protected class MyMouseAdapter extends MouseAdapter {
		
		public void mouseEntered(MouseEvent e) {
				isOver = true;
    	}
    	public void mouseExited(MouseEvent e) {
				isOver = false;
    	}
    	public void mouseClicked(MouseEvent e) {
				isClicked = true;
    	}
	}
}
