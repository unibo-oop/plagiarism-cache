package thatlevelagain.view.backgrounds.various_background;

import thatlevelagain.view.backgrounds.BackgroundImpl;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * background help class.
 *
 */
public class BackgroundHelp extends BackgroundImpl {

/**
 * 
 * 
 */
    public BackgroundHelp() {
        super();
        this.setBackgroundImage(ImageManager.getListLoader().get(ImagePath.BACKGROUND_HELP_IMAGE.getPosition()));
    }
}
