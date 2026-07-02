package thatlevelagain.view.sprite.trophies;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * status and image for trophy.
 *
 */
public class Trophy4 extends Trophies {


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
    public Trophy4(final int x, final int y, final int width, final int height, final boolean check) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.TROPHY4X.getPosition()));
        if (!check) {
            this.setImage(ImageManager.getListLoader().get(ImagePath.TROPHY4X.getPosition()));
        } else {
            this.setImage(ImageManager.getListLoader().get(ImagePath.TROPHY4V.getPosition()));
        }
    }
}
