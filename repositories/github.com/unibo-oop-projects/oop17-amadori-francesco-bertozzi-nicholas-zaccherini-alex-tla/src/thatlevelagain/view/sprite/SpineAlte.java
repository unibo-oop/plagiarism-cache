package thatlevelagain.view.sprite;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * represent entity image and position for spineAlte.
 *
 */
public class SpineAlte extends SpriteImpl {

    private final int initialHeight;
    private final int initialY;
    private int time;
    private int pause;
    private static final int DUE = 2;
    private static final int TRE = 3;
    private static final int QUATTRO = 4;
    private static final int CINQUE = 5;
    private static final int SEI = 6;
    private static final int ADD = 1;
    private static final int TIME1 = 5;
    private static final int SETTE = 7;
    private static final int OTTO = 8;
    private static final int PAUSE = 120;
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
     */
    public SpineAlte(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.SPINE_ALTE.getPosition()));
        pause = 0;
        this.initialHeight = height;
        this.initialY = y;
        this.time = 0;
    }
    /**
     * 
     * @return
     *         initialHeight
     */
    public int getInitialHeight() {
        return initialHeight;
    }
    /**
     * 
     * @return
     *         initialY
     */
    public int getInitialY() {
        return initialY;
    }
    /**
     * 
     * @return
     *         time value.
     */
    public int getTime() {
        return this.time;
    }
    /**
     * 
     * @param time
     *         set time value.
     */
    public void setTime(final int time) {
        this.time = time;
    }

    /**
     * reduce img dimension.
     */
    public void reduce() {
        if (this.getTime() < TIME1) {
            this.setY(this.getInitialY());
        } else if ((this.getTime() >= TIME1 && this.getTime() < TIME1 * DUE) || (this.getTime() >= TIME1 * SETTE && this.getTime() <= TIME1 * OTTO)) {
            this.setY(this.getInitialY() + (this.getInitialHeight() / QUATTRO));
            if (this.getTime() == TIME1 * OTTO) {
                this.setTime(-ADD);
            }
        } else if (this.getTime() >= TIME1 * DUE && this.getTime() <= TIME1 * TRE || this.getTime() >= TIME1 * SEI && this.getTime() <= TIME1 * SETTE) {
            this.setY(this.getInitialY() + (this.getInitialHeight() / DUE));
        } else if (this.getTime() >= TIME1 * TRE && this.getTime() < TIME1 * QUATTRO || this.getTime() >= TIME1 * CINQUE && this.getTime() <= TIME1 * SEI) {
            this.setY(this.getInitialY() + (this.getInitialHeight() * TRE / QUATTRO));
        } else if (this.getTime() == TIME1 * QUATTRO) {
                if (this.pause < PAUSE) {
                    this.pause++;
                    this.setTime(this.getTime() - ADD);
                } else if (this.pause == PAUSE) {
                    this.pause = 0;
         }
            this.setY(this.getInitialY() + this.getInitialHeight());
        }
        this.setTime(this.getTime() + ADD);
    }
}

