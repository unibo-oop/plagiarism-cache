package view;

import java.awt.Image;
import java.util.Random;

import utility.ImageLoader;

/**
 * The Class Background.
 */
public class Background {

	/** The loader. */
	private final ImageLoader loader = ImageLoader.getImageLoader();
	
	/** The image. */
	private final Image image;
	
	/**
	 * Instantiates a new background.
	 *
	 * @param title the title
	 */
	public Background(final String title) {
		final Random random = new Random();
		this.image=loader.getBackgroundImages().get(title).get(random.nextInt(loader.getBackgroundImages().get(title).size()));
		
	}
	
	/**
	 * Load image.
	 *
	 * @return the image
	 */
	public Image loadImage() {
		return image;
	}
}


