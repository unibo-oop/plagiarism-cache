package view.graphics;

import java.awt.image.BufferedImage;

import model.entities.survivor.Survivor;

/**
 * Interface for rendering a survivor entity.
 * 
 * <p>
 * This interface defines the method required to draw the visual representation
 * of a {@link Survivor} using a given image.
 * </p>
 */
public interface GraphicsSurvivor {

    /**
     * Draws the survivor using the provided image.
     *
     * @param sur   the {@link Survivor} instance to be drawn
     * @param image the {@link BufferedImage} used for rendering the survivor's
     *              appearance
     */
    void drawSurvivor(final Survivor sur, final BufferedImage image);
}
