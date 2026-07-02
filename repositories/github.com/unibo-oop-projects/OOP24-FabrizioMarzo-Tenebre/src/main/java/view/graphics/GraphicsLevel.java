package view.graphics;

import java.awt.image.BufferedImage;
import java.util.List;

import model.level.types.Level;

/**
 * Interface for rendering a game level.
 * 
 * <p>
 * This interface defines a method to draw the entire level using a
 * nested list of images representing different graphical elements of the level.
 * </p>
 */
public interface GraphicsLevel {

    /**
     * Draws the specified level using the provided images.
     *
     * @param lvl        the {@link Level} object representing the game level to be
     *                   drawn
     * @param allImage   a nested {@link List} of {@link BufferedImage} objects,
     *                   where each inner list contains images related to specific
     *                   graphical components or layers of the level
     * @param otherImage a {@link BufferedImage} objects, where contains other
     *                   graphical components for the level
     */
    void drawLevel(final Level lvl, final List<List<BufferedImage>> allImage, final BufferedImage otherImage);
}
