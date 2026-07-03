package graphics;

import java.awt.image.BufferedImage;

public class Sprite {

	public SpriteSheet sheet;
	public BufferedImage image;

	public Sprite(SpriteSheet sheet, int x, int y) {
		image = sheet.getSprite(x, y);
	}

	/**
	 * Ritorna l'immagine in questione presa dal metodo getSprite, che a sua
	 * volta prende l'immagine 32x32 dalla griglia contenente gli oggetti.
	 *
	 * @return image
	 */
	public BufferedImage getBufferedImage() {
		return image;
	}
}
