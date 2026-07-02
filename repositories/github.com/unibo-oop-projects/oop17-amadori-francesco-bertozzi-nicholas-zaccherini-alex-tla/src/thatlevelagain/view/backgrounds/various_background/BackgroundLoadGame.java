package thatlevelagain.view.backgrounds.various_background;

import thatlevelagain.view.backgrounds.BackgroundImpl;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * background for load game state.
 *
 */
public class BackgroundLoadGame extends BackgroundImpl {

    /**
     * 
     * 
     */
    public BackgroundLoadGame() {
         super();
         this.setBackgroundImage(ImageManager.getListLoader().get(ImagePath.BACKGROUND_LOADGAME_IMAGE.getPosition()));
    }
}
