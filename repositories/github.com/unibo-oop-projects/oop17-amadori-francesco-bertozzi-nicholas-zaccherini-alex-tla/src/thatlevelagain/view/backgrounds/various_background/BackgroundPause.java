package thatlevelagain.view.backgrounds.various_background;

import thatlevelagain.view.backgrounds.BackgroundImpl;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * background pause class.
 *
 */
public class BackgroundPause extends BackgroundImpl {

/**
 * 
 * 
 */
    public BackgroundPause() {
        super();
        this.setBackgroundImage(ImageManager.getListLoader().get(ImagePath.BACKGROUND_PAUSE_IMAGE.getPosition()));
    }
}
