package thatlevelagain.view.sprite;

import java.awt.image.BufferedImage;

import thatlevelagain.character.player.Player;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;

/**
 * 
 * represent entity image and position for player.
 *
 */
public class Cat extends SpriteImpl {

    private static final int NOVE  = 9;
    private static final int ZERO = 0;
    private static final int UNO = 1;
    private final MapImpl map;
    private int aumentoX, aumentoY;
    private int contImm;
    private final int initialX;
    private final BufferedImage image1a, image2a, image1b, image2b;

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
     * @param map
     *         map for collision
     */
    public Cat(final int x, final int y, final int width, final int height, final MapImpl map) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.GATTO_DESTRA1.getPosition()));
        this.aumentoX = 0;
        image1a = ImageManager.getListLoader().get(ImagePath.GATTO_DESTRA1.getPosition());
        image2a = ImageManager.getListLoader().get(ImagePath.GATTO_SINISTRA1.getPosition());
        image1b = ImageManager.getListLoader().get(ImagePath.GATTO_DESTRA2.getPosition());
        image2b = ImageManager.getListLoader().get(ImagePath.GATTO_SINISTRA2.getPosition());
        this.aumentoY = 0;
        this.map = map;
        this.contImm = 0;
        this.initialX = x;
    }

    /**
     * change cat's imagine.
     */
    public void setImm() {
        if ((this.getX() - this.initialX) % NOVE == ZERO) {
            if (this.contImm == ZERO) {
                this.contImm = UNO;
            } else {
                this.contImm = ZERO;
            }
        }
        if (this.aumentoX  == Player.INCREMENT_X) { 
            if (this.contImm == ZERO) {
                this.setImage(image1a);
            } else {
                this.setImage(image1b);
            }
        } else if (this.aumentoX == Player.DECREMENT_X) {
            if (this.contImm == ZERO) {
                this.setImage(image2a); 
            } else {
                this.setImage(image2b);
            }
        }
    }
    /**
     * 
     * @return
     *         aumentoX
     */
    public int getAumentoX() {
        return this.aumentoX;
    }
    /**
     * 
     * @param aumentoX
     *           set aumentoX;
     */
    public void setAumentoX(final int aumentoX) {
        this.aumentoX = aumentoX;
    }
    /**
     * 
     * @return
     *         aumentoY
     */
    public int getAumentoY() {
        return this.aumentoY;
    }
    /**
     * 
     * @param aumentoY
     *           set aumentoX;
     */
    public void setAumentoY(final int aumentoY) {
        this.aumentoY = aumentoY;
    }
    /**
     * 
     * @return
     *         map
     */
    public MapImpl getMap() {
        return this.map;
    }
}
