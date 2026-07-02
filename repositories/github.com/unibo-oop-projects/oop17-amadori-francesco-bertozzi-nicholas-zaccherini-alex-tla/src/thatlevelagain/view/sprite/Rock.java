package thatlevelagain.view.sprite;

import thatlevelagain.ScreenSize;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * represent entity image and position for rock.
 *
 */
public class Rock extends SpriteImpl {

    private static final int SPEED = 1;    /**
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
    public Rock(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.ROCCIA.getPosition()));
        this.setY((int) (Math.random() * ScreenSize.NUMBER_BLOCK_HEIGHT - 4) * GamePanel.BLOCK_HEIGHT + GamePanel.BLOCK_HEIGHT * 3);
        this.setX((int) (((Math.random() * ScreenSize.NUMBER_BLOCK_WIDTH - 3) * GamePanel.BLOCK_WIDTH) + GamePanel.BLOCK_WIDTH));
    }

    /**
     * 
     */
    public void changePosition() {
        this.setY(this.getY() + SPEED);
        if (this.getY() >= GamePanel.HEIGHT - GamePanel.BLOCK_HEIGHT) {
            this.setY(GamePanel.BLOCK_HEIGHT * 3);
            this.setX((int) (((Math.random() * ScreenSize.NUMBER_BLOCK_WIDTH - 3) * GamePanel.BLOCK_WIDTH) + GamePanel.BLOCK_WIDTH));
        }
    }
}
