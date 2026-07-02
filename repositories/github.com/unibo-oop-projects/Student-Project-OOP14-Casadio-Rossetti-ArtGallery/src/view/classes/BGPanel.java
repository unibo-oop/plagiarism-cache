package view.classes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This is the class which paints the background.
 * @author Sofia Rosetti
 *
 */

public class BGPanel extends JPanel {

	private static final long serialVersionUID = -2575639246227909138L;

	@Override
	public void paintComponent(final Graphics g) {
		final Image image = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
