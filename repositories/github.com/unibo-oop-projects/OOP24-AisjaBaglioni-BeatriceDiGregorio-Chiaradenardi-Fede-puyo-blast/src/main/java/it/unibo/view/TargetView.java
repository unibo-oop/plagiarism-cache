package it.unibo.view;

import javax.swing.*;
import it.unibo.model.CannonModel;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ViewInterface;
import java.awt.*;
import java.net.URL;

/**
 * The TargetView class is responsible for rendering the cannon's target or
 * sight on the screen.
 * It visually represents the target and adjusts its position based on the
 * cannon's model.
 */
public class TargetView extends JPanel implements ViewInterface {

    /**
     * The image representing the cannon's target sight.
     */
    private final Image cannonTargetImage;

    /**
     * The width of the target image.
     */
    private int imageWidth;

    /**
     * The height of the target image.
     */
    private int imageHeight;

    /**
     * The scale object that determines the size and position of the target on the
     * screen.
     */
    private final Scale scale;

    /**
     * The CannonModel instance that provides the current position and angle of the
     * cannon.
     */
    private final CannonModel cannonModel;

    /**
     * Constructor for TargetView that initializes the target image and the cannon
     * model.
     * 
     * @param scale       The scale object used to adjust the size and position of
     *                    the target.
     * @param cannonModel The model representing the cannon, which provides position
     *                    and angle data.
     */
    public TargetView(Scale scale, CannonModel cannonModel) {
        this.scale = scale;
        this.cannonModel = cannonModel;
        /**
         * Load the image representing the cannon's target (sight)
         */
        final URL imageUrl = getClass().getClassLoader().getResource("images/CannonSightView.png");
        this.cannonTargetImage = new ImageIcon(imageUrl).getImage();
        /**
         * Get the image dimensions for proportional scaling
         */
        this.imageWidth = cannonTargetImage.getWidth(null);
        this.imageHeight = cannonTargetImage.getHeight(null);
    }

    /**
     * Draws the target (sight) at the cannon's current position based on its angle.
     * 
     * @param g The Graphics object used to draw the target image.
     */
    @Override
    final public void draw(final Graphics g) {
        /**
         * Define some constants for positioning and scaling
         */
        int puyoCellSize = this.scale.getScale() / 16;
        int width = this.scale.getScale() / 10;

        int height = (width * this.imageHeight) / this.imageWidth;
        /**
         * Calculate grid offsets and grid size for positioning the target
         */
        int offset_gridx = puyoCellSize * 4;
        int offset_gridy = puyoCellSize;
        int grid_width = puyoCellSize * 8;
        int grid_height = puyoCellSize * 8;
        /**
         * Calculate the target's position based on the cannon's x and angle values
         */
        int cannonPosX = offset_gridx + ((int) (cannonModel.getX() * grid_width));
        int cannonPosY = offset_gridy + ((int) ((1.0 - cannonModel.getAngle()) * grid_height));
        /**
         * Draw the target image centered on the cannon's calculated position
         */
        g.drawImage(
                cannonTargetImage,
                cannonPosX - width / 2, 
                cannonPosY - height / 2,
                cannonPosX + width / 2, 
                cannonPosY + height / 2, 
                0, 0, 
                imageWidth, imageHeight, 
                null); 
    }
}
