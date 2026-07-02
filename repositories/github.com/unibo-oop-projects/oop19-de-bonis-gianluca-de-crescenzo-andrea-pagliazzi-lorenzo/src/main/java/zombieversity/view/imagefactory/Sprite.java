package zombieversity.view.imagefactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * Interface used to make sprite.
 *
 */
public interface Sprite {

    /**
     * 
     * @return imageView of the sprite.
     */
    ImageView getImageView();

    /**
     * 
     * @return modified image.
     */
    Image getImage();
}
