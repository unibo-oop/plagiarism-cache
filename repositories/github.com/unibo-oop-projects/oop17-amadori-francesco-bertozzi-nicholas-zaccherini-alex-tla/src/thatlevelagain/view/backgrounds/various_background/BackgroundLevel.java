package thatlevelagain.view.backgrounds.various_background;

import thatlevelagain.view.backgrounds.BackgroundImpl;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * Level Background.
 *
 */
public class BackgroundLevel extends BackgroundImpl {

    /**
     * 
     * 
     */
        public BackgroundLevel() {
            super();
            this.setBackgroundImage(ImageManager.getListLoader().get(ImagePath.BACKGROUND_LEVEL_IMAGE.getPosition()));
        }
}
