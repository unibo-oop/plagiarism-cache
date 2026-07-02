package dev.spaccabolle.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Class UIImageButton.
 */
public class UIImageButton extends UIObject {

	/** The immagini. */
	private BufferedImage[] immagini;
	
	/** The clicker. */
	private ClickListener clicker;
	
	/**
	 * Instantiates a new UI image button.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param images the images
	 * @param clicker the clicker
	 */
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.immagini = images;
		this.clicker = clicker;
	}

	/**
	 * Tick.
	 */
	public void tick() {}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		if(sopra)
			g.drawImage(immagini[1], (int) x, (int) y, larghezza, altezza, null);
		else
			g.drawImage(immagini[0], (int) x, (int) y, larghezza, altezza, null);
	}

	/**
	 * On click.
	 */
	public void onClick() {
		clicker.onClick();
		
	}
	

}