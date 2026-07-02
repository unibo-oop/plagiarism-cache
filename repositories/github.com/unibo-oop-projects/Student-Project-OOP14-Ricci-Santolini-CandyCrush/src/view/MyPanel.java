package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * Classe che estende JPanel in modo che contenga un'immagine come sfondo.
 * 
 * @author Beatrice Ricci
 *
 */
public class MyPanel extends JPanel implements IMyPanel {
	private static final long serialVersionUID = -4749088748024631646L;
	
	private final Image image;
	
	/**
	 * Costruttore con passaggio di parametro.
	 * 
	 * @param img immagine da impostare come sfondo del JPanel
	 */
	public MyPanel(final Image img) {
		this.image = img;
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.image, 0, 0, this);
	}
}