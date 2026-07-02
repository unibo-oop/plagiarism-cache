package view.graphics;

import java.awt.image.BufferedImage;

import model.armory.munition.Munition;

/**
 * Interface for rendering a munition entity.
 * 
 * <p>
 * This interface defines the method required to draw the visual representation
 * of a {@link Munition} using a provided image.
 * </p>
 */
public interface GraphicsMunition {

        /**
         * Draws the munition using the specified image.
         *
         * @param mun   the {@link Munition} instance to be drawn
         * @param image the {@link BufferedImage} used for rendering the munition's
         *              appearance
         */
        void drawMunition(final Munition mun, final BufferedImage image);
}
