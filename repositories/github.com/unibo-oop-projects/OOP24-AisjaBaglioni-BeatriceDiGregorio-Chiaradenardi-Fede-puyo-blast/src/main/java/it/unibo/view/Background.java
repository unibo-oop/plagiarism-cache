package it.unibo.view;

import it.unibo.view.interfaces.BackgroundInterface;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 * The {@code Background} class represents a drawable background image.
 * It loads an image from the "resources/images" directory and provides a method
 * to draw it.
 */
public class Background implements BackgroundInterface {

    /**
     * The image used as the background.
     */
    private Image backgroundImage;

    /**
     * Constructs a {@code Background} object by loading an image from the
     * "resources/images" directory.
     *
     * @param imagePath the name of the image file (including extension) located in
     *                  "resources/images".
     */
    public Background(String imagePath) {
        /**
         * Loads the image from the "resources/images" directory
         */
        URL imageUrl = getClass().getClassLoader().getResource("images/" + imagePath);
        /**
         *  Assign the loaded image to backgroundImage
         */
        this.backgroundImage = new ImageIcon(imageUrl).getImage();
    }

    /**
     * Draws the background image scaled to fit the specified dimensions.
     *
     * @param g      the {@code Graphics} object used to draw the image.
     * @param width  the width to which the image should be scaled.
     * @param height the height to which the image should be scaled.
     */
    @Override
    public void draw(Graphics g, int width, int height) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        }
    }
}
