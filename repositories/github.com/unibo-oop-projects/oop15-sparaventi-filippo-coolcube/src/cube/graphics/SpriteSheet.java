package cube.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(String path){
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Dall'immagine grande con tanti quadrati (32x32) pesco l'immagine in questione.
	 *
	 * @param x, y
	 * @return sheet.getSubimage(x*32-32, y*32-32, 32, 32) relativo alla sub immagine selezionata delle dimensioni 32x32
	 */
	public BufferedImage getSprite(int x, int y){
		return sheet.getSubimage(x*32-32, y*32-32, 32, 32);
	}

}
