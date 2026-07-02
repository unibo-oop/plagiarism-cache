package hollowmen.view.juls.buttons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.SwingConstants;

/**
 * The {@code IconButton} class determines a specialization
 * of {@link CustomButton}. It represents a button with icon and/or text on it.
 * @author Juls
 *
 */
public class IconButton extends CustomButton {

	private static final long serialVersionUID = -1151945739902849112L;
	
	public IconButton() {
		super();
		this.setPreferences();
	}
	
	public IconButton(Icon icon) {
		super(icon);
		this.setPreferences();
	}
	
	public IconButton(String text, Icon icon) {
		super(icon);
		this.setIcon(icon);
		this.setText(text);
		this.setForeground(Color.WHITE);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setPreferences();
	}
	
	public void setPreferences() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
