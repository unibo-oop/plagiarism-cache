package thatlevelagain.view.sprite;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;

/**
 * 
 * represent entity image and position for player.
 *
 */
public class Door extends SpriteImpl {

    private static final int ZERO = 0;
    private static final int DUE = 2;
    private boolean open;
    private boolean falseOpen;
    private final BufferedImage image1, image2;

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
    public Door(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.PORTA_CHIUSA.getPosition()));
        image1 = ImageManager.getListLoader().get(ImagePath.PORTA_CHIUSA.getPosition());
        image2 = ImageManager.getListLoader().get(ImagePath.PORTA_APERTA.getPosition());
        this.open = false;
        this.falseOpen = false;
    }


    @Override
    public final Rectangle getBounds() {
        if (this.open) {
            return new Rectangle(ZERO, ZERO, ZERO, ZERO);
        } else {
            return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }
    /**
     * 
     * @return
     *         open
     */
    public boolean isOpen() {
        return this.open;
    }
    /**
     * 
     * @param open
     *           set open;
     */
    public void setOpen(final boolean open) {
        this.open = open;
        if (this.open) {
            this.setX(this.getX() - this.getWidth());
            this.setWidth(DUE * this.getWidth());
            this.setImage(image2);
        } else {
            this.setX(this.getX() + this.getWidth() / 2);
            this.setWidth(this.getWidth() / 2);
            this.setImage(image1);
        }
    }


    /**
     * 
     * @param falseOpen
     *         set door false open
     */
    public void setFalseOpen(final boolean falseOpen) {
        this.falseOpen = falseOpen;
        if (this.falseOpen) {
            this.setImage(image1);
            this.open = true;
        }
    }
}
