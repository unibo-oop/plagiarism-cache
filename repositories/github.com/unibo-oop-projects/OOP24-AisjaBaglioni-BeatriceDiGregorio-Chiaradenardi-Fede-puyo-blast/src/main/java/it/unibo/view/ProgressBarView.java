package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import it.unibo.model.ProgressBarModel;
import it.unibo.model.Scale;

/**
 * The ProgressBarView class is responsible for rendering the progress bar on
 * the screen.
 * It displays the progress visually using an empty and a full progress bar
 * image.
 */
public class ProgressBarView extends JPanel {

    /**
     * The image representing the empty progress bar (unfilled).
     */
    private final Image progressBarImage;

    /**
     * The image representing the filled progress bar.
     */
    private final Image progressBarFullImage;

    /**
     * The ProgressBarModel that contains the current progress data (e.g., charge
     * level).
     */
    private final ProgressBarModel progressModel;

    /**
     * The width of the progress bar image.
     */
    private int imageWidth;

    /**
     * The height of the progress bar image.
     */
    private int imageHeight;

    /**
     * The scale used to determine the size and position of the progress bar.
     */
    private final Scale scale;

    /**
     * Constructor for ProgressBarView that initializes the progress bar images and
     * model.
     * 
     * @param progressModel The model containing the current progress (charge
     *                      level).
     * @param scale         The scale object used to determine the size of the
     *                      progress bar based on the window size.
     */
    public ProgressBarView(ProgressBarModel progressModel, Scale scale) {
        this.progressBarImage = new ImageIcon(
                getClass().getClassLoader().getResource("images/" + "ProgressBarEmpty.png"))
                .getImage();
        this.progressBarFullImage = new ImageIcon(
                getClass().getClassLoader().getResource("images/" + "ProgressBarFull.png"))
                .getImage();

        /**
         * Get the image dimensions for scaling
         */
        this.imageWidth = progressBarImage.getWidth(null);
        this.imageHeight = progressBarImage.getHeight(null);

        this.progressModel = progressModel;
        this.scale = scale;
    }

    /**
     * Paints the progress bar onto the panel.
     * It draws the empty progress bar first, then overlays the filled part based on
     * the current charge level.
     * 
     * @param g the Graphics object used for drawing the progress bar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /**
         * Calculate the new width of the progress bar based on the scale and
         * calculate the new height in proportion to the width
         */
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        /**
         * Determine the y position to place the progress bar and
         * the x position to center the progress bar.
         */
        int y = (this.scale.getScale() * 29) / 32;
        int x = (this.scale.getScale() - newWidth) / 2;
        /**
         * Draw the empty progress bar as the background
         */
        g.drawImage(progressBarImage, x, y, x + newWidth, y + newHeight, 0, 0, imageWidth, imageHeight, null);
        /**
         * Calculate the width of the filled portion based on the charge level and
         * draw the filled portion of the progress bar
         */
        double dxdouble = this.progressModel.getChargeLevel() * newWidth;
        int dx = (int) dxdouble;
        g.drawImage(progressBarFullImage, x, y, x + dx, y + newHeight, 0, 0, imageWidth, imageHeight, null);
    }
}
