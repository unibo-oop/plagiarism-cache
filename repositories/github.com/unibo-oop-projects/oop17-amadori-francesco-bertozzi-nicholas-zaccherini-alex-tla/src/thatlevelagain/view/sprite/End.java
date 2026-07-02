package thatlevelagain.view.sprite;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * represent entity image and position for end.
 *
 */
public class End extends SpriteImpl {

    private static final int LEVEL1 = 1;
    private static final int LEVEL2 = 2;
    private static final int LEVEL3 = 3;
    private static final int LEVEL4 = 4;
    private static final int LEVEL5 = 5;
    private static final int LEVEL6 = 6;
    private static final int LEVEL7 = 7;
    private static final int LEVEL8 = 8;
    private static final int LEVEL9 = 9;
    private static final int LEVEL10 = 10;
    private static final int LEVEL11 = 11;
    private static final int LEVEL12 = 12;

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
     * @param level
     *         number of level
     */
    public End(final int x, final int y, final int width, final int height, final int level) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.END1.getPosition()));
        switch (level) {
            case LEVEL1: this.setImage(ImageManager.getListLoader().get(ImagePath.END1.getPosition()));
                    break;
            case LEVEL2: this.setImage(ImageManager.getListLoader().get(ImagePath.END2.getPosition()));
                    break;
            case LEVEL3: this.setImage(ImageManager.getListLoader().get(ImagePath.END3.getPosition()));
                    break;
            case LEVEL4: this.setImage(ImageManager.getListLoader().get(ImagePath.END4.getPosition()));
                    break;
            case LEVEL5: this.setImage(ImageManager.getListLoader().get(ImagePath.END5.getPosition()));
                    break;
            case LEVEL6: this.setImage(ImageManager.getListLoader().get(ImagePath.END6.getPosition()));
                break;
            case LEVEL7: this.setImage(ImageManager.getListLoader().get(ImagePath.END7.getPosition()));
                break;
            case LEVEL8: this.setImage(ImageManager.getListLoader().get(ImagePath.END8.getPosition()));
                break;
            case LEVEL9: this.setImage(ImageManager.getListLoader().get(ImagePath.END9.getPosition()));
                break;
            case LEVEL10: this.setImage(ImageManager.getListLoader().get(ImagePath.END10.getPosition()));
                break;
            case LEVEL11: this.setImage(ImageManager.getListLoader().get(ImagePath.END11.getPosition()));
                break;
            case LEVEL12: this.setImage(ImageManager.getListLoader().get(ImagePath.END12.getPosition()));
                break;
            default:
                break;
        }
    }
}
