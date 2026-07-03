package view.gamemap;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class that extends from the JPanel draws a panel with the map as a background
 *
 */
public class WorldGUI extends JPanel {
	private Image img;
	
	  public WorldGUI(String s) {
	    this(new ImageIcon(ClassLoader.getSystemResource(s)).getImage());
	  }

	  public WorldGUI(Image s) {
	    this.img = s;
	  }
	  
	  public void paintComponent(Graphics g) {
		  g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),null);
	  }

}
		