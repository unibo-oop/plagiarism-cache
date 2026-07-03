package game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import base.Drawing;

/**
 * 
 * @author Chiara
 *
 */

public class StaticImage implements Drawing {
	
	private int x;
	private int y;
	private int lenX;
	private int lenY;
	protected Image imagen;
	private boolean visible;
	
	/**
	 * 
	 * @param resource
	 *        path to load
	 * @param x
	 *        point x
	 * @param y
	 *        point y
	 * @param lenX
	 *        length point x
	 * @param lenY
	 *        length point y
	 */
	public StaticImage(final String resource, final int x, final int y, final int lenX, final int lenY) {
		this.x = x;
		this.y = y;
		this.lenX = lenX;
		this.lenY = lenY;
		this.setVisible(true);
		
		imagen = new ImageIcon(this.getClass().getResource(resource)).getImage();
	}
	
	@Override
	public void draw(Graphics graphic) {
		if (isVisible()) {
			graphic.drawImage(imagen, this.x, this.y, this.lenX, this.lenY, null);	
		}
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(final boolean visible) {
		this.visible = visible;
	}
}