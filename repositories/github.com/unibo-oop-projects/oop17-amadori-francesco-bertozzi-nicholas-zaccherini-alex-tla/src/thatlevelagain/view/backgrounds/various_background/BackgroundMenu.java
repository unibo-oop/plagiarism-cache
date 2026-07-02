package thatlevelagain.view.backgrounds.various_background;


import thatlevelagain.view.backgrounds.BackgroundImpl;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * background menu class.
 *
 */
public class BackgroundMenu extends BackgroundImpl {

/**
 * 
 * 
 */
    public BackgroundMenu() {
        super();
        this.setBackgroundImage(ImageManager.getListLoader().get(ImagePath.BACKGROUND_MENU_IMAGE.getPosition()));
    }
}
