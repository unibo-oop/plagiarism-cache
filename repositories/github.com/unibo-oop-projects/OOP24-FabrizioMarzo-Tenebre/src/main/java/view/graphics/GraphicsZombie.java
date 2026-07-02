package view.graphics;

import java.awt.image.BufferedImage;
import model.entities.zombie.Zombie;

/**
 * Interface for rendering a zombie entity.
 * 
 * <p>
 * This interface defines the method required to draw the visual representation
 * of a {@link Zombie} using a given image.
 * </p>
 */
public interface GraphicsZombie {

    /**
     * Draws the zombie using the provided image.
     *
     * @param zob   the {@link Zombie} instance to be drawn
     * @param image the {@link BufferedImage} used for rendering the zombie's
     *              appearance
     */
    void drawZombie(final Zombie zob, final BufferedImage image);
}
