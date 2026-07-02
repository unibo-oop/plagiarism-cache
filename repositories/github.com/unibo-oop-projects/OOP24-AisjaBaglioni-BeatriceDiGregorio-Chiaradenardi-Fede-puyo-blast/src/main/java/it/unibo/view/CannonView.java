package it.unibo.view;

import javax.swing.*;

import it.unibo.model.Scale;
import it.unibo.view.interfaces.CannonViewInterface;
import it.unibo.view.interfaces.ViewInterface;
import it.unibo.model.CannonModel;
import it.unibo.model.Point2DI;

import java.awt.*;
import java.net.URL;

/**
 * The CannonView class represents the visual aspect of the cannon in the game.
 * It is responsible for rendering the cannon's image based on its state
 * (position and angle).
 */
public class CannonView extends JPanel implements CannonViewInterface, ViewInterface {
    /**
     * An array to hold the images of the cannon in different angles.
     * Each image corresponds to a specific angle range of the cannon.
     */

    private Image[] cannonImages;
    /**
     * The CannonModel that stores the cannon's state, including its position and
     * angle.
     */

    private CannonModel cannonModel;
    /**
     * The width of the cannon image.
     */

    private int imageWidth;
    /**
     * The height of the cannon image.
     */

    private int imageHeight;
    /**
     * The scale used to determine the size of the cannon based on the window size.
     */
    private Scale scale;

    /**
     * Constructor for CannonView, initializing the cannon's state and loading the
     * images for the cannon.
     * 
     * @param scale       The scale object used to determine the appropriate size of
     *                    the cannon.
     * @param cannonModel The CannonModel that holds the state of the cannon.
     */
    public CannonView(Scale scale, CannonModel cannonModel) {
        this.scale = scale;
        this.cannonModel = cannonModel;
        this.cannonImages = new Image[5];
        /**
         * Paths for cannon images corresponding to different angles.
         */
        String[] cannonImagePaths = { "CannonImage.png", "CannonImage1.png", "CannonImage2.png", "CannonImage3.png",
                "CannonImage4.png" };

        for (int i = 0; i < cannonImagePaths.length; i++) {
            final URL imageUrl = getClass().getClassLoader().getResource("images/" + cannonImagePaths[i]);
            this.cannonImages[i] = new ImageIcon(imageUrl).getImage();
        }
        /**
         * Initialize dimensions based on the first cannon image.
         */
        this.imageWidth = cannonImages[0].getWidth(null);
        this.imageHeight = cannonImages[0].getHeight(null);
    }

    /**
     * Returns the CannonModel associated with this view.
     * 
     * @return the cannon model.
     */
    public CannonModel getCannonModel() {
        return this.cannonModel;
    }

    /**
     * Calculates the center point for drawing the cannon, based on its position and
     * the window's scale.
     * 
     * @return the Point2DI object representing the center position of the cannon.
     */
    public Point2DI getCenter() {
        /**
         * Calculate the new width based on the scale
         */
        int newWidth = this.scale.getScale() / 10;
        int puyoCellSize = this.scale.getScale() / 16;
        int offsetX = puyoCellSize * 4;
        /**
         * Calculate the new height in proportion to the width
         * newWidth : newHeight = imageWidth : imageHeight
         */
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int y = (this.scale.getScale() * 6) / 8;
        /**
         * Calculate the x position of the cannon
         * xModel : 1 = x : scale - newWidth
         */
        double xdouble = offsetX + this.cannonModel.getX() * (this.scale.getScale() - newWidth - 2 * offsetX);
        int x = (int) xdouble;

        return new Point2DI(x + newWidth / 2, y + newHeight / 2);
    }

    /**
     * Draws the cannon image at its correct position and with the correct angle.
     * 
     * @param g the Graphics object used to render the cannon.
     */
    @Override
    public final void draw(final Graphics g) {
        int newWidth = this.scale.getScale() / 10;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;

        Point2DI center = getCenter();

        /**
         * Calculate the image index based on the cannon's angle
         */
        double angle = this.cannonModel.getAngle();
        int imageIndex = getImageIndexForAngle(angle);

        /**
         * Draw the cannon image at the calculated position and with the correct size.
         */
        g.drawImage(
                cannonImages[imageIndex],
                center.x() - newWidth / 2,
                center.y() - newHeight / 2,
                center.x() + newWidth / 2,
                center.y() + newHeight / 2,
                0, 0,
                imageWidth, imageHeight, null);
    }

    /**
     * Determines the index of the cannon image to be used based on the angle.
     * The angle is divided into 5 ranges, each corresponding to a different image.
     * 
     * @param angle the angle of the cannon.
     * @return the index of the image corresponding to the angle.
     */
    public int getImageIndexForAngle(final double angle) {
        if (angle <= 0.2) {
            return 0;
        } else if (angle <= 0.4) {
            return 1;
        } else if (angle <= 0.6) {
            return 2;
        } else if (angle <= 0.8) {
            return 3;
        } else {
            return 4;
        }
    }
}