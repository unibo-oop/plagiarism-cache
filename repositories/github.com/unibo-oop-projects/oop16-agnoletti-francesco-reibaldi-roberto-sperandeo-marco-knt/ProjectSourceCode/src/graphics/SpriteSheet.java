package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(String path) {
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dall'immagine nelle risorse (rappresentante una griglia virtuale 32x32)
	 * contenente gli oggetti da disegnare seleziono l'immagine desiderata
	 * 
	 * @param x, y
	 * @return sheet.getSubimage(x*32-32, y*32-32, 32, 32) relativo alla sub
	 *         immagine selezionata delle dimensioni 32x32
	 */
	public BufferedImage getSprite(int x, int y) {
		return sheet.getSubimage(x * 32 - 32, y * 32 - 32, 32, 32);
	}

}
