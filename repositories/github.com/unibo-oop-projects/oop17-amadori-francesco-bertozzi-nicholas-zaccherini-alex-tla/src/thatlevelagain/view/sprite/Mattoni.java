package thatlevelagain.view.sprite;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * represent entity image and position for wall.
 *
 */
public class Mattoni extends SpriteImpl {


    /**
     * constructor.
     * @param x
     *         x position.
     * @param y
     *         y position.
     * @param width
     *        shape's width.
     * @param height
     *        shape's height.
     */
    public Mattoni(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.MATTONE.getPosition()));
    }
}
