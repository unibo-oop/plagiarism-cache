package thatlevelagain.view.sprite.trophies;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * status and image for trophy.
 *
 */
public class Trophy11 extends Trophies {


    /**
     * constructor.
     * @param x
     *         x position
     * @param y
     *         y position
     * @param width
     *         shape's width
     * @param height
     *         shape's height
     * @param check
     *         if trophy was found 
     */
    public Trophy11(final int x, final int y, final int width, final int height, final boolean check) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.TROPHY11X.getPosition()));
        if (!check) {
            this.setImage(ImageManager.getListLoader().get(ImagePath.TROPHY11X.getPosition()));
        } else {
            this.setImage(ImageManager.getListLoader().get(ImagePath.TROPHY11V.getPosition()));
        }
    }
}
