package javawulf.view;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javawulf.controller.PlayerStatus;
import javawulf.model.player.Player;

/**
 * Class that implements useful methods for all Drawer classes.
 */
abstract class AbstractDrawer implements Drawer {

    private final GamePanel gamePanel;
    private final PlayerStatus player;

    /**
     * @param gamePanel The gamepanel the game must be drawn into
     * @param player The current status of the Player character
     */
    AbstractDrawer(final GamePanel gamePanel, final PlayerStatus player) {
        this.gamePanel = gamePanel;
        this.player = player;
    }

    /**
     * It loades an image from the resources.
     * 
     * @param image The image file to be loaded
     * @return The file as a BufferedImage
     */
    protected BufferedImage imageLoader(final ImagePath image) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(image.getPath()));
    }

    /**
     * @return The X-axis position of Player on the screen.
     */
    protected int getPlayerX() {
        return this.gamePanel.getWidth() / 2 - Player.OBJECT_SIZE / 2;
    }

    /**
     * @return The Y-axis position of Player on the screen.
     */
    protected int getPlayerY() {
        return this.gamePanel.getHeight() / 2 - Player.OBJECT_SIZE / 2;
    }

    /**
     * Rotates an image according to the String direction.
     * 
     * @param startingImage The image that must be rotated
     * @param direction The direction the image must face.
     * @return The image rotated 
     */
    protected BufferedImage rotateImage(final BufferedImage startingImage, final String direction) {
        final AffineTransform tx = new AffineTransform();
        AffineTransformOp op;
        final BufferedImage finalImage = startingImage;
        double theta;

        if ("up".equals(direction)) {
            theta = 0;
        } else if ("down".equals(direction)) {
            theta = Math.PI;
        } else if ("right".equals(direction)) {
            theta = Math.PI / 2;
        } else {
            theta = -Math.PI / 2;
        }
        tx.rotate(theta, (double) finalImage.getWidth() / 2, (double) finalImage.getHeight() / 2);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(finalImage, null);
    }

    /**
     * This method draws on the screen the sprites in the correct spot considering the Player's position.
     * This variant is used if the sprite made isn't a square, but a rectangle, so the width and height
     * must be specified
     * 
     * @param graphics Where component must be drawn
     * @param img The image to be drawn
     * @param elementX The position of the element in the game on the X-axis
     * @param elementY The position of the element in the game on the Y-axis
     * @param width The width the sprite should have
     * @param height The height the sprite should have
     */
    protected void drawImage(final Graphics2D graphics, final BufferedImage img, final int elementX, final int elementY,
        final int width, final int height) {
        final int x = this.getPlayerX() - (this.player.getPlayerX() - elementX) * GamePanel.SCALE;
        final int y = this.getPlayerY() - (this.player.getPlayerY() - elementY) * GamePanel.SCALE;
        graphics.drawImage(img, x, y, width * GamePanel.SCALE, height * GamePanel.SCALE, null);
    }

    /**
     * This method draws on the screen the sprites in the correct spot considering the Player's position.
     * 
     * @param graphics Where component must be drawn
     * @param img The image to be drawn
     * @param elementX The position of the element in the game on the X-axis
     * @param elementY The position of the element in the game on the Y-axis
     */
    protected void drawImage(final Graphics2D graphics, final BufferedImage img, final int elementX, final int elementY) {
        this.drawImage(graphics, img, elementX, elementY,
            GamePanel.TILESIZE / GamePanel.SCALE, GamePanel.TILESIZE / GamePanel.SCALE);
    }
}
