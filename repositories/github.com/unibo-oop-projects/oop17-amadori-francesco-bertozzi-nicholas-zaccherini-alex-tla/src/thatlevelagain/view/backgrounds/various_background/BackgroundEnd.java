package thatlevelagain.view.backgrounds.various_background;

import thatlevelagain.view.backgrounds.BackgroundImpl;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * background end class.
 *
 */
public class BackgroundEnd extends BackgroundImpl {

/**
 * 
 * 
 */
    public BackgroundEnd() {
        super();
        this.setBackgroundImage(ImageManager.getListLoader().get(ImagePath.BACKGROUND_END_IMAGE.getPosition()));
    }
}
