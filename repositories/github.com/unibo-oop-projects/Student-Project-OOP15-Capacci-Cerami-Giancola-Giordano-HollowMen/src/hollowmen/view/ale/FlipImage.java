package hollowmen.view.ale;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FlipImage extends ImageIcon{
	
	private static final long serialVersionUID = -8228473622139069793L;
	JLabel tmp;
	
	public FlipImage(Image file){
		super(file);
	}

	public synchronized void paintIcon(Component c, Graphics g, int x, int y){
		Graphics2D g2=(Graphics2D)g.create();
		g2.translate(getIconWidth(), 0);
		g2.scale(-1, 1);//flip the image
		super.paintIcon(c, g2, x, y);
		
	}

}
