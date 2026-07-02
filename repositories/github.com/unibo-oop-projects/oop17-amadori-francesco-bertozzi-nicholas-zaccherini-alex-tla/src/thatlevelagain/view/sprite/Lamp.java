package thatlevelagain.view.sprite;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * represent entity image and position for hint button.
 *
 */
public class Lamp extends SpriteImpl {

    private final int initialHeight;
    private final int initialWidth;
    private final int initialY;
    private final int initialX;

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
    public Lamp(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.LAMPADINA.getPosition()));
        this.initialHeight = height;
        this.initialWidth = width;
        this.initialX = x;
        this.initialY = y;
    }

    /**
     * 
     * @return
     *         initial Height.
     */
    public int getInitialHeight() {
        return this.initialHeight;
    }
    /**
     * 
     * @return
     *         initial Height.
     */
    public int getInitialWidth() {
        return this.initialWidth;
    }

    /**
     * 
     * @param pressed
     *        if true, this increase image's size.
     */
    public void bigImage(final boolean pressed) {
        if (pressed) {
            this.setHeight(this.initialHeight + GamePanel.BLOCK_HEIGHT / 2);
            this.setWidth(this.initialWidth + GamePanel.BLOCK_WIDTH / 2); 
            this.setY(this.initialY - GamePanel.BLOCK_HEIGHT / 4);
            this.setX(this.initialX - GamePanel.BLOCK_HEIGHT / 4);
        } else {
            this.setHeight(this.initialHeight);
            this.setWidth(this.initialWidth);
            this.setY(this.initialY);
            this.setX(this.initialX);
        }
    }

}
