package view;

import java.awt.Image;
import java.util.Random;

import utilities.ImageLoader;

/**
 * Class used to get the right image for the background of a window.
 */
public class BackgroundAnimator {
    private final ImageLoader loader = ImageLoader.getLoader();
    private final Image image;

    /**
     * @param title the title of the window that needs an image.
     */
    public BackgroundAnimator(final String title) {
        final Random random = new Random();
        this.image = loader.getBackgroundImages().get(title).get(random.nextInt(loader.getBackgroundImages().get(title).size()));
    }

    /**
     * @return the right background image
     */
    public Image loadImage() {
        return image;
    }
}
