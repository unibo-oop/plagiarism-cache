package thatlevelagain.view.sprite;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * represent entity image and position for dog.
 *
 */
public class Dog extends SpriteImpl implements Runnable {

    private static final int WAIT_TIME1 = 5500;
    private static final int WAIT_TIME2 = 7500;
    private static final int X_SCALE = 7;
    private static final int Y_SCALE = 5;
    private static final int HEIGHT_SCALE = 2 * Y_SCALE;
    private static final int WIDTH_SCALE = 2 * X_SCALE;
    private static final int LIMIT = 2560;
    private boolean sleeping;
    private final int xZone;
    private final int yZone;
    private final int widthZone;
    private final int heightZone;
    private boolean stop;
    private final BufferedImage image1, image2;
    private final int wait;
    private final Toolkit t = Toolkit.getDefaultToolkit();
    private final int sizeScreen  = (int) t.getScreenSize().getWidth();

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
    public Dog(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.CANE_SVEGLIO.getPosition()));
        image1 = ImageManager.getListLoader().get(ImagePath.CANE_SVEGLIO.getPosition());
        image2 = ImageManager.getListLoader().get(ImagePath.CANE_ADDORMENTATO.getPosition());
        this.xZone = this.getX() - X_SCALE * GamePanel.BLOCK_WIDTH + GamePanel.BLOCK_WIDTH;
        this.yZone = this.getY() - Y_SCALE * GamePanel.BLOCK_HEIGHT;
        this.heightZone = HEIGHT_SCALE * GamePanel.BLOCK_HEIGHT;
        this.widthZone = WIDTH_SCALE * GamePanel.BLOCK_WIDTH;
        final Thread th = new Thread(this);
        this.sleeping = false;
        this.stop = false;
        if (sizeScreen > LIMIT) {
            wait = WAIT_TIME2;
        } else {
            wait = WAIT_TIME1;
        }
        th.start();
    }
    /**
     * if dog is sleeping, its bounds are its dimension, but if it isn't sleeping, its bounds are extends.
     */
    @Override
    public final Rectangle getBounds() {
        if (this.sleeping) {
            return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            return new Rectangle(this.xZone, this.yZone, this.widthZone, this.heightZone);
        }
    }

    /**
     * 
     * @return
     *         stop value
     */
    public boolean isStop() {
        return stop;
    }
    /**
     * 
     * @param stop
     *         set stop value
     */
    public void setStop(final boolean stop) {
        this.stop = stop;
    }

    @Override
    public final void run() {
        this.stop = true;
        while (this.stop) {
            if (this.sleeping) {
                this.sleeping = false;
                this.setImage(image1);
                if (!this.stop) {
                    break;
                }
                SoundManager.getListLoader().get(SoundPath.DOGBARKPATH.getPosition()).play();
            } else {
                this.sleeping = true;
                this.setImage(image2);
                if (!this.stop) {
                    break;
                }
                SoundManager.getListLoader().get(SoundPath.SNOREPATH.getPosition()).play();
            }
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 
     * @return
     *          if dog is sleeping
     */
    public boolean isSleeping() {
        return this.sleeping;
    }
    /**
     * 
     * @return
     *         zone rectangle
     */
    public Rectangle getRectZone() {
        return new Rectangle(this.xZone, this.yZone, this.widthZone, this.heightZone);
    }
}
