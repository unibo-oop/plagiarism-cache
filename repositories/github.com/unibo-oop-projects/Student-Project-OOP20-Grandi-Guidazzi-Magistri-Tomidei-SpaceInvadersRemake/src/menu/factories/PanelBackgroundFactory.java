package menu.factories;

import java.awt.Graphics;


import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.Constants;

public class PanelBackgroundFactory extends JPanel{

	private static final long serialVersionUID = 1L;
	private Image image;
	private Image resizedImage;
	
	/**
	 * A class that create a simple JPanel with a background image that is given in the constructor
	 * @param imageLocation
	 */
	public PanelBackgroundFactory(String imageLocation) {
		try {
			image = ImageIO.read(ClassLoader.getSystemResource(imageLocation));
			this.resizedImage = image.getScaledInstance(Constants.ObjectDimension.preferDimension.width,
					Constants.ObjectDimension.preferDimension.height, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			this.setOpaque(false);
		}
	}

	@Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.drawImage(resizedImage, 0, 0, this);          
	 }
}
